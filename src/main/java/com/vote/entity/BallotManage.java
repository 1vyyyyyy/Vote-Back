package com.vote.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.google.gson.Gson;
import lombok.Data;
import java.util.List;
import java.time.LocalDate;

@Data
public class BallotManage {
    private Integer ballotId;

    private LocalDate ballotDate;

    private Integer voteId;

    private Integer userId;

//    private Object ballot;

    private String ballotStr;

    private List<BallotItem> ballot;

    public void setBallot() {
        this.ballotStr = new Gson().toJson(ballot);
        System.out.println("setBallot:"+ballotStr);
    }

    public List<BallotItem> getBallotItemList() {
        return ballot;
    }
}


