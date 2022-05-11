package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class CarController {

    @GetMapping("/register-car")
    public String getCarDetails(){
        return "register-car";
    }

    @PostMapping("/get-chassis-number")
    public String getChassisNumber(WebRequest dataFromForm, HttpSession session){
        String chassisNumberFromForm = dataFromForm.getParameter("chassis-number");

        session.setAttribute("chassisSession", chassisNumberFromForm);


        CarRepo carRepo = new CarRepo();
        boolean isUpdated = carRepo.updateEntity(chassisNumberFromForm);


        if (isUpdated == true){
            //hvis chassis number er valid og bilen er updatet bliver man sendt videre
            return "redirect:/create-renter-information";
        } else {
            //hvis chassis number ikke er valid bliver useren på siden
            return "redirect:/register-car";
        }
    }
}
