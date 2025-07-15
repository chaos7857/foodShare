package com.cc.backend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class ShareVO implements Serializable {
    private Long id;
    private String shareTitle;
    private String shareDetail;
    private String sharePicture;
    private List<String> shareTags;
    private Long userId;
    private Long shareClickNum;
    private Long shareLike;
    private Integer lastReviewStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
