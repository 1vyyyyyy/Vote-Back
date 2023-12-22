package com.vote.controller;

import com.vote.entity.*;
import com.vote.serviceimpl.RegisterServiceImpl;
import com.vote.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
public class RegisterController {

    @Autowired
    private RegisterServiceImpl registerService;

    @PostMapping("/register")
    public ApiResult register(@RequestBody Register register) {

        int registerRes = registerService.add(register);
        if (registerRes != 0) {
            return ApiResultHandler.buildApiResult(200, "请求成功", registerRes);
        }

        return ApiResultHandler.buildApiResult(400, "请求失败", null);

    }
}

