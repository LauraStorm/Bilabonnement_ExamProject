package com.example.bilabonnement_examproject.utility;

import java.io.*;
import java.sql.*;
import java.util.Properties;

//Alle
public class DatabaseConnectionManager {
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private DatabaseConnectionManager(){}


    //environment variables
    public static Connection getConnection(){
        if(conn != null){
            return conn;
        }

        try {
            url = System.getenv("db.url");
            username = System.getenv("db.username");
            password = System.getenv("db.password");
            conn = DriverManager.getConnection(url, username, password);
        } catch(SQLException sqlException){
        }
        return conn;
    }

}