package com.exam.ab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBconnection {

    static DBconnection instance = new DBconnection();
    static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    // Database url for Amazon RDS
    static final String DATABASE_URL = "jdbc:mysql://eksamensprojekt.cxn0gq7uvujo.eu-west-1.rds.amazonaws.com:3306/AB";
    static Connection con;

    public Connection createConnection(){
        con = null;
        try {
            Class.forName(JDBC_Driver);
            // credentials for Amazon RDS
            return con = DriverManager.getConnection(DATABASE_URL,"uddin","cricket786");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static DBconnection getInstance(){
        return instance;

    }



}