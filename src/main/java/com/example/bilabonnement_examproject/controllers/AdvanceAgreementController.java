package com.example.bilabonnement_examproject.controllers;


import com.example.bilabonnement_examproject.models.AdvanceAgreementModel;
import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.AdvanceAgreementRepo;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.repositories.DamageRepo;
import com.example.bilabonnement_examproject.repositories.LocationRepo;
import com.example.bilabonnement_examproject.services.AdvanceAgreementService;
import com.example.bilabonnement_examproject.services.CarService;
import com.example.bilabonnement_examproject.services.DamageService;
import com.example.bilabonnement_examproject.services.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

//udarbejdet af Simon & Elisa
@Controller
public class AdvanceAgreementController {
    private AdvanceAgreementService agreementService = new AdvanceAgreementService();

	@GetMapping("/registeradvanceagreement")
    public String registerAdvanceAgreementFormShow(Model model,
             @RequestParam("chassisnumber") String chassisNumber,
                                                   @RequestParam(value = "issold", required = false) boolean isSold) {
        model.addAttribute("advanceagreement", new AdvanceAgreementModel());
		return agreementService.getAdvanceAgreement(model,chassisNumber,isSold);
    }


	@PostMapping("/registeradvanceagreement")
    public String registerAdvanceAgreementFormSubmit(@ModelAttribute AdvanceAgreementModel advanceAgreement,
                                                  Model model,
                                                  RedirectAttributes attributes,
													 @RequestParam("chassisnumber") String chassisNumber) {
        model.addAttribute("advanceagreement", advanceAgreement);
        return agreementService.postAdvanceAgreement(advanceAgreement, attributes, chassisNumber, model);
    }


}
