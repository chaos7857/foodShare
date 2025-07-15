package com.cc.backend.model.enums.review;

import lombok.Getter;

@Getter
public enum ReviewStatus {
    AGREE(1, "通过"),
    REJECT(2, "未通过"),;


    public final int reviewStatus;
    public final String zh;

    ReviewStatus(int reviewStatus, String zh) {
        this.reviewStatus = reviewStatus;
        this.zh = zh;
    }

    public static String getZhByStatus(int reviewStatus){
        for(ReviewStatus rs : ReviewStatus.values()){
            if(rs.getReviewStatus()==reviewStatus){
                return rs.zh;
            }
        }
        return null;
    }
}
