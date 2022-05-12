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

    public int getTotalPriceForRentedCars () {
        List<SubscriptionModel> allSubscriptions = subscriptionRepo.getAllEntities();

        int sum = 0;
        //If hvis den rented
        for (SubscriptionModel currentSubscription : allSubscriptions) {
            //antal mdr. * total pris pr. mdr.
        }

        return sum;
    }



    //år-månede-dag -> xxxx-xx-xx
    public String getDeliveryDate(String pickupDate, int lenght){
        String deliveryDate = "";

        String year = "";
        String month = "";
        String day = "";

        String[] pickupDates = pickupDate.split("-");

        year = pickupDates[0];
        month = pickupDates[1];
        day = pickupDates[2];

        int monthNumber = Integer.parseInt(month) + lenght;
        int yearNumber = Integer.parseInt(year);


        while(monthNumber >= 12) {
            if (monthNumber > 12) {
                monthNumber = monthNumber - 12;
                yearNumber = yearNumber + 1;
            }
        }

        if (monthNumber < 10){
            deliveryDate = yearNumber + "-" + 0 + monthNumber + "-" + day;
        } else {
            deliveryDate = yearNumber + "-" + monthNumber + "-" + day;
        }

        return deliveryDate;
    }
}
