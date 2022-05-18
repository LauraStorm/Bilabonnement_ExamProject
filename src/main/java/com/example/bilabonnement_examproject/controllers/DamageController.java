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
    public String damageDataForm(Model model, @RequestParam (value = "chassisnumber")
                                 String chassisNumber) {
        model.addAttribute("damagesforcar",damageService.showAllDamagesForCar(chassisNumber));
        model.addAttribute("chassisnumber", chassisNumber);
        model.addAttribute("damage", new DamageReportModel());
        return "damage";
    }


    @PostMapping("/damage")
    public String damageDataSubmit(@ModelAttribute DamageReportModel damage,
                                   Model model, @RequestParam (value = "chassisnumber")
                                               String chassisNumber,
                                   RedirectAttributes attributes) {
        String result = "";
        model.addAttribute("damage", damage);
        damage.setChassisNumber(chassisNumber);
        if (damage.getDefectDescription().isEmpty()) {
            attributes.addFlashAttribute("error","Der mangler en beskrivelse!");
            result = "redirect:/damage?chassisnumber=" + chassisNumber;
        } else if (damage.getPrice() == 0.0){
            attributes.addFlashAttribute("error","Der mangler en pris!");
            result = "redirect:/damage?chassisnumber=" + chassisNumber;
        } else {
            damageService.createDamageReport(damage);
            result = "result-damage";
        }
        return result;
    }


    @GetMapping("/returncarpage")
    public String getReturnCarPage(){
        return "return-car";
    }

    @PostMapping("/returncarpage")
    public String returnCarPage(WebRequest dataFromForm, RedirectAttributes attributes, Model model, HttpSession session,
                                @RequestParam (value = "chassisnumber")
                                        String chassisNumber) {
        CarRepo carRepo = new CarRepo();
        CarService carService = new CarService();
        String result = "";

        String chassisNumberInput = dataFromForm.getParameter("chassis-number-input");

        if (!carRepo.getSingleEntity(chassisNumberInput).isRented()) {
            attributes.addFlashAttribute("error", "Denne bil har ikke været udlejet!");
            result = "redirect:/getreturncarpage";

        } else if (chassisNumberInput.isEmpty() || chassisNumberInput.length() != 17) {
            attributes.addFlashAttribute("error", "Udfyld valid stelnummer!");
            result = "redirect:/getreturncarpage";

        } else {
            session.setAttribute("returncar", carRepo.getSingleEntity(chassisNumberInput));
            carService.isChassisNumberValid(chassisNumberInput);
            carRepo.changeRentedStatus(chassisNumberInput);
            result = "redirect:/returncarsuccesspage";

            model.addAttribute("car", carRepo.getSingleEntity(chassisNumberInput));
            result = "redirect:/returncarsuccesspage";
        }

        return result;

    }

    @GetMapping("/selectchassisnumberreturn")
    public String selectReturnedCar(Model model, @RequestParam(value = "key") int key) {
        model.addAttribute("key",key);
        model.addAttribute("availablecars",carService.fillCarListWithADummyOption(carService.getRentedCarsToReturn()));
        model.addAttribute("car",new CarModel());


        return "select-chassisnumber-of-cars-return";
    }

    @GetMapping("/returncarsuccesspage")
    public String returnCarSuccessPage(Model model,@ModelAttribute CarModel car
                                       ,HttpSession session,
                                       @RequestParam (value = "chassisnumber")
                                               String chassisNumber) {
        CarRepo carRepo = new CarRepo();
        CarModel returnCar = carRepo.changeRentedStatus(chassisNumber);
        model.addAttribute("car", returnCar);
        return "return-car-success";
    }
}
