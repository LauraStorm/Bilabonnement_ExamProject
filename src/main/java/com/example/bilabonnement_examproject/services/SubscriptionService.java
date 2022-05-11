package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.SubscriptionRepo;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionService {
    private SubscriptionRepo subscriptionRepo = new SubscriptionRepo();

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

    public int getTotalPriceForRentedCars (){
        List<SubscriptionModel> allSubscriptions = subscriptionRepo.getAllEntities();

        int sum = 0;
        //If hvis den rented

        for (SubscriptionModel currentSubscription : allSubscriptions) {
            //antal mdr. * total pris pr. mdr.
        }

          return sum;
    }


}
