package com.cc.backend.controller;

import com.cc.backend.model.dto.BaseResponse;
import com.cc.backend.model.vo.LoginUserVO;
import com.cc.backend.service.UserService;
import com.cc.backend.utils.ResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    /*用户模块*/
    @Resource
    private UserService userService;

    /*
    * 注册*/
    @PostMapping("/register")
    public BaseResponse<Long> userRegister() {
        return ResultUtils.success(666L);
    }

    /*
    * 登录*/
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin() {
        return null;
    }

    /*
    * 个人信息查询*/

    /*
    * 个人信息修改*/

    /*
    * 退出登录*/

    // 管理员权限---------------------------------------------------------------------

    /*
    * 批量添加用户*/

    /*
    * 删除用户（）*/
}
