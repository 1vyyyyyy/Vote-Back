package com.vote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.ApiResult;
import com.vote.entity.User;
import com.vote.serviceimpl.UserServiceImpl;
import com.vote.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Page<User> userPage = new Page<>(page,size);
        IPage<User> res = userService.findAll(userPage);
        return  ApiResultHandler.buildApiResult(200,"分页查询所有学生",res);
    }

    @GetMapping("/user/{userId}")
    public ApiResult findById(@PathVariable("userId") Integer userId) {
        User res = userService.findById(userId);
        if (res != null) {
        return ApiResultHandler.buildApiResult(200,"请求成功",res);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }

    @DeleteMapping("/user/{userId}")
    public ApiResult deleteById(@PathVariable("userId") Integer userId) {
        return ApiResultHandler.buildApiResult(200,"删除成功",userService.deleteById(userId));
    }

    @PutMapping("/userPWD")
    public ApiResult updatePwd(@RequestBody User user) {
        userService.updatePwd(user);
        return ApiResultHandler.buildApiResult(200,"密码更新成功",null);
    }
    @PutMapping("/user")
    public ApiResult update(@RequestBody User user) {
        int res = userService.update(user);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"更新成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"更新失败",res);
    }

    @PostMapping("/user")
    public ApiResult add(@RequestBody User user) {
        int res = userService.add(user);
        if (res == 1) {
            return ApiResultHandler.buildApiResult(200,"添加成功",null);
        }else {
            return ApiResultHandler.buildApiResult(400,"添加失败",null);
        }
    }
}
