package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.repositories.DamageRepo;
import com.example.bilabonnement_examproject.services.CarService;
import com.example.bilabonnement_examproject.services.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DamageController {
    private CarService carService = new CarService(new CarRepo());
    private DamageService damageService = new DamageService(new DamageRepo());


	@GetMapping("/damage")
    public String damageDataForm(Model model, @RequestParam("chassisnumber")
                                 String chassisNumber) {
        model.addAttribute("chassisnumbercar",chassisNumber);
        model.addAttribute("damage", new DamageReportModel());
        damageService.showDamagesForACar(model,chassisNumber);
        return "damage";
    }


	@PostMapping("/damage")
    public String damageDataSubmit(@ModelAttribute DamageReportModel damage, RedirectAttributes attributes,
                                   Model model, @RequestParam("chassisnumber")
                                               String chassisNumber) {
        model.addAttribute("damage", damage);
        return damageService.damageDataFormPost(damage,chassisNumber, attributes);
    }


	@GetMapping("/selectchassisnumberreturn")
    public String selectReturnedCar(Model model, @RequestParam("key") int key) {
        carService.selectCarToReturn(model,key);
        model.addAttribute("header","Meld følgende biler returneret");
        model.addAttribute("actionname","Modtag retur");
        model.addAttribute("car",new CarModel());
        return "select-chassisnumber-of-cars-return";
    }


	@GetMapping("/returncarsuccesspage")
    public String returnCarSuccessPage(Model model,@ModelAttribute CarModel car
                                       ,HttpSession session,
                                       @RequestParam("chassisnumber")
                                               String chassisNumber) {
        return carService.returnCarSuccesPage(chassisNumber,model);
    }
}
