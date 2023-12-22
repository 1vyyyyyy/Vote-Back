package com.vote.mapper;

import com.vote.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginMapper {


    @Select("select userId,userName from user " +
            "where userName = #{username} and pwd = #{password}")
    public User userLogin(String username, String password);
}
