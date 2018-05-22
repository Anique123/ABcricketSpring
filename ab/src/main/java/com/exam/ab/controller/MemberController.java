package com.exam.ab.controller;

import com.exam.ab.DBconnection;
import com.exam.ab.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;
import java.util.*;

@Controller
public class MemberController {



    @GetMapping("/addmembersite")
    public String addmembersite(Model model) {
        model.addAttribute("medlem", new Member());
        return "addmembersite";
    }
    @GetMapping("/showmember")
    public String showmember(Model model){
        model.addAttribute("members", showMember());
        return "showmember";
    }

    @PostMapping("/addmembersite")
    public String medlem(@ModelAttribute Member member) {

        addMember(member);
        return "redirect:/confirmMember";
    }

    //Slet
    @GetMapping("/deletemember/{id}")
    public String deleteMember(@PathVariable("id") int id) {
        //Slet fra database
        //"DELETE FROM <table_name> WHERE <id> = '"+id+"'"
        System.out.println(id);
        return "redirect:/showmember";
    }

    private void addMember(Member member) {
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        Statement s = null;
        PreparedStatement preparedStatement1 = null;
        try {
            preparedStatement1 = con.prepareStatement("INSERT INTO Member(FirstName, LastName, CPR, Address, Zipcode, City, Phone, Email) VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement1.setString(1, member.getFirstName());
            preparedStatement1.setString(2, member.getLastName());
            preparedStatement1.setInt(3, member.getCPR());
            preparedStatement1.setString(4, member.getAdress());
            preparedStatement1.setInt(5, member.getZipcode());
            preparedStatement1.setString(6,member.getCity());
            preparedStatement1.setInt(7, member.getPhone());
            preparedStatement1.setString(8, member.getEmail());
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Member> showMember() {
        ArrayList<Member> medlemsOversigt = new ArrayList<>();

        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        Statement s = null;

        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM Member");
            while (resultSet.next()) {
                try {
                    Member member= new Member(
                            resultSet.getInt("ID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Address"),
                            resultSet.getInt("Zipcode"),
                            resultSet.getString("City"),
                            resultSet.getInt("CPR"),
                            resultSet.getInt("Phone"),
                            resultSet.getString("Email"));

                    medlemsOversigt.add(member);

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }


        return medlemsOversigt;
    }
}