package com.vote.mapper;


import com.vote.entity.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface RegisterMapper {

    // return值是受影响的记录条数

    @Insert("insert into user(userName,pwd) values (#{username},#{password})")
    int add(Register register);
}

