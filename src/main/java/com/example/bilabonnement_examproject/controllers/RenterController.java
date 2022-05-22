package com.example.bilabonnement_examproject.controllers;
import com.example.bilabonnement_examproject.models.RenterModel;
import com.example.bilabonnement_examproject.repositories.RenterRepo;
import com.example.bilabonnement_examproject.services.RenterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class RenterController {
    private RenterService renterService = new RenterService();

    @GetMapping("/create-renter-information")
    public String getCreateRenterPage(){
        return "create-renter-information";
    }


	@PostMapping("/get-renter-information")
    public String getRenterDetails(WebRequest dataFromForm, HttpSession session,  RedirectAttributes attributes) {
        return renterService.renterServicePost(dataFromForm, session, attributes);
    }
}