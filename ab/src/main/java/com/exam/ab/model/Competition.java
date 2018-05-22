package com.exam.ab.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Competition {
    private int competitionID;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String ground;

    private String note;

    public Competition(){

    }
    public Competition(int competitionID, String name, Date startDate, Date endDate, String ground, String Note) {
        this.competitionID = competitionID;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ground = ground;
        this.note = note;
    }

    public int getCompetitionID(){
        return competitionID;
    }

    public void setCompetitionID(int competitionID){
        this.competitionID = competitionID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Date getEndDate(){return endDate;}

    public void setEndDate(Date endDate) { this.endDate = endDate;}


    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public String getNote(){return this.note;}

    public void setNote(String note) { this.note = note;}
}