package com.cc.backend.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.backend.annotation.RequireRole;
import com.cc.backend.constant.UserConstant;
import com.cc.backend.exception.ErrorCode;
import com.cc.backend.model.dto.common.BaseResponse;
import com.cc.backend.model.dto.common.DeleteRequest;
import com.cc.backend.model.dto.common.PageRequest;
import com.cc.backend.model.dto.share.AddRequest;
import com.cc.backend.model.dto.share.UpdateRequest;
import com.cc.backend.model.entity.Share;
import com.cc.backend.model.entity.User;
import com.cc.backend.model.vo.ShareVO;
import com.cc.backend.service.ShareService;
import com.cc.backend.service.UserService;
import com.cc.backend.utils.ResultUtils;
import com.cc.backend.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping("/share")
public class ShareController {
    /*分享接口*/

    @Resource
    private ShareService shareService;

    @Resource
    private UserService userService;

    /*
    * 新增分享内容*/
    @PostMapping("/add")
    @RequireRole
    public BaseResponse<Long> addShare(@RequestPart MultipartFile file,
                                       @ModelAttribute AddRequest addRequest,
                                       HttpServletRequest request) throws FileNotFoundException {
        User user = userService.getLoginUser(request);
        Long userId = user.getId();
        Long shareId = shareService.addShare(addRequest, file, userId);
        return ResultUtils.success(shareId);
    }

    /*
    * 删除*/
    @RequireRole
    @PostMapping("/delete")
    public BaseResponse<String> deleteShare(@RequestBody DeleteRequest deleteRequest,
                                             HttpServletRequest request){
        ThrowUtils.throwIf(deleteRequest == null, ErrorCode.PARAMS_ERROR);
        Long shareId = deleteRequest.getId();
        User user = userService.getLoginUser(request);
        Long userId = user.getId();
        String userRole = user.getUserRole();
        shareService.deleteShare(shareId, userId, userRole);
        return ResultUtils.success("ok");
    }

    /*
    * 修改*/
    @RequireRole
    @PostMapping("/update")
    public BaseResponse<String> updateShare(@RequestBody UpdateRequest updateRequest,
                                            HttpServletRequest request){
        ThrowUtils.throwIf(
                updateRequest == null,
                ErrorCode.PARAMS_ERROR
        );

        Long shareId = updateRequest.getId();
        Share oldShare = shareService.getById(shareId);

        Long userId = oldShare.getUserId();
        User user = (User)request.getSession().getAttribute(UserConstant.LOGIN_USER);
        Long loginUserId = user.getId();
        String userRole = user.getUserRole();

        ThrowUtils.throwIf(
                !userId.equals(loginUserId) && UserConstant.USER_ROLE.equals(userRole),
                ErrorCode.NO_AUTH_ERROR
        );
        // TODO（cc）:tags需要转换
        // 图片暂时不允许更新
        BeanUtil.copyProperties(updateRequest, oldShare);

        shareService.updateById(oldShare);
        return ResultUtils.success("ok");
    }


    /*
    * 查看自己的分享*/
    @RequireRole
    @PostMapping("/list/my")
    public BaseResponse<Page<ShareVO>> listMyShare(@RequestBody PageRequest pageRequest,
                                            HttpServletRequest request){
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        User user = userService.getLoginUser(request);
        Long userId = user.getId();
        QueryWrapper<Share> shareQueryWrapper = new QueryWrapper<>();
        shareQueryWrapper.eq("userId", userId);
        Page<Share> sharePage = shareService.page(new Page<>(current, size),
                shareQueryWrapper);
        // 这里返回的应该要变一下：1、tags改成列表；2、去掉逻辑删除
        Page<ShareVO> shareVOPage = shareService.entity2VO(sharePage);

        return ResultUtils.success(shareVOPage);
    }

    /*
    * 查看通过审核的公开分享(这里应该做防护)*/
    @PostMapping("/list/public")
    public BaseResponse<Page<ShareVO>> listPublicShare(@RequestBody PageRequest pageRequest){
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 审核模块做好前就是查询所有的内容，优化点和上一个一样，当然，还需要优化一下参数传递实现模糊查询
        QueryWrapper<Share> shareQueryWrapper = new QueryWrapper<>();
        shareQueryWrapper.eq("lastReviewStatus", "1");
        Page<Share> sharePage = shareService.page(new Page<>(current, size), shareQueryWrapper);
        Page<ShareVO> shareVOPage = shareService.entity2VO(sharePage);
        return ResultUtils.success(shareVOPage);
    }

    /*
    * 未审核的 图片走另一个接口*/

    /*
    * 带缓存的查询方法*/

    /*
    * 查看分享的信息详情 */



    /// /////////////////////////////////////////
    ///             管理员权限                 ///
    /// ////////////////////////////////////////

    /*
    * 管理员发布（不需要审核内容）
    * 管理员需不需要发布功能呢*/


    /*
    * 管理置顶项*/

    /// /////////////////////////////////////////
    ///             开发者权限                 ///
    /// ////////////////////////////////////////
    @RequireRole(userRole = UserConstant.DEV_ROLE)
    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile file,
                                           @ModelAttribute AddRequest addRequest,
                                           HttpServletRequest request) {
        User dev = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER);
        return ResultUtils.success("uuid");
    }


    @RequireRole(userRole = UserConstant.DEV_ROLE)
    @GetMapping("/test/path")
    public BaseResponse<String> pathTest() throws FileNotFoundException {
        URL resource = ResourceUtils.getURL("classpath:static");
        File file = new File(resource.getPath());
        return ResultUtils.success(file.getAbsolutePath());
    }
}
