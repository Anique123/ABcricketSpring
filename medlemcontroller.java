package com.example.demo;

import com.example.demo.medlem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;
import java.util.*;

@Controller
public class medlemcontroller {

    ArrayList<medlem> medlemmer = new ArrayList<medlem>();


    @GetMapping("/addmembersite")
    public String addmembersite(Model model) {

        model.addAttribute("medlem", new medlem());
        return "addmembersite";
    }
    @GetMapping("/vismedlemmer")
    public String vismedlemmer(Model model){
        model.addAttribute("medlemmer", showMember());
        return "vismedlemmer";
    }

    @PostMapping("/addmembersite")
    public String medlem(@ModelAttribute medlem medlem) {

        addMember(medlem);
        return "redirect:/confirmMember";
    }

    private void addMember(medlem medlem) {
        dbConnection db = dbConnection.getInstance();
        Connection con = db.createConnection();
        Statement s = null;
        PreparedStatement preparedStatement1 = null;
        try {
            preparedStatement1 = con.prepareStatement("INSERT INTO member(member_firstname, member_lastname, member_cpr, member_adress, zip_code, phone_number, email_adress) VALUES(?,?,?,?,?,?,?)");
            preparedStatement1.setString(1, medlem.getFirstName());
            preparedStatement1.setString(2, medlem.getLastName());
            preparedStatement1.setString(3, medlem.getCPR());
            preparedStatement1.setString(4, medlem.getAdress());
            preparedStatement1.setInt(5, medlem.getZipcode());
            preparedStatement1.setInt(6, medlem.getPhone());
            preparedStatement1.setString(7, medlem.getEmail());
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<medlem> showMember() {
        ArrayList<medlem> medlemsOversigt = new ArrayList<>();

        dbConnection db = dbConnection.getInstance();
        Connection con = db.createConnection();
        Statement s = null;

        try {
            ResultSet resultSet = s.executeQuery("SELECT * FROM member INNER JOIN zipcode ON member.zip_code = zipcode.zipcode_zipcode");
            while (resultSet.next()) {
                try {
                    medlem medlem = new medlem();
                    medlem.setFirstName(resultSet.getString("member_firstname"));
                    medlem.setLastName(resultSet.getString("member_lastname"));
                    medlem.setCPR(resultSet.getString("member_cpr"));
                    medlem.setAdress(resultSet.getString("member_adress"));
                    medlem.setZipcode(resultSet.getInt("zip_code"));
                    medlem.setPhone(resultSet.getInt("phone_number"));
                    medlem.setEmail(resultSet.getString("email_adress"));

                    medlemsOversigt.add(medlem);

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


