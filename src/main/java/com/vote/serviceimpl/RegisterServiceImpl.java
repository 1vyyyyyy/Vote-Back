package com.vote.serviceimpl;

import com.vote.entity.Register;
import com.vote.mapper.RegisterMapper;
import com.vote.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public int add(Register register) {
        return registerMapper.add(register);
    }
}

