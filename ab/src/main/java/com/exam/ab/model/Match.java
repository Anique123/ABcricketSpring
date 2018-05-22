package com.exam.ab.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Match {
    private int matchID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    private String homeTeam;
    private String awayTeam;
    private String ground;

    public Match(){

    }

    public Match(int matchID, Date startTime, String homeTeam, String awayTeam, String ground){
        this.matchID = matchID;
        this.startTime = startTime;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.ground = ground;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }
}