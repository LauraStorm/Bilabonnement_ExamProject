package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.SubscriptionRepo;
import com.example.bilabonnement_examproject.services.SubscriptionService;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.io.StringBufferInputStream;

@Controller
public class SubscriptionController {

    @GetMapping("/create-subscription")
    public String getCreateSubscriptionPage(){
        return "create-subscription-information";
    }

    @PostMapping("/get-subscription-information")
    public String getSubscriptionDetails(WebRequest dataFromForm, HttpSession session){
        SubscriptionRepo subscriptionRepo = new SubscriptionRepo();
        SubscriptionService subscriptionService = new SubscriptionService();
        SubscriptionModel subscriptionModel = null;

        String locationId = String.valueOf(session.getAttribute("locationIdSession"));
        String chassisNumber = String.valueOf(session.getAttribute("chassisSession"));
        String rentersId = String.valueOf(session.getAttribute("renterId"));
        String selfrisk = subscriptionService.StringTooBooleanTerms(dataFromForm.getParameter("selfrisk"));
        String deliveryInsurance = subscriptionService.StringTooBooleanTerms(dataFromForm.getParameter("deliveryInsurance"));
        String totalPrice = dataFromForm.getParameter("totalPrice");
        String lenght = dataFromForm.getParameter("lenght");
        String subscriptionType = subscriptionService.findType(Integer.parseInt(lenght));
        String pickupDate = dataFromForm.getParameter("pickupDate");

        System.out.println(rentersId);

        subscriptionModel = new SubscriptionModel(Integer.parseInt(locationId), chassisNumber, Integer.parseInt(rentersId),
                Boolean.parseBoolean(selfrisk), Boolean.parseBoolean(deliveryInsurance), Integer.parseInt(totalPrice),
                Integer.parseInt(lenght), subscriptionType, pickupDate);

        subscriptionRepo.createEntity(subscriptionModel);

        return"redirect:/success";
    }
    @GetMapping("/success")
    public String getSuccesPage(){
        return "success-page";
    }
}
