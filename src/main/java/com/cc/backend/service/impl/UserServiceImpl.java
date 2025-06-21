package com.cc.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.backend.model.entity.User;
import com.cc.backend.service.UserService;
import com.cc.backend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-06-22 00:32:54
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




