package com.cc.backend.utils;


import com.cc.backend.exception.ErrorCode;
import com.cc.backend.model.dto.common.BaseResponse;

public class ResultUtils {
    /*
    * 成功*/
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0,"ok",data);
    }

    /*
    * 失败*/
    public static  BaseResponse<?> error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

    /*
    * 通用错误码，自定义细化错误*/
    public static BaseResponse<?> error(ErrorCode errorCode,String msg){
        return new BaseResponse<>(errorCode.getCode(),errorCode.getMsg(),msg);
    }

    /*
    * 自定义错误码，自定义错误消息*/
    public static BaseResponse<?> error(int code,String msg){
        return new BaseResponse<>(code,msg,null);
    }
}
