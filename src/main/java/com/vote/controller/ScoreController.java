package com.vote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.ApiResult;
import com.vote.entity.Message;
import com.vote.entity.Score;
import com.vote.serviceimpl.ScoreServiceImpl;
import com.vote.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @GetMapping("/scores")
    public ApiResult findAll() {
        List<Score> res = scoreService.findAll();
        return ApiResultHandler.buildApiResult(200,"查询所有学生成绩",res);
    }
//    分页
    @GetMapping("/score/{page}/{size}/{studentId}")
    public ApiResult findById(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("studentId") Integer studentId) {
        Page<Score> scorePage = new Page<>(page, size);
        IPage<Score> res = scoreService.findById(scorePage, studentId);
        return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
    }

//    不分页
    @GetMapping("/score/{studentId}")
        public ApiResult findById(@PathVariable("studentId") Integer studentId) {
        List<Score> res = scoreService.findById(studentId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "ID不存在", res);
        }
    }

    @PostMapping("/score")
    public ApiResult add(@RequestBody Score score) {
        int res = scoreService.add(score);
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400,"成绩添加失败",res);
        }else {
            return ApiResultHandler.buildApiResult(200,"成绩添加成功",res);
        }
    }

    @GetMapping("/scores/{voteId}")
    public ApiResult findByVoteCode(@PathVariable("voteId") Integer voteId) {
        List<Score> scores = scoreService.findByVoteCode(voteId);
        return ApiResultHandler.buildApiResult(200,"查询成功",scores);
    }
}
