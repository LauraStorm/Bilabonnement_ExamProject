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

@Controller
public class LocationController {
    private LocationRepo locationRepo = new LocationRepo();
    private LocationService locationService = new LocationService();

    @GetMapping("/register-location")
    public String getRegisterLocationPage(Model model){
        model.addAttribute("location",new LocationModel());
        model.addAttribute("locations",locationRepo.getAllEntities());
        return "register-location-information";
    }

    @PostMapping("/register-location")
    public String getLocationDetails(Model model, @ModelAttribute LocationModel location,
                                     WebRequest dataFromForm, HttpSession session, RedirectAttributes attributes){
        String errorMessage = "Lokationen findes ikke";
        model.addAttribute("location",location);

            session.setAttribute("locationIdSession", location.getId());

            if (location.getId() < 0) {

                attributes.addFlashAttribute("error", errorMessage);

                return "redirect:/register-location";

        } else {
                return "redirect:/create-subscription";
        }







        /*
        String address = dataFromForm.getParameter("address");
        String city = dataFromForm.getParameter("city");
        String postcode = dataFromForm.getParameter("postcode");

        if (postcode == ""){
            postcode = "0";
        }

        String fejlBesked = "Lokationen findes ikke";

        if (locationService.isLocationValid(city,address,Integer.parseInt(postcode)) == true) {

            location = locationRepo.getSingleLocationByCityAndZipcode(city, Integer.parseInt(postcode), address);

            session.setAttribute("locationIdSession", location.getId());

            return "redirect:/create-subscription";
        } else {

            attributes.addFlashAttribute("error", fejlBesked);

            return "redirect:/register-location";
        }

         */

    }
}
