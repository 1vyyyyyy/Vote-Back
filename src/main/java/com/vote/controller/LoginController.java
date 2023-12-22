package com.vote.controller;

import com.vote.entity.*;
import com.vote.serviceimpl.LoginServiceImpl;
import com.vote.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody Login login) {

        String username = login.getUsername();
        String password = login.getPassword();


        User userRes = loginService.userLogin(username,password);
        if (userRes != null) {
                return ApiResultHandler.buildApiResult(200, "请求成功", userRes);
        }

        return ApiResultHandler.buildApiResult(400, "请求失败", null);

    }
}
