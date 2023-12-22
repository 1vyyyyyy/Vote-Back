package com.vote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.ApiResult;
import com.vote.entity.VoteManage;
import com.vote.serviceimpl.VoteManageServiceImpl;
import com.vote.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteManageController {

    @Autowired
    private VoteManageServiceImpl VoteManageService;

    @GetMapping("/votes")
    public ApiResult findAll(){
        System.out.println("不分页查询所有投票");
        ApiResult apiResult;
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", VoteManageService.findAll());
        return apiResult;
    }

    @GetMapping("/votes/{page}/{size}")
    public ApiResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        System.out.println("分页查询所有投票");
        ApiResult apiResult;
        Page<VoteManage> voteManage = new Page<>(page,size);
        IPage<VoteManage> all = VoteManageService.findAll(voteManage);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/vote/{voteId}")
    public ApiResult findById(@PathVariable("voteId") Integer voteId){
        System.out.println("根据ID查找");
        VoteManage res = VoteManageService.findById(voteId);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"投票编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @DeleteMapping("/vote/{voteId}")
    public ApiResult deleteById(@PathVariable("voteId") Integer voteId){
        int res = VoteManageService.delete(voteId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @PutMapping("/vote")
    public ApiResult update(@RequestBody VoteManage votemanage){
        System.out.println("更新操作执行");
        int res = VoteManageService.update(votemanage);
//        if (res == 0) {
//            return ApiResultHandler.buildApiResult(20000,"请求参数错误");
//        }
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @PostMapping("/vote")
    public ApiResult add(@RequestBody VoteManage votemanage){
        int res = VoteManageService.add(votemanage);
        if (res == 1) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        } else {
            return  ApiResultHandler.buildApiResult(400,"添加失败",res);
        }
    }

    @GetMapping("/voteManagePaperId")
    public ApiResult findOnlyPaperId() {
        VoteManage res = VoteManageService.findOnlyPaperId();
        if (res != null) {
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"请求失败",res);
    }
}
