package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.repositories.SubscriptionRepo;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SubscriptionService {
    private CRUDInterface subscriptionRepo = new SubscriptionRepo();
    private CRUDInterface carRepo = new CarRepo();

    public SubscriptionService (CRUDInterface<SubscriptionModel,Integer> subscriptionRepository, CRUDInterface<CarModel,String> carRepository){
        this.subscriptionRepo =subscriptionRepository;
        this.carRepo=carRepository;
    }

    public SubscriptionService (){

    }

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


        while(monthNumber > 12) {
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


    public int getTotalPriceForAllRentedCars(){  //lau
        List<CarModel> allCars = carRepo.getAllEntities();
        List<SubscriptionModel> allSubscriptions = subscriptionRepo.getAllEntities();

        int totalPriceForAllRentedCars = 0;

        for (CarModel aCar:allCars) {
            if (aCar.isRented()== true){

                for (SubscriptionModel aSubscription : allSubscriptions) {
                    if(aCar.getChassisNumber() == aSubscription.getChassisNumber()){

                        //antal mdr. * total pris pr. mdr.
                        int leasingLengthInMonth = aSubscription.getLength();
                        int pricePrMonth = aSubscription.getTotalPriceMd();
                        totalPriceForAllRentedCars = totalPriceForAllRentedCars + leasingLengthInMonth * pricePrMonth;
                        System.out.println(aSubscription);
                        System.out.println(totalPriceForAllRentedCars);
                    }
                }
            }

        }
        System.out.println(totalPriceForAllRentedCars);
        return totalPriceForAllRentedCars;
    }

    public int getCurrentTotalPriceStatus(){ //lau
       //Start dato til i dag * total pris pr mdr.
        List<SubscriptionModel> allSubscriptions = subscriptionRepo.getAllEntities();
        CarService carService = new CarService();
        List<CarModel> allRentedCars = carService.getRentedCars(carRepo.getAllEntities());
        //liste med current lejeaftaler
        List<SubscriptionModel> nowleasing = new ArrayList<SubscriptionModel>();

        //dags dato
        LocalDate todaysDate = LocalDate.now();
        //pickup day
        LocalDate pickupDate;

        for (int i = 0; i < allRentedCars.size(); i++) {
            for (int j = 0; j < allSubscriptions.size(); j++) {
                if (allRentedCars.get(i).getChassisNumber() == allSubscriptions.get(j).getChassisNumber()){

                }
            }
        }

        int totalSum = 0;
        for (SubscriptionModel aSubscription :allSubscriptions) {
            String [] pickup = aSubscription.getPickupDate().split("-");
            //pickup day
            int pickupYear = Integer.parseInt(pickup[0]);
            int pickupMonth = Integer.parseInt(pickup[1]);
            int pickupDay = Integer.parseInt(pickup[2]);
            pickupDate = LocalDate.of(pickupYear,pickupMonth,pickupDay);
            //Delivery
            String [] delivery = aSubscription.getDeliveryDate().split("-");
            int deliveryYear = Integer.parseInt(delivery[0]);
            int deliveryMonth = Integer.parseInt(delivery[1]);
            int deliveryDay = Integer.parseInt(delivery[2]);
            LocalDate deliveryDate = LocalDate.of(deliveryYear,deliveryMonth,deliveryDay);

            //HVIS pick up er før d.d. og delivery er efter d.d.
            if (pickupDate.isBefore(todaysDate) && deliveryDate.isAfter(todaysDate)){
                nowleasing.add(aSubscription);

                Period periodBetweenDates = Period.between(pickupDate,todaysDate);
                System.out.println("period: " + periodBetweenDates);
                System.out.println("month: " +periodBetweenDates.getMonths());

                int totalMonth = 0;
                if (periodBetweenDates.getYears() <= 1 ){
                    totalMonth += 12 * periodBetweenDates.getYears();
                }
                if (periodBetweenDates.getMonths() != 0){
                    totalMonth += periodBetweenDates.getMonths();
                }

                if (periodBetweenDates.getMonths() == 0 && periodBetweenDates.getDays() > 0 ){
                    totalMonth += 1;
                }

                int sum = totalMonth * aSubscription.getTotalPriceMd();
                totalSum = totalSum + sum;
            }
        }
        System.out.println("size: " +nowleasing.size());
        System.out.println("totalSum: " +totalSum);

        return totalSum;
    }

    public boolean isDeliveryDatePassedTodaysDate (String deliveryDate){
        //Delivery date
        String [] delivery = deliveryDate.split("-");
        int deliveryYear = Integer.parseInt(delivery[0]);
        int deliveryMonth = Integer.parseInt(delivery[1]);
        int deliveryDay = Integer.parseInt(delivery[2]);
        LocalDate endDate = LocalDate.of(deliveryYear,deliveryMonth,deliveryDay);
        //Todays date
        LocalDate todaysDate = LocalDate.now();

        if (endDate.isAfter(todaysDate) || endDate.isEqual(todaysDate)){
            return true;
        }else {
            return false;
        }
    }

    public List<SubscriptionModel> onGoingSubscription (){
        List<SubscriptionModel> allSubscriptions = subscriptionRepo.getAllEntities();

        ArrayList<SubscriptionModel> onGoingSubscriptions = new ArrayList<SubscriptionModel>();

        for (SubscriptionModel aSupscription : allSubscriptions) {
            //Delivery date
            String [] delivery = aSupscription.getDeliveryDate().split("-");
            int deliveryYear = Integer.parseInt(delivery[0]);
            int deliveryMonth = Integer.parseInt(delivery[1]);
            int deliveryDay = Integer.parseInt(delivery[2]);
            LocalDate deliveryDate = LocalDate.of(deliveryYear,deliveryMonth,deliveryDay);
            //Todays date
            LocalDate todaysDate = LocalDate.now();
            if(deliveryDate.isAfter(todaysDate)){
                System.out.println("delivery Date: " + deliveryDate + " today: " + todaysDate);
                System.out.println(deliveryDate.isAfter(todaysDate));
                System.out.println(aSupscription);
                onGoingSubscriptions.add(aSupscription);
            }
        }
        return onGoingSubscriptions;
    }



    public int getExpectedSumFromTodayUntilDeliveryDate (){
        int totalLeasePrice = getTotalPriceForAllRentedCars();
        int currentTotalPriceStatus = getCurrentTotalPriceStatus();

        int expectedMoneyToReceive = totalLeasePrice - currentTotalPriceStatus;

        return expectedMoneyToReceive;
    }


}
