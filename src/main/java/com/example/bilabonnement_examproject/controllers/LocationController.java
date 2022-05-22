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
        model.addAttribute("location",new LocationModel());
        model.addAttribute("locations", locationService.getSelectLocationListForView());
        return "register-location-information";
    }

	@PostMapping("/register-location")
    public String getLocationDetails(Model model, @ModelAttribute LocationModel location,
                                     WebRequest dataFromForm, HttpSession session, RedirectAttributes attributes){
        model.addAttribute("location",location);
        return locationService.locationsDetilsSelectPost(session,location,attributes);

    }
}
