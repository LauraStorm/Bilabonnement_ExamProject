package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.repositories.LocationRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class LocationController {

    @GetMapping("/register-location")
    public String getRegisterLocationPage(){
        return "register-location-information";
    }

    @PostMapping("/get-location-information")
    public String getLocationDetails(WebRequest dataFromForm, HttpSession session){
        LocationRepo locationRepo = new LocationRepo();
        LocationModel location = null;

        String address = dataFromForm.getParameter("address");
        String city = dataFromForm.getParameter("city");
        String postcode = dataFromForm.getParameter("postcode");

        location = locationRepo.getSingleLocationByCityAndZipcode(city,Integer.parseInt(postcode),address);

        session.setAttribute("locationIdSession", location.getId());

        return "redirect:/create-subscription";
    }
}
