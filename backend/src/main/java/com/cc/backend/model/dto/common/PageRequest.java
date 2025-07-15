package com.cc.backend.model.dto.common;

import lombok.Data;

import java.io.Serializable;
@Data
public class PageRequest implements Serializable {
    private int current = 1;
    private int pageSize = 10;
    private String sortField;
    private String sortOrder = "descend";
}
