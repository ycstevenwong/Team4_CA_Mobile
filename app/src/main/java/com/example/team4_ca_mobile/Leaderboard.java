package com.example.team4_ca_mobile;

public class Leaderboard {
    private String usename;
    private Integer time;
    public Leaderboard(String username,Integer time){
        this.usename=username;
        this.time=time;
    }

    public Integer getTime() {
        return time;
    }

    public String getUsename() {
        return usename;
    }
}
