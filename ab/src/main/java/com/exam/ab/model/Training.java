package com.exam.ab.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Training {
    private int ID;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int startTime;
    private int endTime;
    private String note;

    public Training(){

    }

    public int getID(){
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;

    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public int getStartTime(){
        return this.startTime;
    }

    public void setStartTime(int startTime){
        this.startTime=startTime;
    }

    public int getEndTime(){
        return this.endTime;
    }

    public void setEndTime(int endTime){
        this.endTime = endTime;
    }

    public String getNote(){
        return this.note;
    }

    public void setNote(String note){
        this.note = note;

    }

}
