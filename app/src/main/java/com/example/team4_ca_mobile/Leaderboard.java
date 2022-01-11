package com.example.team4_ca_mobile;

public class Leaderboard {

    private String username;
    private Integer time;

    public Leaderboard(String username,Integer time) {
        this.username=username;
        this.time=time;
    }

    public Integer getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }
}
