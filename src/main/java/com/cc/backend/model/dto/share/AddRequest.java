package com.cc.backend.model.dto.share;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddRequest implements Serializable {
    private String title;
    private String detail;
    private List<String> tags;
}
