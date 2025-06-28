package com.cc.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.backend.model.entity.History;
import com.cc.backend.service.HistoryService;
import com.cc.backend.mapper.HistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【history】的数据库操作Service实现
* @createDate 2025-06-28 14:53:14
*/
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History>
    implements HistoryService{

    @Override
    public Long addHistory(Long userId, Long shareId) {
        History history = new History();
        history.setUserId(userId);
        history.setShareId(shareId);
        this.saveOrUpdate(history);
        return history.getId();
    }
}




