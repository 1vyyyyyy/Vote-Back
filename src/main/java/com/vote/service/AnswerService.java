package com.vote.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.vo.AnswerVO;

public interface AnswerService {

    IPage<AnswerVO> findAll(Page<AnswerVO> page);
}
