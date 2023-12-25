package com.vote.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.vote.entity.*;

import com.vote.serviceimpl.VoteManageServiceImpl;
import com.vote.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println(votemanage.getVoteDate());
        System.out.println(votemanage.getEndDate());
        if (res == 1) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        } else {
            return  ApiResultHandler.buildApiResult(400,"添加失败",res);
        }
    }

    @GetMapping("/voteCalc/{voteId}")
    public ApiResult calc(@PathVariable("voteId") Integer voteId){
        System.out.println("根据ID结算结果");
        List<BallotManage> allBallots = VoteManageService.findAllBallots(voteId);

        System.out.println("所有选票:"+allBallots);
        List<Integer> allVoters = allBallots.stream()
                .map(BallotManage::getUserId)
                .collect(Collectors.toList());
        System.out.println("所有投票人:"+allVoters);

        List<Integer> allVoterTypes = allVoters.stream()
                .map(VoteManageService::findUserType)
                .collect(Collectors.toList());
        System.out.println("所有投票人类型:"+allVoterTypes);

        List<Integer> voteWeights = Arrays.asList(VoteManageService.findById(voteId).getWeight0(),VoteManageService.findById(voteId).getWeight1(),VoteManageService.findById(voteId).getWeight2());
        System.out.println("投票权重:"+voteWeights);

        List<Integer> allVoterWeights = allVoterTypes.stream()
                .map(voteWeights::get)
                .collect(Collectors.toList());
        System.out.println("所有投票人权重:"+allVoterWeights);

        List<String> allVoterBallotsStr = new ArrayList<>();
        for(Integer voterId: allVoters) {
            System.out.println("voterId:"+voterId);
            allVoterBallotsStr.add(VoteManageService.findSingleBallot(voteId,voterId));
        }
        System.out.println("所有String类型的选票:"+allVoterBallotsStr);
        List<List<Integer>> checkedValues = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (int index = 0;index < allVoterBallotsStr.size();index++) {
            try {
                List<Integer> userValues = new ArrayList<>();
                Integer userWeight = allVoterWeights.get(index);
                List<BallotItem> ballotList = objectMapper.readValue(allVoterBallotsStr.get(index), new TypeReference<List<BallotItem>>() {
                });
                for (BallotItem item : ballotList) {
                    System.out.println("Name: " + item.getName());
                    System.out.println("Description: " + item.getDescription());
                    System.out.println("Checked: " + item.isChecked());
                    System.out.println("----");
                    userValues.add(userWeight * (item.isChecked() ? 1:0));
                }
                checkedValues.add(userValues);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Print the extracted checked values
//        for (List<Integer> row : checkedValues) {
//            System.out.println(row);
//        }
        List<Integer> voteRes = new ArrayList<>();
        for(int i=0;i < checkedValues.get(0).size();i++) {
            Integer sumRow = 0;
            for (List<Integer> checkedValue : checkedValues) {
                sumRow += checkedValue.get(i);
            }
            voteRes.add(sumRow);
        }
        System.out.println(voteRes);
        String result = voteRes.toString();
        int res = VoteManageService.calc(voteId,result);
        if(res != 0) {
            return ApiResultHandler.buildApiResult(200,"结算成功",null);
        }
        return ApiResultHandler.buildApiResult(400,"结算失败",res);
    }

    @GetMapping("/voteManagePaperId")
    public ApiResult findOnlyPaperId() {
        VoteManage res = VoteManageService.findOnlyPaperId();
        if (res != null) {
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"请求失败",res);
    }

    public static List<List<Boolean>> extractCheckedValues(List<BallotManage> allBallots) {
        List<List<Boolean>> result = new ArrayList<>();

        for (BallotManage ballotManage : allBallots) {
            List<Boolean> checkedList = new ArrayList<>();
//            System.out.println(ballotManage);
            for (BallotItem ballotItem : ballotManage.getBallotItemList()) {
                System.out.println('2');
                checkedList.add(ballotItem.isChecked());
            }

            result.add(checkedList);
        }

        return result;
    }
}
