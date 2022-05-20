package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.services.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class FinanceController {

	@GetMapping("/finance")
    public String showFinances(Model model){
        SubscriptionService subscriptionService = new SubscriptionService();
        model.addAttribute("sumcurrentlease",subscriptionService.getExpectedRevenueForCurrentSubs());
        model.addAttribute("totalrevenue",subscriptionService.getTotalRevenueFromCurrentSubs());
        //model.addAttribute("mostmanufacturer",;
        return "finance-page";
    }
}
