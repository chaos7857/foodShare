package com.cc.backend.controller;


import com.cc.backend.model.dto.common.BaseResponse;
import com.cc.backend.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {
    @GetMapping("/health")
    public BaseResponse<String> healthCheck(){
        return ResultUtils.success("OK");
    }
}
