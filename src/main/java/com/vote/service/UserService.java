package com.vote.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.User;

import java.util.List;

public interface UserService {

    IPage<User> findAll(Page<User> page);

    int findUserType(Integer userId);

    User findById(Integer userId);

    int deleteById(Integer userId);

    int update(User user);

    int updatePwd(User user);
    int add(User user);
}
