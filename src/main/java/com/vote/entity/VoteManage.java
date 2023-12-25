package com.vote.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.google.gson.Gson;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VoteManage {
    private Integer voteId;

    private String voteName;

    private String voteDescription;

    private LocalDate voteDate;

    private LocalDate endDate;

    private Object candidates;

    private String candidatesStr;

    private Boolean enableWeight;

    private Integer weight0;

    private Integer weight1;

    private Integer weight2;

    private String weightsStr;

    private String result;

    public void setCandidates() {
        this.candidatesStr = new Gson().toJson(candidates);
    }

//    public void setWeights() {
//        this.weightsStr = new Gson().toJson(weights);
//    }
}

