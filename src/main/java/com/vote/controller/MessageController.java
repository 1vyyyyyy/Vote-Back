package com.vote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.ApiResult;
import com.vote.entity.Message;
import com.vote.serviceimpl.MessageServiceImpl;
import com.vote.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("/messages/{page}/{size}")
    public ApiResult<Message> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Page<Message> messagePage = new Page<>(page,size);
        IPage<Message> all = messageService.findAll(messagePage);
        return ApiResultHandler.buildApiResult(200,"查询所有留言",all);
    }

    @GetMapping("/message/{id}")
    public ApiResult findById(@PathVariable("id") Integer id) {
        Message res = messageService.findById(id);
        return ApiResultHandler.buildApiResult(200,"根据Id查询",res);
    }

    @DeleteMapping("/message/{id}")
    public int delete(@PathVariable("id") Integer id) {
        int res = messageService.delete(id);
        return res;
    }

    @PostMapping("/message")
    public ApiResult add(@RequestBody Message message) {
        int res = messageService.add(message);
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400,"添加失败",res);
        } else {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
    }
}
