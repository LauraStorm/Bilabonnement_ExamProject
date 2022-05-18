package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.services.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FinanceController {

    @GetMapping("/finance")
    public String showFinances(Model model){
        SubscriptionService subscriptionService = new SubscriptionService();
        model.addAttribute("sumcurrentlease",subscriptionService.getExpectedRevenueForCurrentLeases());
        model.addAttribute("alreadyearned",subscriptionService.getAlreadyEarnedRevenueFromCurrentLeases());
        return "finance-page";
    }
}
