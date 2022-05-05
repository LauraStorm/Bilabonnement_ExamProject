package com.example.bilabonnement_examproject.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private DatabaseConnectionManager(){}

    public static Connection getConnection(){
        if(conn != null){
            return conn;
        }

        url = System.getenv("db.url");
        username = System.getenv("db.username");
        password = System.getenv("db.password");

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Yes - we have a connection to the Database");
        } catch (SQLException e) {
            System.out.println("No - something went wrong with the connection to the Database");
            e.printStackTrace();
        }

        return conn;
    }
}
