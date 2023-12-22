package com.vote.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.VoteManage;
import com.vote.mapper.VoteManageMapper;
import com.vote.service.VoteManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteManageServiceImpl implements VoteManageService {
    @Autowired
    private VoteManageMapper voteManageMapper;


    @Override
    public List<VoteManage> findAll() {
        return voteManageMapper.findAll();
    }

    @Override
    public IPage<VoteManage> findAll(Page<VoteManage> page) {
        return voteManageMapper.findAll(page);
    }

    @Override
    public VoteManage findById(Integer voteId) {
        return voteManageMapper.findById(voteId);
    }

    @Override
    public int delete(Integer voteId) {
        return voteManageMapper.delete(voteId);
    }

    @Override
    public int update(VoteManage votemanage) {
        votemanage.setCandidates();
        votemanage.setWeights();
        return voteManageMapper.update(votemanage);
    }

    @Override
    public int add(VoteManage votemanage) {
        votemanage.setCandidates();
        votemanage.setWeights();
        return voteManageMapper.add(votemanage);
    }

    @Override
    public VoteManage findOnlyPaperId() {
        return voteManageMapper.findOnlyPaperId();
    }
}
