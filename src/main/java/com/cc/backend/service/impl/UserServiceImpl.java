package com.cc.backend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.backend.constant.UserConstant;
import com.cc.backend.exception.ErrorCode;
import com.cc.backend.mapper.UserMapper;
import com.cc.backend.model.entity.User;
import com.cc.backend.model.vo.LoginUserVO;
import com.cc.backend.service.UserService;
import com.cc.backend.utils.ThrowUtils;
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
    public Long userRegister(String userAccount, String userPassword, String confirmPassword) {
        // 参数检验
        ThrowUtils.throwIf(
                StrUtil.hasBlank(userAccount, userPassword, confirmPassword),
                ErrorCode.PARAMS_ERROR,"参数为空"
        );
        ThrowUtils.throwIf(
                userAccount.length()<4,
                ErrorCode.PARAMS_ERROR,"账号过短"
        );
        ThrowUtils.throwIf(
                userPassword.length()<8,
                ErrorCode.PARAMS_ERROR,"密码强调过低"
        );
        ThrowUtils.throwIf(
                !userPassword.equals(confirmPassword),
                ErrorCode.PARAMS_ERROR,"两次输入的密码不一致"
        );
        // 确保userAccount不存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        Long count = this.baseMapper.selectCount(userQueryWrapper);
        ThrowUtils.throwIf(
                count>0,
                ErrorCode.PARAMS_ERROR,"账户已存在"
        );
        // 密码加密
        String encodedPassword = this.getEncodedPassword(userPassword);
        // 插入数据库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encodedPassword);
        user.setUserName(UserConstant.USER_NAME);
        user.setUserRole(UserConstant.ROLE_USER);
        boolean saveRes = this.save(user);
        ThrowUtils.throwIf(
                !saveRes,
                ErrorCode.SYSTEM_ERROR,"注册失败，数据库错误"
        );
        // 返回userId
        return user.getId();
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
    @Override
    public String getEncodedPassword(String password) {
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




