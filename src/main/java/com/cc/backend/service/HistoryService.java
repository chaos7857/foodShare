package com.cc.backend.service;

import com.cc.backend.model.entity.History;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Admin
* @description 针对表【history】的数据库操作Service
* @createDate 2025-06-28 14:53:14
*/
public interface HistoryService extends IService<History> {
    public Long addHistory(Long userId, Long shareId);
}
