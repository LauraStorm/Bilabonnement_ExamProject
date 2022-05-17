package com.example.bilabonnement_examproject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;


@Controller
public class IndexController {

    @GetMapping("/")
    public String getloginPage(){
        return "index";
    }


/*
    @GetMapping("/login")
    public String loginpage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(WebRequest dataFromForm){
        String login = dataFromForm.getParameter("login");
        String password = dataFromForm.getParameter("adgangskode");

        return "redirect:/homepage";

    }

    @GetMapping("/gethomepage")
    public String getHomePage(){
        return "homepage";
    }

 */





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
