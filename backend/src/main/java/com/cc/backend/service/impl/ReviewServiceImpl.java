package com.cc.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.backend.model.entity.Review;
import com.cc.backend.service.ReviewService;
import com.cc.backend.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【review】的数据库操作Service实现
* @createDate 2025-06-27 13:15:35
*/
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review>
    implements ReviewService{

}




