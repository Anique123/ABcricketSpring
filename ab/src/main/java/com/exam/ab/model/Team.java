package com.exam.ab.model;

public class Team {
    private int teamID;
    private String name;

    public Team(){

    }
    public Team(int teamID, String name){
        this.teamID = teamID;
        this.name = name;
    }

    public int getTeamID(){
        return this.teamID;
    }

    public void setTeamID(int teamID){
        this.teamID = teamID;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
