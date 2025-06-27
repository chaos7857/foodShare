package com.cc.backend.model.dto.review;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddReviewRequest implements Serializable {
    private Long shareId;
    private String reviewStatus;
    private String reviewMessage;
}
