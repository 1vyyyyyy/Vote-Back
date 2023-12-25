package com.vote.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.BallotManage;

import java.util.List;

public interface BallotManageService {

    /**
     * 不分页查询所有考试信息
     */
    List<BallotManage> findAll();

    List<Integer> findAllVoters(Integer voteId);

    IPage<BallotManage> findAll(Page<BallotManage> page);

    BallotManage findById(Integer ballotId);

    int delete(Integer ballotId);

    int update(BallotManage ballotmanage);

    int checkExists(BallotManage ballotmanage);

    int add(BallotManage ballotmanage);

    BallotManage findOnlyPaperId();
}

