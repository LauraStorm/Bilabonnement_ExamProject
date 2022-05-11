package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.repositories.DamageRepo;
import com.example.bilabonnement_examproject.repositories.LocationRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class DamageController {

    @GetMapping("/choosechassisnumber")
    public String getChooseChassisNumberPage(){
        return "damage-choose-chassis-number";
    }

    @GetMapping("/getregisterdamagepage")
    public String getRegisterDamagePage(){
        return "damage-register";
    }

    @PostMapping("/registerdamage")
    public String registerDamagePage(WebRequest dataFromForm){
        DamageRepo damageRepo = new DamageRepo();
        DamageReportModel damageReport = null;

        String defectDescription = dataFromForm.getParameter("defect-desciption");
        String price = dataFromForm.getParameter("defect-price");

        damageReport = damageRepo.getDamageReportModel(defectDescription, Integer.parseInt(price));


        return "redirect:/damage-success-page";
    }

}
