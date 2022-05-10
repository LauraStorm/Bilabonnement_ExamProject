package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.repositories.LocationRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocationController {

    @GetMapping("/register-location")
    public String getRegisterLocationPage(){
        LocationRepo locationRepo = new LocationRepo();
        locationRepo.getSingleLocationByCityAndZipcode("rødovre", 2610,"slotsherrensvej 411c");
        return "register-location-information";
    }
}
