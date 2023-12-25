package com.vote.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.User;
import com.vote.mapper.UserMapper;
import com.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public IPage<User> findAll(Page<User> page) {
        return userMapper.findAll(page);
    }

    @Override
    public int findUserType(Integer userId) {
        return userMapper.findUserType(userId);
    }

    @Override
    public User findById(Integer userId) {
        return userMapper.findById(userId);
    }

    @Override
    public int deleteById(Integer userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int updatePwd(User user) {
        return userMapper.updatePwd(user);
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }
}
