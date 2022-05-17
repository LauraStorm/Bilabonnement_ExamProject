package com.example.bilabonnement_examproject.controllers;


import com.example.bilabonnement_examproject.models.AdvanceAgreementModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdvanceAgreementController {

    @GetMapping("/registeradvanceagreement")
    public String registerAdvanceAgreementFormShow(Model model) {
        model.addAttribute("advanceagreement", new AdvanceAgreementModel());
        return "register-advance-agreement";
    }


    @PostMapping("/registeradvanceagreement")
    public String registerAdvanceAgreementFormSubmit(@ModelAttribute AdvanceAgreementModel advanceAgreement,
                                                  Model model,
                                                  RedirectAttributes attributes) {
        String result = "";
        model.addAttribute("advanceagreement", advanceAgreement);
        if (advanceAgreement.getTerms().isEmpty()) {
            attributes.addFlashAttribute("errormessage", "Alle felter skal være udfyldt!");
            result = "redirect:/registeradvanceagreement";
        } else {
            result = "register-advance-agreement-result";
        }
        return result;
    }
}
