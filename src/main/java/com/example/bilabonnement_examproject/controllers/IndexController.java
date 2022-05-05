package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        DatabaseConnectionManager.getConnection();
        return "index";
    }
}
