package com.vote.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.BallotManage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BallotManageMapper {
    @Select("select * from ballots")
    List<BallotManage> findAll();

    @Select("select userId from ballots where voteId = #{voteId}")
    List<Integer> findAllVoters(Integer voteId);

    @Select("select * from ballots")
    IPage<BallotManage> findAll(Page page);

    @Select("select * from ballots where ballotId = #{ballotId}")
    BallotManage findById(Integer ballotId);

    @Delete("delete from ballots where ballotId = #{ballotId}")
    int delete(Integer ballotId);

    @Update("update ballots set ballotName = #{ballotName},ballotDescription = #{ballotDescription}," +
            "ballotDate = #{ballotDate},endDate = #{endDate},candidates = #{candidatesStr}," +
            "enableWeight = #{enableWeight},weights = #{weightsStr} where ballotId = #{ballotId}")
    int update(BallotManage ballotmanage);

    @Select("select count(*) from ballots where userId = #{userId} and voteId = #{voteId}")
    int checkExists(BallotManage ballotmanage);

    //    @Options(useGeneratedKeys = true,keyProperty = "ballotId")
    @Insert("insert into ballots(ballotDate,voteId,userId,ballot)" +
            " values(#{ballotDate},#{voteId},#{userId},#{ballotStr})")
    int add(BallotManage ballotmanage);

    /**
     * 查询最后一条记录的paperId,返回给前端达到自增效果
     * @return paperId
     */
    @Select("select paperId from ballots order by paperId desc limit 1")
    BallotManage findOnlyPaperId();
}
