package com.vote.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.VoteManage;

import java.util.List;

public interface VoteManageService {

    /**
     * 不分页查询所有考试信息
     */
    List<VoteManage> findAll();
    IPage<VoteManage> findAll(Page<VoteManage> page);

    VoteManage findById(Integer voteId);

    int delete(Integer voteId);

    int update(VoteManage votemanage);

    int add(VoteManage votemanage);

    VoteManage findOnlyPaperId();
}
