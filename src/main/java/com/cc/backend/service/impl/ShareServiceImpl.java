package com.cc.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.backend.model.entity.Share;
import com.cc.backend.service.ShareService;
import com.cc.backend.mapper.ShareMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【share】的数据库操作Service实现
* @createDate 2025-06-23 22:24:19
*/
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share>
    implements ShareService{

}




