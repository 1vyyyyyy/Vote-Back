package com.vote.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.VoteManage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VoteManageMapper {
    @Select("select * from votes")
    List<VoteManage> findAll();

    @Select("select * from votes")
    IPage<VoteManage> findAll(Page page);

    @Select("select * from votes where voteId = #{voteId}")
    VoteManage findById(Integer voteId);

    @Delete("delete from votes where voteId = #{voteId}")
    int delete(Integer voteId);

    @Update("update votes set voteName = #{voteName},voteDescription = #{voteDescription}," +
            "voteDate = #{voteDate},endDate = #{endDate},candidates = #{candidatesStr}," +
            "enableWeight = #{enableWeight},weights = #{weightsStr} where voteId = #{voteId}")
    int update(VoteManage votemanage);

//    @Options(useGeneratedKeys = true,keyProperty = "voteId")
    @Insert("insert into votes(voteName,voteDescription,voteDate,endDate,candidates,enableWeight,weights)" +
            " values(#{voteName},#{voteDescription},#{voteDate},#{endDate},#{candidatesStr},#{enableWeight},#{weightsStr})")
    int add(VoteManage votemanage);

    /**
     * 查询最后一条记录的paperId,返回给前端达到自增效果
     * @return paperId
     */
    @Select("select paperId from votes order by paperId desc limit 1")
    VoteManage findOnlyPaperId();
}
