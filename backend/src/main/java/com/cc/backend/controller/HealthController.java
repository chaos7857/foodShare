package com.cc.backend.controller;


import com.cc.backend.annotation.RequireRole;
import com.cc.backend.constant.UserConstant;
import com.cc.backend.model.dto.common.BaseResponse;
import com.cc.backend.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {
    /*检测模块*/

    @GetMapping("/health")
    public BaseResponse<String> healthCheck(){
        return ResultUtils.success("OK");
    }

    @RequireRole()
    @GetMapping("/health/user")
    public BaseResponse<String> userHealthCheck(){
        return ResultUtils.success("OK");
    }

    @RequireRole(userRole = UserConstant.DEV_ROLE)
    @GetMapping("/health/dev")
    public BaseResponse<String> devHealthCheck(){
        return ResultUtils.success("OK");
    }

    @RequireRole(userRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/health/admin")
    public BaseResponse<String> adminHealthCheck(){
        return ResultUtils.success("OK");
    }
}
