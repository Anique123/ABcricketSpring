package com.exam.ab.controller;

import com.exam.ab.DBconnection;
import com.exam.ab.model.Competition;
import com.exam.ab.model.Match;
import com.exam.ab.model.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class CompetitionController {

    // Global competition object for delete
    Competition competition_global = new Competition();

    /*
     * Shows all competition when URL points to /competition
     */
    @GetMapping("/competitions")
    public String showCompetitions(Model model){
        model.addAttribute("competitions", getCompetitionsFromDB());
        return "competitions";
    }

    /*
     * Creates a new Competition object for creating a competition
     */
    @GetMapping("/competitions_create")
    public String createCompetition(Model model){
        model.addAttribute("competition", new Competition());
        return "competitions_create";
    }

    /*
     * Creates the competition objebt from the values specified in the fields in the view
     * if the the form is submitted (if the "gem" button is clicked)
     */
    @PostMapping("/competitions_create")
    public String createCompetition(@ModelAttribute Competition competition){
        createCompetitionInDB(competition);
        return "redirect:/competitions";
    }

    /*
     * Gets and adds the competition objebt that is to be edited
     * to Model. We need the ID for that specific competition object.
     */
    @GetMapping("/competitions_edit/{id}")
    public String editMatch(Model model, @PathVariable("id") int id){
        Competition competition= getCompetitionFromDB(id);
        model.addAttribute("competition", competition);
        return "competitions_edit";
    }

    /*
     * Update the competition object that was added to Model if the "gem" button is clicked.
     */
    @PostMapping("/competitions_edit")
    public String editMatch(@ModelAttribute Competition competition){
        editCompetitionInDB(competition);
        return "redirect:/competitions";
    }

    /*
     * Set the ID for the competition to be deleted in a global variable
     */
    @GetMapping("/competitions_delete/{id}")
    public String deleteCompetition(Model model, @PathVariable("id") int id){
        competition_global.setCompetitionID(id);
        return "competitions_delete";
    }

    /*
     * Gets the ID from the global variable to delete the competition
     */
    @PostMapping("/competitions_delete")
    public String deleteMatch(){
        deleteCompetitionFromDB(competition_global.getCompetitionID());
        return "redirect:/competitions";
    }

    /*
     * Get all competitions from database
     */
    private ArrayList<Competition> getCompetitionsFromDB() {

        ArrayList<Competition> competitions = new ArrayList<>();

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        try {
            // Execute sql code
            Statement s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM AB.`Competition`");

            // Iterate over each row in DB and create a corresponding Competition object and pass it to Array.
            while (resultSet.next()) {
                try {
                    // Create competition object for each row
                    Competition competition= new Competition();
                    competition.setCompetitionID(resultSet.getInt("ID"));
                    competition.setName(resultSet.getString("name"));
                    competition.setStartDate(resultSet.getDate("startDate"));
                    competition.setEndDate(resultSet.getDate("endDate"));
                    competition.setGround(resultSet.getString("ground"));
                    competition.setNote(resultSet.getString("note"));

                    // Add competition to the collection
                    competitions.add(competition);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // return list of trainings
        return competitions;
    }

    /*
     * Get one competition from database
     */
    private Competition getCompetitionFromDB(int id) {
        Competition competition= null;

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();

        try {
            // Execute sql code
            Statement s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM AB.`Competition` WHERE ID = " + id);
            resultSet.next();
            try {
                // Add values from the database to the competition objebt
                competition = new Competition();
                competition.setCompetitionID(resultSet.getInt("ID"));
                competition.setName(resultSet.getString("name"));
                competition.setStartDate(resultSet.getDate("startDate"));
                competition.setEndDate(resultSet.getDate("endDate"));
                competition.setGround(resultSet.getString("ground"));
                competition.setNote(resultSet.getString("note"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // returning the training object
        return competition;
    }

    /*
     * Create competition in DB with prepared statement
     */
    private void createCompetitionInDB(Competition competition) {

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        try {
            // Prepare sql command by using prepared statement
            PreparedStatement preparedStatement1 = null;

            // Specify each column name in DB Table. ID does not need to be specified because it auto increments.
            preparedStatement1 = con.prepareStatement("INSERT INTO AB.`Competition`(name, startDate, endDate, ground, note) VALUES(?,?,?, ?, ?)");
            preparedStatement1.setString(1, competition.getName());
            preparedStatement1.setDate(2, new java.sql.Date(competition.getStartDate().getTime()));
            preparedStatement1.setDate(3, new java.sql.Date(competition.getEndDate().getTime()));
            preparedStatement1.setString(4, competition.getGround());
            preparedStatement1.setString(5, competition.getNote());

            // Execute command
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Update competition in DB with prepared statement
     */
    private void editCompetitionInDB(Competition competition) {

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();

        try {
            // Prepare sql command by using prepared statement
            PreparedStatement preparedStatement1 = null;
            preparedStatement1 = con.prepareStatement("UPDATE AB.`Competition` SET startDate = ?, endDate = ?, name = ?, ground = ?, note = ? WHERE ID = ?");
            preparedStatement1.setDate(1,new java.sql.Date(competition.getStartDate().getTime()) );
            preparedStatement1.setDate(2,new java.sql.Date(competition.getEndDate().getTime()) );
            preparedStatement1.setString(3, competition.getName());
            preparedStatement1.setString(4, competition.getGround());
            preparedStatement1.setString(5, competition.getNote());

            // We need to specify the ID for the competition to update it
            preparedStatement1.setInt(6, competition.getCompetitionID());

            // Execute command
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Deletes the competition from the database
     */
    private void deleteCompetitionFromDB(int competitionID) {

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        try {
            // Prepare sql command
            PreparedStatement preparedStatement1 = null;
            preparedStatement1 = con.prepareStatement("DELETE FROM AB.`Competition` WHERE ID = ?");
            preparedStatement1.setInt(1,competitionID);

            // Execute command
            preparedStatement1.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
