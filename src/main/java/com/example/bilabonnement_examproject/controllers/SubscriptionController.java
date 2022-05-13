package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.SubscriptionRepo;
import com.example.bilabonnement_examproject.services.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@Controller
public class SubscriptionController {

    @GetMapping("/create-subscription")
    public String getCreateSubscriptionPage(){
        return "create-subscription-information";
    }

    @PostMapping("/get-subscription-information")
    public String getSubscriptionDetails(WebRequest dataFromForm, HttpSession session, RedirectAttributes attributes){
        SubscriptionRepo subscriptionRepo = new SubscriptionRepo();
        SubscriptionService subscriptionService = new SubscriptionService();
        SubscriptionModel subscriptionModel = null;
        String errorMessage = "Alle felter skal udfyldes";

        String locationId = String.valueOf(session.getAttribute("locationIdSession"));
        String chassisNumber = String.valueOf(session.getAttribute("chassisSession"));
        String rentersId = String.valueOf(session.getAttribute("renterId"));
        String selfrisk = (dataFromForm.getParameter("selfrisk"));
        String deliveryInsurance = (dataFromForm.getParameter("deliveryInsurance"));
        String totalPrice = dataFromForm.getParameter("totalPrice");
        String lenght = dataFromForm.getParameter("lenght");
        String subscriptionType = lenght;
        String pickupDate = dataFromForm.getParameter("pickupDate");

        if (selfrisk == null || deliveryInsurance == null || totalPrice == "" || lenght == ""
        || subscriptionType ==  "" || pickupDate == "") {

            attributes.addFlashAttribute("error", errorMessage);
            return "redirect:/create-subscription";


        } else {

            subscriptionModel = new SubscriptionModel(Boolean.parseBoolean(subscriptionService.StringTooBooleanTerms(selfrisk)),
                    Boolean.parseBoolean(subscriptionService.StringTooBooleanTerms(deliveryInsurance)), Integer.parseInt(totalPrice),
                    Integer.parseInt(lenght), subscriptionService.findType(subscriptionType), chassisNumber, Integer.parseInt(locationId), Integer.parseInt(rentersId),
                    pickupDate);

            subscriptionRepo.createEntity(subscriptionModel);

            return "redirect:/success";
        }
    }
    @GetMapping("/success")
    public String getSuccesPage(){
        return "success-page";
    }
}
