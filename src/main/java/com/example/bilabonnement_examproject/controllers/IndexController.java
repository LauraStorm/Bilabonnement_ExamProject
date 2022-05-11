package com.example.bilabonnement_examproject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.PreparedStatement;
import java.sql.SQLException;


@Controller
public class IndexController {

    /*
    @GetMapping("/")
    public String index() {
        String createCar = "INSERT INTO cars VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt = Data(createCar);
            pstmt.setInt(1,00000000000000000);
            pstmt.setString(2,"Spyder");
            pstmt.setString(3,"Red");
            pstmt.setBoolean(4,true);
            pstmt.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("nononononnoono");
        }
        return "index";
    }

     */
}
