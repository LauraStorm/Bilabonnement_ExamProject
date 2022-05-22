package com.example.bilabonnement_examproject.controllers;


import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarPurchaseController {
    private CarService carService = new CarService(new CarRepo());


	@GetMapping("/registernewcartofleet")
    public String registerNewCarToFleetFormShow(Model model) {
        model.addAttribute("car", new CarModel());
        return "car-register-purchase";
    }



	@PostMapping("/registernewcartofleet")
    public String registerNewCarToFleetFormSubmit(@ModelAttribute CarModel car,
                                   Model model,
                                   RedirectAttributes attributes) {
        model.addAttribute("car", car);
     return carService.submitCarToFleetPost(car,attributes);
    }
}
