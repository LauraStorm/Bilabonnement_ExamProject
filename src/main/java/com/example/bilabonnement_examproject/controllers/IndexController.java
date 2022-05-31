package com.example.bilabonnement_examproject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//Elisa og Laura
@Controller
public class IndexController {

    @GetMapping("/")
    public String getloginPage(){
        return "index";
    }


}
