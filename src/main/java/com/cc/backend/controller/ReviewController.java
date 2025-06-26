package com.cc.backend.controller;

import com.cc.backend.service.ReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Resource
    private ReviewService reviewService;

    /// /////////////////////////////////////////
    ///             管理员权限                 ///
    /// ////////////////////////////////////////

    /*
    * 对未审核数据进行审核*/


    /*
    * 删除审核记录（这个不太合理）*/


    /*
    * 更新审核（这里通过新增一条审核来更新share的审核状态）*/


    /*
    * 查询所有未审核*/



    /*
    * 查询某个share的所有审核信息*/



    /// /////////////////////////////////////////
    ///             开发者权限                 ///
    /// ////////////////////////////////////////
}
