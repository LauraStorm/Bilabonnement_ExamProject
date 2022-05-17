package com.example.bilabonnement_examproject.controllers;


import com.example.bilabonnement_examproject.models.AdvanceAgreementModel;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.repositories.DamageRepo;
import com.example.bilabonnement_examproject.services.CarService;
import com.example.bilabonnement_examproject.services.DamageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdvanceAgreementController {
    private CarService carService = new CarService(new CarRepo());
    private DamageService damageService = new DamageService(new DamageRepo());

    @GetMapping("/caridagreement")
    public String carIdForm() {
        return "advance-agreement-carid";
    }

    @PostMapping("/caridagrement")
    public String carIdSubmit(WebRequest formData,
                              RedirectAttributes attributes) {
        String result = "";
        String carId = formData.getParameter("carid");
        assert carId != null;
        if (carService.isChassisNumberValid(carId)) {
            result = "redirect:/registeradvanceagreement?carid=" + carId;
        } else {
            attributes.addFlashAttribute("error","Stelnummeret findes ikke!");
            result = "redirect:/caridagreement";
        }
        return result;
    }

    @GetMapping("/registeradvanceagreement")
    public String registerAdvanceAgreementFormShow(Model model,
             @RequestParam(value = "carid") String carId) {
        model.addAttribute("advanceagreement", new AdvanceAgreementModel());
        model.addAttribute("carid",carId);
        return "register-advance-agreement";
    }


    @PostMapping("/registeradvanceagreement")
    public String registerAdvanceAgreementFormSubmit(@ModelAttribute AdvanceAgreementModel advanceAgreement,
                                                  Model model,
                                                  RedirectAttributes attributes,
                                                     @RequestParam(value = "carid") String carId) {
        String result = "";
        model.addAttribute("advanceagreement", advanceAgreement);
        model.addAttribute("damagesforcar", damageService.showAllDamagesForCar(carId));
        advanceAgreement.setChassisNumber(carId);
        if (advanceAgreement.getTerms().isEmpty()) {
            attributes.addFlashAttribute("errormessage", "Alle felter skal være udfyldt!");
            result = "redirect:/registeradvanceagreement?carid="+carId;
        } else {
            result = "register-advance-agreement-result";
        }
        return result;
    }
}
