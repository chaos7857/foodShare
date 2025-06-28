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
import com.cc.backend.utils.ResultUtils;
import com.cc.backend.utils.ThrowUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/storeup")
public class StoreUpController {
    /*收藏模块*/

    @Resource
    StoreupService storeupService;

    /*
    * 新增收藏*/
    @PostMapping("/add")
    @RequireRole
    public BaseResponse<Long> addStoreUp(@RequestParam Long shareId,
                                         HttpServletRequest request) {
        ThrowUtils.throwIf(shareId == null,
                ErrorCode.PARAMS_ERROR);
        // 获取登录用户信息
        User user = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER);
        // 保存收藏信息
        Storeup storeup = new Storeup();
        storeup.setShareId(shareId);
        storeup.setUserId(user.getId());
        storeupService.save(storeup);
        // 返回收藏id
        return ResultUtils.success(storeup.getId());
    }

    /*
    * 删除收藏*/
    @PostMapping("/delete")
    @RequireRole
    public BaseResponse<String> deleteStoreUp(@RequestParam Long id,
                                              HttpServletRequest request){
        ThrowUtils.throwIf(id == null,ErrorCode.PARAMS_ERROR);
        // 获取当前用户信息
        User user = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER);
        // 查找收藏信息
        Storeup old = storeupService.getById(id);
        // 如果没找到或者不是自己的就抛出异常
        // 管理员也不能操作他人的收藏！！！
        ThrowUtils.throwIf(
                old == null || !user.getId().equals(old.getUserId()),
                ErrorCode.NO_AUTH_ERROR
        );
        // 删除
        boolean res = storeupService.removeById(id);
        ThrowUtils.throwIf(!res, ErrorCode.SYSTEM_ERROR);
        // 返回操作结果
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
        // 获取当前用户信息
        User user = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER);
        // 按userId查找收藏
        QueryWrapper<Storeup> storeupQueryWrapper = new QueryWrapper<>();
        storeupQueryWrapper.eq("userId", user.getId());
        Page<Storeup> storeupPage = storeupService.page(new Page<>(current, size), storeupQueryWrapper);
        // 返回结果
        return ResultUtils.success(storeupPage);
    }
}
