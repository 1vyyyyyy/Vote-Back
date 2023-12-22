package com.vote.entity;

import lombok.Data;

@Data
public class User {
    private Integer userId;

    private String userName;

    private String pwd;

    private Integer userType;
}