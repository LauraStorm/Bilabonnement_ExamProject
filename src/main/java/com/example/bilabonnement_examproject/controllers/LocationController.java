package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.repositories.LocationRepo;
import com.example.bilabonnement_examproject.services.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class LocationController {
    private LocationRepo locationRepo = new LocationRepo();
    private LocationService locationService = new LocationService();

    @GetMapping("/register-location")
    public String getRegisterLocationPage(Model model){
        ArrayList<LocationModel> locationModelsWithExtra = new ArrayList<LocationModel>();
        locationModelsWithExtra.add(new LocationModel("Vælg adresse","Vælg by",-1,-1));
        locationModelsWithExtra.addAll(locationRepo.getAllEntities());
        model.addAttribute("location",new LocationModel());
        model.addAttribute("locations",locationModelsWithExtra);
        return "register-location-information";
    }

    @PostMapping("/register-location")
    public String getLocationDetails(Model model, @ModelAttribute LocationModel location,
                                     WebRequest dataFromForm, HttpSession session, RedirectAttributes attributes){
        String errorMessage = "Lokationen findes ikke";
        model.addAttribute("location",location);
            session.setAttribute("locationIdSession", location.getId());
            if (location.getId() == 0) {

                attributes.addFlashAttribute("error", errorMessage);

                return "redirect:/register-location";

        } else {
                return "redirect:/create-subscription";
        }


    }
}
