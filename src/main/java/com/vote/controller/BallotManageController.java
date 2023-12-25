package com.vote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.ApiResult;
import com.vote.entity.BallotManage;
import com.vote.serviceimpl.BallotManageServiceImpl;
import com.vote.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BallotManageController {

    @Autowired
    private BallotManageServiceImpl BallotManageService;

    @GetMapping("/ballots")
    public ApiResult findAll(){
        System.out.println("不分页查询所有投票");
        ApiResult apiResult;
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", BallotManageService.findAll());
        return apiResult;
    }

    @GetMapping("/ballots/{page}/{size}")
    public ApiResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        System.out.println("分页查询所有投票");
        ApiResult apiResult;
        Page<BallotManage> ballotManage = new Page<>(page,size);
        IPage<BallotManage> all = BallotManageService.findAll(ballotManage);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/ballot/{ballotId}")
    public ApiResult findById(@PathVariable("ballotId") Integer ballotId){
        System.out.println("根据ID查找");
        BallotManage res = BallotManageService.findById(ballotId);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"投票编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @DeleteMapping("/ballot/{ballotId}")
    public ApiResult deleteById(@PathVariable("ballotId") Integer ballotId){
        int res = BallotManageService.delete(ballotId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @PutMapping("/ballot")
    public ApiResult update(@RequestBody BallotManage ballotmanage){
        System.out.println("更新操作执行");
        int res = BallotManageService.update(ballotmanage);
//        if (res == 0) {
//            return ApiResultHandler.buildApiResult(20000,"请求参数错误");
//        }
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @PostMapping("/ballot")
    public ApiResult add(@RequestBody BallotManage ballotmanage){
        int check = BallotManageService.checkExists(ballotmanage);
        if (check == 1) {
            return  ApiResultHandler.buildApiResult(403,"重复投票",check);
        } else {
            int res = BallotManageService.add(ballotmanage);
            if (res == 1) {
                return ApiResultHandler.buildApiResult(200, "添加成功", res);
            } else {
                return  ApiResultHandler.buildApiResult(400,"添加失败",res);
            }
        }

    }

    @GetMapping("/ballotManagePaperId")
    public ApiResult findOnlyPaperId() {
        BallotManage res = BallotManageService.findOnlyPaperId();
        if (res != null) {
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"请求失败",res);
    }
}

