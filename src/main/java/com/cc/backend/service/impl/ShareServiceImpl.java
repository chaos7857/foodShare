package com.cc.backend.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.backend.constant.UserConstant;
import com.cc.backend.exception.ErrorCode;
import com.cc.backend.mapper.ShareMapper;
import com.cc.backend.model.dto.share.AddRequest;
import com.cc.backend.model.entity.Share;
import com.cc.backend.service.ShareService;
import com.cc.backend.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
* @author Admin
* @description 针对表【share】的数据库操作Service实现
* @createDate 2025-06-23 22:24:19
*/
@Service
@Slf4j
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share>
    implements ShareService{

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    public String pictureDir;

    @Resource
    private String recycleDir;

    @Override
    public Long addShare(AddRequest addRequest, MultipartFile picture, Long userId) throws FileNotFoundException {
        // 参数校验
        String title = addRequest.getTitle();
        ThrowUtils.throwIf(title == null, ErrorCode.PARAMS_ERROR);
        String detail = addRequest.getDetail();
        List<String> tags = addRequest.getTags();
        String tagsStr = JSONUtil.toJsonStr(tags);
        // 如果有图片就保存图片并返回url
        // 校验图片格式
        Share share = new Share();
        File dest;
        if (!picture.isEmpty()) {
            String contentType = picture.getContentType();
            String originalFilename = picture.getOriginalFilename();
            ThrowUtils.throwIf(
                    contentType == null || !"image".equals(contentType.split("/")[0]),
                    ErrorCode.PARAMS_ERROR,
                    "图片格式异常"
            );

            String suffix = "";
            if (originalFilename!=null){
                suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            }else {
                suffix = contentType.split("/")[1];
            }

            String fileUrl = UUID.fastUUID() +"."+suffix;
            share.setSharePicture(fileUrl);

            dest = new File(pictureDir+"/"+fileUrl);
        } else {
            dest = null;
        }

        // 封装
        share.setShareTitle(title);
        share.setShareDetail(detail);
        share.setShareTags(tagsStr);
        share.setUserId(userId);
        // 开启事务:插入数据库，上传图片
        transactionTemplate.execute(status -> {
            boolean res = this.save(share);
            ThrowUtils.throwIf(!res, ErrorCode.OPERATION_ERROR);

            if (dest != null) {
                try {
                    picture.transferTo(dest);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return share;
        });

        // 返回shareId
        return share.getId();
    }

    @Override
    public void deleteShare(Long shareId, Long userId, String userRole) {
        // 参数校验
        ThrowUtils.throwIf(shareId < 0, ErrorCode.PARAMS_ERROR);
        Share oldShare = this.getById(shareId);
        ThrowUtils.throwIf(oldShare == null, ErrorCode.PARAMS_ERROR);

        // 不是开发人员需要确认内容归属（管理员也不能随意删除！！！）
        // TODO（cc)：这个写到dev的部分去会更好，但毕竟是重复代码，偷个懒
        ThrowUtils.throwIf(
                !UserConstant.DEV_ROLE.equals(userRole)
                        && !oldShare.getUserId().equals(userId),
                ErrorCode.NO_AUTH_ERROR
        );

        String sharePicture = oldShare.getSharePicture();
        // 删除
        // TODO（cc):这里有些可以拿出来
        transactionTemplate.execute( status -> {
            boolean res = this.removeById(shareId);
            ThrowUtils.throwIf(!res, ErrorCode.OPERATION_ERROR);
            // 删除图片（移动到回收站保留）
            if (sharePicture != null) {
                try {
                    File originFile = new File(pictureDir+"/"+sharePicture);
                    File destFile = new File(recycleDir + "/" + sharePicture);
                    if (originFile.exists()) {
                        Files.move(originFile.toPath(), destFile.toPath(),
                                StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        });
    }
}




