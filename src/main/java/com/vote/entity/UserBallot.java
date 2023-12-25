package com.vote.entity;


import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

public class UserBallot {
    private Integer userType;

    private Integer weight;

    private List<BallotItem> ballotItemList;

//    public List<Integer> calcScore() {
//        return this.ballot.stream()
//                .map(item -> item * weight)
//                .collect(Collectors.toList());
//    }
}
