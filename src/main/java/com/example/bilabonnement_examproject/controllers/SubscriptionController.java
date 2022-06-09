package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.models.RenterModel;
import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.repositories.LocationRepo;
import com.example.bilabonnement_examproject.repositories.RenterRepo;
import com.example.bilabonnement_examproject.services.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

//Laura og Rasmus
@Controller
public class SubscriptionController {
    private SubscriptionService subscriptionService = new SubscriptionService();

    @GetMapping("/create-subscription")
    public String getCreateSubscriptionPage(){
        return "create-subscription-information";
    }


	@PostMapping("/get-subscription-information")
    public String getSubscriptionDetails(WebRequest dataFromForm, HttpSession session, RedirectAttributes attributes){
        return subscriptionService.subscriptionServicePost(dataFromForm,session,attributes);
    }

	@GetMapping("/receipt")
    public String getSubscriptionReceipt(HttpSession session, Model model){
        CarRepo carRepo = new CarRepo();
        CarModel carModel;
        LocationRepo locationRepo = new LocationRepo();
        LocationModel locationModel;
        RenterRepo renterRepo = new RenterRepo();
        RenterModel renterModel;

        SubscriptionModel subscriptionModel = (SubscriptionModel) session.getAttribute("justCreatedSubscription");
        model.addAttribute("subscription", subscriptionModel);

        carModel = carRepo.getSingleEntity(subscriptionModel.getChassisNumber());
        model.addAttribute("car", carModel);

        locationModel = locationRepo.getSingleEntity(subscriptionModel.getLocationId());
        model.addAttribute("location", locationModel);

        renterModel = renterRepo.getSingleEntity(subscriptionModel.getRentersId());
        model.addAttribute("renter", renterModel);

        return"subscription-receipt";
    }
}