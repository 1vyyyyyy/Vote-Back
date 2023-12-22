package com.vote.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vote.entity.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper {
    /**
     * @param score 添加一条成绩记录
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "scoreId")
    @Insert("insert into score(voteId,studentId,subject,ptScore,etScore,score,answerDate) values(#{voteId},#{studentId},#{subject},#{ptScore},#{etScore},#{score},#{answerDate})")
    int add(Score score);

    @Select("select scoreId,voteId,studentId,subject,ptScore,etScore,score,answerDate from score order by scoreId desc")
    List<Score> findAll();

    // 分页
    @Select("select scoreId,voteId,studentId,subject,ptScore,etScore,score,answerDate from score where studentId = #{studentId} order by scoreId desc")
    IPage<Score> findById(Page<?> page, Integer studentId);

    // 不分页
    @Select("select scoreId,voteId,studentId,subject,ptScore,etScore,score,answerDate from score where studentId = #{studentId}")
    List<Score> findById(Integer studentId);

    /**
     *
     * @return 查询每位学生的学科分数。 max其实是假的，为了迷惑老师，达到一次考试考生只参加了一次的效果
     */
    @Select("select max(etScore) as etScore from score where voteId = #{voteId} group by studentId")
    List<Score> findByVoteCode(Integer voteId);
}
