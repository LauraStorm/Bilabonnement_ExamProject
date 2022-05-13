package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.repositories.SubscriptionRepo;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

public class SubscriptionService {
    private SubscriptionRepo subscriptionRepo = new SubscriptionRepo();
    private CarRepo carRepo = new CarRepo();


    public String StringTooBooleanTerms(String input){
        String paid = "";

        if (input.equalsIgnoreCase("ja")){
            paid = "true";
        } else if(input == null){
            paid = "";
        } else {
            paid = "false";
        }
        return paid;
    }

    public String findType (String length){
        String type = "";
        if (length.length() == 4){
            type = "limited";
        } else {
            type = "unlimited";
        }
        return type;
    }

    public int getTotalPriceForRentedCars () {
        List<SubscriptionModel> allSubscriptions = subscriptionRepo.getAllEntities();
        List<CarModel> allCars = carRepo.getAllEntities();
        int sum = 0;
        for (CarModel currentCar : allCars) {
            if (currentCar.isRented()){
        for ( SubscriptionModel currentSubscription : allSubscriptions) {
                if (currentCar.getChassisNumber().equals(currentSubscription.getChassisNumber())){

                    /*
                    int spanDays = expectedTimeSpanPickUpAndEnd(
                            currentSubscription.getPickupDate(),currentSubscription.getLength()).getDays();

                     */

                    int spanMonths = expectedTimeSpanPickUpAndEnd(
                            currentSubscription.getPickupDate(),currentSubscription.getLength()).getMonths();

                    sum += currentSubscription.getTotalPriceMd()*spanMonths;
                }
            }
        }
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

    public Period expectedTimeSpanPickUpAndEnd(String pickUpDate, int leaseLength){
        HashMap<Integer,Month> monthHashMap = new HashMap<Integer,Month>();
        monthHashMap.put(1,Month.JANUARY);
        monthHashMap.put(2,Month.FEBRUARY);
        monthHashMap.put(3,Month.MARCH);
        monthHashMap.put(4,Month.APRIL);
        monthHashMap.put(5,Month.MAY);
        monthHashMap.put(6,Month.JUNE);
        monthHashMap.put(7,Month.JULY);
        monthHashMap.put(8,Month.AUGUST);
        monthHashMap.put(9,Month.SEPTEMBER);
        monthHashMap.put(10,Month.OCTOBER);
        monthHashMap.put(11,Month.NOVEMBER);
        monthHashMap.put(12,Month.DECEMBER);
        String[] pickUpDateArray = pickUpDate.split("-");
        LocalDate today = LocalDate.now();
        LocalDate endOfLease = LocalDate.of(Integer.parseInt(pickUpDateArray[0]),
                monthHashMap.get(Integer.parseInt(pickUpDateArray[1])), Integer.parseInt(pickUpDateArray[2]))
                .plusMonths(leaseLength);

        System.out.println("Until end of the lease: " + today.until(endOfLease));
        System.out.println("Until end of lease (chrono): " + today.until(endOfLease, ChronoUnit.DAYS));

        return today.until(endOfLease);
    }
}
