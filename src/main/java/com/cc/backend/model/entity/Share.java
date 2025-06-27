package com.cc.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName share
 */
@TableName(value ="share")
@Data
public class Share implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String shareTitle;

    /**
     * 
     */
    private String shareDetail;

    /**
     * 
     */
    private String sharePicture;

    /**
     * 
     */
    private String shareTags;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private Long shareClickNum;

    /**
     * 
     */
    private Long shareLike;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    /**
     * 
     */
    private Integer isDelete;

    /**
     * 
     */
    private String reviewId;

    /**
     * 
     */
    private Integer lastReviewStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Share other = (Share) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getShareTitle() == null ? other.getShareTitle() == null : this.getShareTitle().equals(other.getShareTitle()))
            && (this.getShareDetail() == null ? other.getShareDetail() == null : this.getShareDetail().equals(other.getShareDetail()))
            && (this.getSharePicture() == null ? other.getSharePicture() == null : this.getSharePicture().equals(other.getSharePicture()))
            && (this.getShareTags() == null ? other.getShareTags() == null : this.getShareTags().equals(other.getShareTags()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getShareClickNum() == null ? other.getShareClickNum() == null : this.getShareClickNum().equals(other.getShareClickNum()))
            && (this.getShareLike() == null ? other.getShareLike() == null : this.getShareLike().equals(other.getShareLike()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getReviewId() == null ? other.getReviewId() == null : this.getReviewId().equals(other.getReviewId()))
            && (this.getLastReviewStatus() == null ? other.getLastReviewStatus() == null : this.getLastReviewStatus().equals(other.getLastReviewStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getShareTitle() == null) ? 0 : getShareTitle().hashCode());
        result = prime * result + ((getShareDetail() == null) ? 0 : getShareDetail().hashCode());
        result = prime * result + ((getSharePicture() == null) ? 0 : getSharePicture().hashCode());
        result = prime * result + ((getShareTags() == null) ? 0 : getShareTags().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getShareClickNum() == null) ? 0 : getShareClickNum().hashCode());
        result = prime * result + ((getShareLike() == null) ? 0 : getShareLike().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getReviewId() == null) ? 0 : getReviewId().hashCode());
        result = prime * result + ((getLastReviewStatus() == null) ? 0 : getLastReviewStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shareTitle=").append(shareTitle);
        sb.append(", shareDetail=").append(shareDetail);
        sb.append(", sharePicture=").append(sharePicture);
        sb.append(", shareTags=").append(shareTags);
        sb.append(", userId=").append(userId);
        sb.append(", shareClickNum=").append(shareClickNum);
        sb.append(", shareLike=").append(shareLike);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", reviewId=").append(reviewId);
        sb.append(", lastReviewStatus=").append(lastReviewStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}