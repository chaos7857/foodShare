package com.cc.backend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAddRequest implements Serializable {
    public String userAccount;
    public String userName;
}
