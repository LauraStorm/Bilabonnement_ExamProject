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

@Controller
public class DamageController {
    private CarService carService = new CarService(new CarRepo());
    private DamageService damageService = new DamageService(new DamageRepo());

    @GetMapping("/carid")
    public String carIdForm() {
        return "carid";
    }

    @PostMapping("/carid")
    public String carIdSubmit(WebRequest formData, RedirectAttributes attributes) {
        String result = "";
        String carId = formData.getParameter("carid");
        assert carId != null;
        if (carService.isChassisNumberValid(carId)) {
            result = "redirect:/damage?carid=" + carId;
        } else {
            attributes.addFlashAttribute("error","Stelnummeret findes ikke!");
            result = "redirect:/carid";
        }
        return result;
    }

    @GetMapping("/damage")
    public String damageDataForm(Model model, @RequestParam
            (value = "carid") String carId) {
        model.addAttribute("carid", carId);
        model.addAttribute("damage", new DamageReportModel());
        return "damage";
    }


    @PostMapping("/damage")
    public String damageDataSubmit(@ModelAttribute DamageReportModel damage,
                                   Model model,
                                   @RequestParam(value = "carid") String carId,
                                   RedirectAttributes attributes) {
        String result = "";
        model.getAttribute("carid");
        damage.setChassisNumber(carId);
        model.addAttribute("damage", damage);
        if (damage.getDefectDescription().isEmpty()) {
            attributes.addFlashAttribute("error","Der mangler en beskrivelse!");
            result = "redirect:/damage?carid=" + carId;
        } else if (damage.getPrice() == 0.0){
            attributes.addFlashAttribute("error","Der mangler en pris!");
            result = "redirect:/damage?carid=" + carId;
        } else {
            damageService.createDamageReport(damage);
            result = "result-damage";
        }
        return result;
    }

    @GetMapping("/getreturncarpage")
    public String getReturnCarPage(){
        return "return-car";
    }

    @PostMapping("/returncarpage")
    public String returnCarPage(WebRequest dataFromForm, Model model){
        CarRepo carRepo = new CarRepo();
        CarService carService = new CarService();

        String chassisNumberInput = dataFromForm.getParameter("chassis-number-input");
        carService.isChassisNumberValid(chassisNumberInput);
        carRepo.changeRentedStatus(chassisNumberInput);

        model.addAttribute("car", carRepo.getSingleEntity(chassisNumberInput));

        return "redirect:/returncarsuccesspage";
    }

    @GetMapping("/returncarsuccesspage")
    public String returnCarSuccessPage(@ModelAttribute CarModel car, Model model) {
        model.addAttribute("car", car);
        return "return-car-success";
    }
}
