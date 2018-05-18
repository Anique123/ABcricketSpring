package com.example.demo;

import java.sql.*;
import java.util.ArrayList;



public class dbConnection {

    static dbConnection instance = new dbConnection();
    static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/ABdb?useSSL=false";
    static Connection con;

    public Connection createConnection(){
        con = null;
        try {
            Class.forName(JDBC_Driver);
            return con = DriverManager.getConnection(DATABASE_URL, "root", "shoot2kill2");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static dbConnection getInstance(){
        return instance;

    }



}






