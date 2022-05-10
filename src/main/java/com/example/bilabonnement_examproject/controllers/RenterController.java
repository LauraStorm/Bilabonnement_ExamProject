package com.example.bilabonnement_examproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RenterController {
    @GetMapping("/create-renter-information")
    public String getCreateRenterPage(){
        return "create-renter-information";
    }
}
