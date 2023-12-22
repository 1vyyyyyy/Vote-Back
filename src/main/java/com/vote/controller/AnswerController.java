package com.vote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.ApiResult;
import com.vote.serviceimpl.AnswerServiceImpl;
import com.vote.util.ApiResultHandler;
import com.vote.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AnswerController {

    @Autowired
    private AnswerServiceImpl answerService;

    @GetMapping("/answers/{page}/{size}")
    public ApiResult findAllQuestion(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
       Page<AnswerVO> answerVOPage = new Page<>(page,size);
       IPage<AnswerVO> answerVOIPage = answerService.findAll(answerVOPage);
       return ApiResultHandler.buildApiResult(200,"查询所有题库",answerVOIPage);

    }
}
