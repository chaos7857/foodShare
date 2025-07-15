package com.cc.backend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {
    public String userAccount;
    public String userPassword;
    public String passwordConfirm;
//    public String email;
}
