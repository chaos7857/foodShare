package com.cc.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.backend.model.entity.User;
import com.cc.backend.model.vo.LoginUserVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author Admin
* @description 针对表【user】的数据库操作Service
* @createDate 2025-06-22 00:32:54
*/
public interface UserService extends IService<User> {
    public Long userRegister(String username, String password, String confirmPassword);
    public User userLogin(String username, String password, HttpServletRequest request);
    public User getLoginUser(HttpServletRequest request);
    public String userLogout(HttpServletRequest request);

    public Long addUser(String userAccount, String userName);

    LoginUserVO getLoginUserVO(User user);
}
