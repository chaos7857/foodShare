package com.cc.backend.controller;

import com.cc.backend.exception.ErrorCode;
import com.cc.backend.model.dto.common.BaseResponse;
import com.cc.backend.model.dto.user.LoginRequest;
import com.cc.backend.model.dto.user.RegisterRequest;
import com.cc.backend.model.entity.User;
import com.cc.backend.model.vo.LoginUserVO;
import com.cc.backend.service.UserService;
import com.cc.backend.utils.ResultUtils;
import com.cc.backend.utils.ThrowUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    /*用户模块*/
    @Resource
    private UserService userService;

    /*
    * 注册*/
    public BaseResponse<Long> userRegister(@RequestBody RegisterRequest registerRequest) {
        ThrowUtils.throwIf(registerRequest==null, ErrorCode.PARAMS_ERROR);
        String userAccount = registerRequest.getUserAccount();
        String userPassword = registerRequest.getUserPassword();
        String passwordConfirm = registerRequest.getPasswordConfirm();
        Long result = userService.userRegister(userAccount, userPassword, passwordConfirm);
        return ResultUtils.success(result);
    }

    /*
    * 登录*/
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(loginRequest==null, ErrorCode.PARAMS_ERROR);
        String userAccount = loginRequest.getUserAccount();
        String userPassword = loginRequest.getPassword();
        User user = userService.userLogin(userAccount, userPassword, request);
        LoginUserVO loginUserVO = userService.getLoginUserVO(user);
        return ResultUtils.success(loginUserVO);
    }

    /*
    * 个人信息查询*/
    @GetMapping("/me")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest httpServletRequest){
        User user = userService.getLoginUser(httpServletRequest);
        LoginUserVO loginUserVO = userService.getLoginUserVO(user);
        return ResultUtils.success(loginUserVO);
    }

    /*
    * 个人信息修改*/

    /*
    * 退出登录*/
    @GetMapping("/logout")
    public BaseResponse<String> userLogout(HttpServletRequest httpServletRequest){
        String result = userService.userLogout(httpServletRequest);
        return ResultUtils.success(result);
    }

    // 管理员权限---------------------------------------------------------------------

    /*
    * 批量添加用户*/

    /*
    * 查看用户列表*/

    /*
    * 删除用户（）*/
}
