package com.cc.backend.utils;


import com.cc.backend.exception.BusinessException;
import com.cc.backend.exception.ErrorCode;

public class ThrowUtils{
    public static void throwIf(boolean condition,RuntimeException e){
        if(condition){
            throw e;
        }
    }

    public static void throwIf(boolean condition, ErrorCode errorCode){
        throwIf(condition,new BusinessException(errorCode.getCode(), errorCode.getMsg()));
    }

    public static void throwIf(boolean condition,ErrorCode errorCode,String message){
        throwIf(condition,new BusinessException(errorCode.getCode(), message));
    }
}
