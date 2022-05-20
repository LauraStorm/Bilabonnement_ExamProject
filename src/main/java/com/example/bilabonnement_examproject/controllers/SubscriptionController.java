package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.models.RenterModel;
import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.repositories.LocationRepo;
import com.example.bilabonnement_examproject.repositories.RenterRepo;
import com.example.bilabonnement_examproject.repositories.SubscriptionRepo;
import com.example.bilabonnement_examproject.services.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        String deliveryDate = "";

        if (selfrisk == null || deliveryInsurance == null || totalPrice == "" || lenght == ""
        || subscriptionType ==  "" || pickupDate == "") {

            attributes.addFlashAttribute("error", errorMessage);
            return "redirect:/create-subscription";


        } else {
            System.out.println("længden er " + lenght + "typen er " + subscriptionService.findType(lenght));

            subscriptionModel = new SubscriptionModel(Boolean.parseBoolean(subscriptionService.StringTooBooleanTerms(selfrisk)),
                    Boolean.parseBoolean(subscriptionService.StringTooBooleanTerms(deliveryInsurance)), Integer.parseInt(totalPrice),
                    Integer.parseInt(lenght), subscriptionService.findType(lenght), chassisNumber,
                    Integer.parseInt(locationId), Integer.parseInt(rentersId),
                    pickupDate, subscriptionService.getDeliveryDate(pickupDate, Integer.parseInt(lenght)));

            subscriptionRepo.createEntity(subscriptionModel);

            session.setAttribute("justCreatedSubscription", subscriptionModel);

            return "redirect:/receipt";
        }
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

    @GetMapping("/success")
    public String getSuccesPage(){
        return "success-page";
    }
}