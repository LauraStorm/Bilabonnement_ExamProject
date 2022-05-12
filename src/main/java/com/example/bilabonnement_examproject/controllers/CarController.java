package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.List;

@Controller
public class CarController {

    @GetMapping("/register-car")
    public String getCarDetails(){
        return "register-car";
    }

    @PostMapping("/get-chassis-number")
    public String getChassisNumber(WebRequest dataFromForm, HttpSession session){
        CarRepo carRepo = new CarRepo();
        CarService carService = new CarService();
        String chassisNumberFromForm = dataFromForm.getParameter("chassis-number");

        if (carService.isChassisNumberValid(chassisNumberFromForm) == true){

            //hvis chassis number er valid og bilen er updatet bliver man sendt videre
            session.setAttribute("chassisSession", chassisNumberFromForm);
            boolean isUpdated = carRepo.updateEntity(chassisNumberFromForm);
            return "redirect:/create-renter-information";

        } else {
            //hvis chassis number ikke er valid bliver useren på siden
            return "redirect:/register-car";

        }
    }

    @GetMapping("/rented-cars")
    public String getAllRentedCarsPage(Model car){
        CarRepo carRepo = new CarRepo();
        CarService carService = new CarService();
        List<CarModel> rentedCars= carService.getRentedCars(carRepo.getAllEntities());

        car.addAttribute("rentedCars", rentedCars);

        return "view-leased-cars";
    }
}
