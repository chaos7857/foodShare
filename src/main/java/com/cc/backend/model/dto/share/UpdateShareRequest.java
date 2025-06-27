package com.cc.backend.model.dto.share;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class UpdateShareRequest implements Serializable {
    private Long id;
    private String shareTitle;
    private String shareDetail;
    private List<String> shareTags;
}
