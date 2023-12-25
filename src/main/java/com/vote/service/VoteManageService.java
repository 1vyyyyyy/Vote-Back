package com.vote.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.BallotManage;
import com.vote.entity.VoteManage;

import java.util.List;

public interface VoteManageService {

    /**
     * 不分页查询所有考试信息
     */
    List<VoteManage> findAll();

    IPage<VoteManage> findAll(Page<VoteManage> page);

    String findSingleBallot(Integer voteId, Integer userId);

    VoteManage findById(Integer voteId);

    int delete(Integer voteId);

    int update(VoteManage votemanage);

    int add(VoteManage votemanage);

    int calc(Integer voteId, String voteRes);

    int findUserType(Integer userId);

    List<BallotManage> findAllBallots(Integer voteId);

    VoteManage findOnlyPaperId();
}
