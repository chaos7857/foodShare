package com.cc.backend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.backend.model.entity.User;
import com.cc.backend.model.vo.LoginUserVO;
import com.cc.backend.service.UserService;
import com.cc.backend.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

/**
* @author Admin
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-06-22 00:32:54
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Long userRegister(String username, String password, String confirmPassword) {
        // 参数检验
        // 确保userAccount不存在
        // 密码加密
        // 插入数据库
        // 返回userId
        return 0L;
    }

    @Override
    public User userLogin(String username, String password, HttpServletRequest request) {
        // 参数检验
        // 密码转为加密
        // 查询
        // 不存在则抛出异常，存在保存登录状态
        // 返回实体类（需要手动脱敏）
        return null;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 状态检验
        // 查询数据库
        // 返回实体类（需要手动脱敏）
        return null;
    }

    @Override
    public String userLogout(HttpServletRequest request) {
        // 状态检验
        // 移除登录态
        // 返回成功
        return "";
    }

    @Override
    public Long addUser(String userAccount, String userName) {
        // 参数检验（此处应该为列表）
        // 遍历每个执行userRegister，默认将密码设置为12345678
        // 返回userId（此处应该为列表）
        return 0L;
    }

    /*
    * 密码加密*/
    public static String getEncodedPassword(String password) {
        final String SALT = "hfutdevcc";
        return DigestUtils.md5DigestAsHex((SALT + password).getBytes());
    }

    /*
    * 用户数据脱敏（移除密码等信息）*/
    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(user, loginUserVO);
        return loginUserVO;
    }
}




