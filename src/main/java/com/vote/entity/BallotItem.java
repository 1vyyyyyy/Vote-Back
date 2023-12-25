package com.vote.entity;

public class BallotItem {
    private String name;
    private String description;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
