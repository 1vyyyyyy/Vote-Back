package com.vote.serviceimpl;

import com.vote.entity.User;
import com.vote.mapper.LoginMapper;
import com.vote.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User userLogin(String username, String password) {
        return loginMapper.userLogin(username,password);
    }

}
