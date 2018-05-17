package com.exam.ab;

import com.exam.ab.model.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccessDB{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://eksamensprojekt.cxn0gq7uvujo.eu-west-1.rds.amazonaws.com:3306/AB";
    static Connection con;
    /**   we want to use JDBC protocol, mysql DBMS , the local host with
     the database ap */

    public static ArrayList<Member> getMembers() throws SQLException {
        try {
            ArrayList<Member> members = null;

            //***  Establishing the connection
            con = null;
            Statement s = null;
            Class.forName (JDBC_DRIVER);

            // in the url we have to tell which account and password to use
            con =  DriverManager.getConnection(DATABASE_URL,"uddin","cricket786");

            //*** now that the connection is established we do the query
            s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT * from Member ");

            // if the resultset is not empty, we position to first row and display first field
            if (rs != null)
                while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt(1));
                }
            s.close();
            con.close();
            return members;
        }
        /* correct errorhandling takes up a lot of space */
        catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                con.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }
        catch (ClassNotFoundException noClass) {
            System.err.println("Driver Class not found");
            System.out.println(noClass.getMessage());
            System.exit(1);  // terminate program
        }
        return null;
    }





}
