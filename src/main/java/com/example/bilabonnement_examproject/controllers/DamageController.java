package com.example.bilabonnement_examproject.controllers;

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
    public String carIdSubmit(WebRequest formData) {
        String result = "";
        String carId = formData.getParameter("carid");
        assert carId != null;
        if (carService.isChassisNumberValid(carId)) {
            result = "redirect:/damage?carid=" + carId;
        } else {
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
                                   @RequestParam(value = "carid") String carId) {
        model.getAttribute("carid");
        damage.setChassisNumber(carId);
        model.addAttribute("damage", damage);
        damageService.createDamageReport(damage);
        return "result-damage";
    }
}
