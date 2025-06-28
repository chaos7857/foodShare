package com.cc.backend.model.dto.share;

import com.cc.backend.model.dto.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryByRSRequest extends PageRequest implements Serializable {
    private int reviewStatus;
}
