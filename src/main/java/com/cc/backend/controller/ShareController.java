package com.cc.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.backend.annotation.RequireRole;
import com.cc.backend.constant.UserConstant;
import com.cc.backend.exception.ErrorCode;
import com.cc.backend.model.dto.common.BaseResponse;
import com.cc.backend.model.dto.common.DeleteRequest;
import com.cc.backend.model.dto.common.PageRequest;
import com.cc.backend.model.dto.share.AddRequest;
import com.cc.backend.model.entity.Share;
import com.cc.backend.model.entity.User;
import com.cc.backend.service.ShareService;
import com.cc.backend.service.UserService;
import com.cc.backend.utils.ResultUtils;
import com.cc.backend.utils.ThrowUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

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
    public BaseResponse<Boolean> deleteShare(@RequestBody DeleteRequest deleteRequest,
                                             HttpServletRequest request){
        ThrowUtils.throwIf(deleteRequest == null, ErrorCode.PARAMS_ERROR);
        Long shareId = deleteRequest.getId();
        User user = userService.getLoginUser(request);
        Long userId = user.getId();
        String userRole = user.getUserRole();
        shareService.deleteShare(shareId, userId, userRole);
        return ResultUtils.success(true);
    }

    /*
    * 修改*/


    /*
    * 查看自己的分享*/
    @RequireRole
    @PostMapping("/list/my")
    public BaseResponse<Page<Share>> listMyShare(@RequestBody PageRequest pageRequest,
                                            HttpServletRequest request){
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        User user = userService.getLoginUser(request);
        Long userId = user.getId();
        QueryWrapper<Share> shareQueryWrapper = new QueryWrapper<>();
        shareQueryWrapper.eq("userId", userId);
        Page<Share> picturePage = shareService.page(new Page<>(current, size),
                shareQueryWrapper);
        // TODO(cc):这里返回的应该要变一下：1、tags改成列表；2、去掉逻辑删除
        return ResultUtils.success(picturePage);
    }

    /*
    * 查看通过审核的公开分享(这里应该做防护)*/


    /*
    * 查看分享的信息详情 */



    /// /////////////////////////////////////////
    ///             管理员权限                 ///
    /// ////////////////////////////////////////

    /*
    * 信息审核*/


    /*
    * 管理置顶项*/

    /// /////////////////////////////////////////
    ///             开发者权限                 ///
    /// ////////////////////////////////////////
    @RequireRole(userRole = UserConstant.DEV_ROLE)
    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile file,
                                           @ModelAttribute AddRequest addRequest) {
        return ResultUtils.success("uuid");
    }
}
