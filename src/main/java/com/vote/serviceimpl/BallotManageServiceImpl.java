package com.vote.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.BallotManage;
import com.vote.mapper.BallotManageMapper;
import com.vote.service.BallotManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallotManageServiceImpl implements BallotManageService {
    @Autowired
    private BallotManageMapper ballotManageMapper;


    @Override
    public List<BallotManage> findAll() {
        return ballotManageMapper.findAll();
    }

    @Override
    public List<Integer> findAllVoters(Integer voteId) {
        return ballotManageMapper.findAllVoters(voteId);
    }

    @Override
    public IPage<BallotManage> findAll(Page<BallotManage> page) {
        return ballotManageMapper.findAll(page);
    }

    @Override
    public BallotManage findById(Integer ballotId) {
        return ballotManageMapper.findById(ballotId);
    }

    @Override
    public int delete(Integer ballotId) {
        return ballotManageMapper.delete(ballotId);
    }

    @Override
    public int update(BallotManage ballotmanage) {
        ballotmanage.setBallot();
        return ballotManageMapper.update(ballotmanage);
    }

    @Override
    public int checkExists(BallotManage ballotmanage) {
        return ballotManageMapper.checkExists(ballotmanage);
    }

    @Override
    public int add(BallotManage ballotmanage) {
        ballotmanage.setBallot();
        return ballotManageMapper.add(ballotmanage);
    }

    @Override
    public BallotManage findOnlyPaperId() {
        return ballotManageMapper.findOnlyPaperId();
    }
}
