package com.vote.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 分页查询所有学生
     * @param page
     * @return List<User>
     */
    @Select("select * from user")
    IPage<User> findAll(Page page);

    @Select("select * from user where userId = #{userId}")
    User findById(Integer userId);

    @Delete("delete from user where userId = #{userId}")
    int deleteById(Integer userId);

    /**
     *更新所有学生信息
     * @param user 传递一个对象
     * @return 受影响的记录条数
     */
    @Update("update user set userType = #{userType} " +
            "where userId = #{userId}")
    int update(User user);

    /**
     * 更新密码
     * @param user
     * @return 受影响的记录条数
     */
    @Update("update user set pwd = #{pwd} where userId = #{userId}")
    int updatePwd(User user);


    @Options(useGeneratedKeys = true,keyProperty = "userId")
    @Insert("insert into user(userName,grade,major,clazz,institute,tel,email,pwd,cardId,sex,role) values " +
            "(#{userName},#{grade},#{major},#{clazz},#{institute},#{tel},#{email},#{pwd},#{cardId},#{sex},#{role})")
    int add(User user);
}
