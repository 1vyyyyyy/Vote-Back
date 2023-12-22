package com.vote.service;

import com.vote.entity.User;

public interface LoginService {

    public User userLogin(String username, String password);
}
