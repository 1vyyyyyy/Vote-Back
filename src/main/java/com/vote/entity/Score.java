package com.vote.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Score {
    private Integer voteId;

    private Integer studentId;

    private String subject;

    private Integer ptScore;

    private Integer etScore;

    private Integer score;

    private Integer scoreId;

    private String answerDate;
}