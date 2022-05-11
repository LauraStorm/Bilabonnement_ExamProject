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

    public String findType (int length){
        String type = "";

        if (length == 4){
            type = "limited";
        } else {
            type = "unlimited";
        }

        return type;
    }


}
