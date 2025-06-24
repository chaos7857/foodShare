package com.cc.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.backend.model.dto.share.AddRequest;
import com.cc.backend.model.entity.Share;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
* @author Admin
* @description 针对表【share】的数据库操作Service
* @createDate 2025-06-23 22:24:19
*/
public interface ShareService extends IService<Share> {
    public Long addShare(AddRequest addRequest, MultipartFile picture, Long userId) throws FileNotFoundException;

    public void deleteShare(Long shareId, Long userId, String userRole);
}
