package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.RenterModel;
import com.example.bilabonnement_examproject.repositories.RenterRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RenterController {

    @GetMapping("/create-renter-information")
    public String getCreateRenterPage(){

        RenterModel renterModel = new RenterModel("tim", "tim", "kea", 6089, "kbh", "jsjds@jskdk.ksdk", 12345678, 982939000, 92892003, 82839923);
        RenterRepo renterRepo = new RenterRepo();
        renterRepo.createEntity(renterModel);

        return "create-renter-information";
    }
}
