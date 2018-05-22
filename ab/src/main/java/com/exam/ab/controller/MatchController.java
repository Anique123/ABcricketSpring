package com.exam.ab.controller;

import com.exam.ab.DBconnection;
import com.exam.ab.model.Match;
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
public class MatchController {

    // Global traning object for delete
    private Match match_global = new Match();

    /*
     * Shows all matches when URL points to /matches
     */
    @GetMapping("/matches")
    public String showMatches(Model model){

        model.addAttribute("matches", getMatchesFromDB());
        return "matches";
    }

    /*
     * Creates a new Match object for creating a match
     */
    @GetMapping("/matches_create")
    public String createMatch(Model model){
        model.addAttribute("match", new Match());
        return "matches_create";
    }

    /*
     * Creates the match objebt from the values specified in the fields in the view
     * if the the form is submitted (if the "gem" button is clicked)
     */
    @PostMapping("/matches_create")
    public String createMatch(@ModelAttribute Match match){
        createMatchInDB(match);
        return "redirect:/matches";
    }

    /*
     * Gets and adds the match objebt that is to be edited
     * to Model. We need the ID for that specific match object.
     */
    @GetMapping("/matches_edit/{id}")
    public String editMatch(Model model, @PathVariable("id") int id){
        Match match = getMatchFromDB(id);
            model.addAttribute("match", match);
            return "matches_edit";
    }

    /*
     * Update the match object that was added to Model if the "gem" button is clicked.
     */
    @PostMapping("/matches_edit")
    public String editMatch(@ModelAttribute Match match){
        editMatchInDB(match);
        return "redirect:/matches";
    }

    /*
     * Set the ID for the training to be deleted in a global variable
     */
    @GetMapping("/matches_delete/{id}")
    public String deleteMatch(Model model, @PathVariable("id") int id){
        match_global.setMatchID(id);
        return "matches_delete";
    }

    /*
     * Gets the ID from the global variable to delete the training
     */
    @PostMapping("/matches_delete")
    public String deleteMatch(){
        deleteMatchFromDB(match_global.getMatchID());
        match_global.setMatchID(0);
        return "redirect:/matches";
    }

    /*
     * Get all matches from database
     */
    public ArrayList<Match> getMatchesFromDB() {
        ArrayList<Match> matches = new ArrayList<>();

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();

        try {
            // Execute sql code
            Statement s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM AB.`Match`");

            // Iterate over each row in DB and create a corresponding Match object and pass it to Array.
            while (resultSet.next()) {
                try {
                    // Create match object for each row
                    Match match = new Match();
                    match.setMatchID(resultSet.getInt("ID"));
                    match.setHomeTeam(resultSet.getString("HomeTeam"));
                    match.setAwayTeam(resultSet.getString("AwayTeam"));
                    match.setGround(resultSet.getString("Ground"));
                    match.setStartTime(resultSet.getDate("startTime"));

                    // Add match to the collection
                    matches.add(match);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        // return list of matches
        return matches;
    }


    /*
     * Get one match from database
     */
    public Match getMatchFromDB(int id){
        Match match = null;

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();

        try {
            // Execute sql code
            Statement s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM AB.`Match` WHERE ID = " + id);
            resultSet.next();
            try {
                // Add values from the database to the match objebt
                match = new Match();
                match.setMatchID(resultSet.getInt("ID"));
                match.setHomeTeam(resultSet.getString("HomeTeam"));
                match.setAwayTeam(resultSet.getString("AwayTeam"));
                match.setGround(resultSet.getString("Ground"));
                match.setStartTime(resultSet.getDate("startTime"));


            } catch (SQLException e) {
                e.printStackTrace();

            }

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        // returning the match object
        return match;
    }

    /*
     * Create training in DB with prepared statement
     */
    private void createMatchInDB(Match match) {

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();

        try {
            // Prepare sql command by using prepared statement
            PreparedStatement preparedStatement1 = null;
            preparedStatement1 = con.prepareStatement("INSERT INTO AB.`Match`(startTime, HomeTeam, AwayTeam, Ground) VALUES(?,?,?,?)");
            preparedStatement1.setDate(1, new java.sql.Date(match.getStartTime().getTime()));
            preparedStatement1.setString(2, match.getHomeTeam());
            preparedStatement1.setString(3, match.getAwayTeam());
            preparedStatement1.setString(4, match.getGround());

            // Execute command
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Update training in DB with prepared statement
     */
    private void editMatchInDB(Match match){

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        try {

            // Prepare sql command by using prepared statement
            PreparedStatement preparedStatement1 = null;
            preparedStatement1 = con.prepareStatement("UPDATE AB.`Match` SET startTime = ?, HomeTeam = ?, AwayTeam = ?, Ground = ? WHERE ID = ?");
            preparedStatement1.setDate(1, new java.sql.Date(match.getStartTime().getTime()));
            preparedStatement1.setString(2, match.getHomeTeam());
            preparedStatement1.setString(3, match.getAwayTeam());
            preparedStatement1.setString(4, match.getGround());

            // We need to specify the ID for the match to update it
            preparedStatement1.setInt(5, match.getMatchID());

            // Execute command
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Deletes the training from the database
     */
    public void deleteMatchFromDB(int id){

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        try {
            // Prepare sql command
            PreparedStatement preparedStatement1 = null;
            preparedStatement1 = con.prepareStatement("DELETE FROM AB.`Match` WHERE ID = ?");
            preparedStatement1.setInt(1,id);

            // Execute command
            preparedStatement1.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
