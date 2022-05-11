package com.example.bilabonnement_examproject.services;

public class SubscriptionService {

    public String StringTooBooleanTerms(String input){
        String paid = null;

        if (input.equalsIgnoreCase("ja")){
            paid = "true";
        } else {
            paid = "false";
        }

        return paid;
    }
}
