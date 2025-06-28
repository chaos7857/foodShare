package com.cc.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.backend.annotation.RequireRole;
import com.cc.backend.constant.UserConstant;
import com.cc.backend.exception.ErrorCode;
import com.cc.backend.model.dto.common.BaseResponse;
import com.cc.backend.model.dto.common.PageRequest;
import com.cc.backend.model.entity.Storeup;
import com.cc.backend.model.entity.User;
import com.cc.backend.service.StoreupService;
import com.cc.backend.service.UserService;
import com.cc.backend.utils.ResultUtils;
import com.cc.backend.utils.ThrowUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/storeup")
public class StoreUpController {
    @Resource
    StoreupService storeupService;

    @Resource
    UserService userService;


    /*
    * 新增收藏*/
    @PostMapping("/add")
    @RequireRole
    public BaseResponse<Long> addStoreUp(@RequestParam Long shareId,
                                         HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER);
        Storeup storeup = new Storeup();
        storeup.setShareId(shareId);
        storeup.setUserId(user.getId());
        storeupService.save(storeup);
        return ResultUtils.success(storeup.getId());
    }

    /*
    * 删除收藏*/
    @PostMapping("/delete")
    @RequireRole
    public BaseResponse<String> deleteStoreUp(@RequestParam Long id,
                                              HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER);
        Storeup byId = storeupService.getById(id);
        ThrowUtils.throwIf(
                !user.getId().equals(byId.getUserId()),
                ErrorCode.NO_AUTH_ERROR
        );
        storeupService.removeById(id);
        return ResultUtils.success("ok");
    }

    /*
    * 查询收藏*/
    @RequireRole
    @PostMapping("/my")
    public BaseResponse<Page<Storeup>> getMyStoreUp(@RequestBody PageRequest pageRequest,
                                                    HttpServletRequest request) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        User user = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER);
        QueryWrapper<Storeup> storeupQueryWrapper = new QueryWrapper<>();
        storeupQueryWrapper.eq("userId", user.getId());
        Page<Storeup> storeupPage = storeupService.page(new Page<>(current, size), storeupQueryWrapper);
        return ResultUtils.success(storeupPage);
    }
}
