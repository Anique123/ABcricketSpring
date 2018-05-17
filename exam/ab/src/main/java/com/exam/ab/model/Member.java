package com.exam.ab.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller

public class Member {

    private int memberId;
    private String FirstName;
    private String LastName;
    private String Adress;
    private int Zipcode;
    private String City;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Birthdate;
    private String CPR;
    private int Phone;
    private String email;

    public Member() {


    }

    public Member(int memberId, String firstName, String lastName, String adress, int zipcode, String city, Date birthdate, String CPR, int phone, String email) {
        this.memberId = memberId;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Adress = adress;
        this.Zipcode = zipcode;
        this.City = city;
        this.Birthdate = birthdate;
        this.CPR = CPR;
        this.Phone = phone;
        this.email = email;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public int getZipcode() {
        return Zipcode;
    }

    public void setZipcode(int zipcode) {
        Zipcode = zipcode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Date getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(Date birthdate) {
        Birthdate = birthdate;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}