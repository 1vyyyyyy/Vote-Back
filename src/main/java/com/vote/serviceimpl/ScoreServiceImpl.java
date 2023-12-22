package com.vote.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.Score;
import com.vote.mapper.ScoreMapper;
import com.vote.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public int add(Score score) {
        return scoreMapper.add(score);
    }

    @Override
    public List<Score> findAll() {
        return scoreMapper.findAll();
    }

    @Override
    public IPage<Score> findById(Page page, Integer studentId) {
        return scoreMapper.findById(page, studentId);
    }

    @Override
    public List<Score> findById(Integer studentId) {
        return scoreMapper.findById(studentId);
    }

    @Override
    public List<Score> findByVoteCode(Integer voteId) {
        return scoreMapper.findByVoteCode(voteId);
    }
}
