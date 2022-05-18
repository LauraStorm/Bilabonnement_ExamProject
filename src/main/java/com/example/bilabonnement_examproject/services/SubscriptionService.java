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
    private CarService carService = new CarService();

    public SubscriptionService (CRUDInterface<SubscriptionModel,Integer> subscriptionRepository, CRUDInterface<CarModel,String> carRepository){
        this.subscriptionRepo =subscriptionRepository;
        this.carRepo=carRepository;
    }

    public SubscriptionService (){

    }

    public ArrayList<SubscriptionModel> getAllSubscriptions(){
        ArrayList<SubscriptionModel> subscriptionModels = new ArrayList<SubscriptionModel>(subscriptionRepo.getAllEntities());
        return subscriptionModels;
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

                    int spanMonths = expectedTimeSpanPickUpAndEndMonths(
                            currentSubscription.getPickupDate(),currentSubscription.getLength());

                    sum += currentSubscription.getTotalPriceMd()*spanMonths;
                }
            }
        }
            //antal mdr. * total pris pr. mdr.
        }
        return sum;
    }

    public int getExpectedRevenueForCurrentLeases(){  //lau & simon
        ArrayList<CarModel> allRentedCars = carService.getAllRentedCars();
        ArrayList<SubscriptionModel> allSubscriptions = getAllSubscriptions();

        int expectedRevenue = 0;

        for (CarModel car:allRentedCars) {
                for (SubscriptionModel subscription : allSubscriptions) {
                    if(car.getChassisNumber().equals(subscription.getChassisNumber())){
                        //antal mdr. * total pris pr. mdr.
                        int leasingLengthInMonthFromToday = expectedTimeSpanPickUpAndEndMonths(subscription.getPickupDate(),
                                subscription.getLength());
                        int pricePrMonth = subscription.getTotalPriceMd();
                        expectedRevenue += (pricePrMonth * leasingLengthInMonthFromToday);
                    }
                }


        }
        System.out.println(expectedRevenue);
        return expectedRevenue;
    }
    public int getAlreadyEarnedRevenueFromCurrentLeases(){  //lau & simon
        ArrayList<CarModel> allRentedCars = carService.getAllRentedCars();
        ArrayList<SubscriptionModel> allSubscriptions = getAllSubscriptions();

        int earnedRevenue = 0;

        for (CarModel car:allRentedCars) {
            for (SubscriptionModel subscription : allSubscriptions) {
                if(car.getChassisNumber().equals(subscription.getChassisNumber())){
                    //antal mdr. * total pris pr. mdr.
                    int totalLeasingLength = subscription.getLength();
                    int pricePrMonth = subscription.getTotalPriceMd();
                    earnedRevenue += ((pricePrMonth * totalLeasingLength) -
                            getExpectedRevenueForCurrentLeases());
                }
            }


        }
        System.out.println(earnedRevenue);
        return earnedRevenue;
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

    public int expectedTimeSpanPickUpAndEndMonths(String pickUpDate, int leaseLength){
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

        return (int) today.until(endOfLease,ChronoUnit.MONTHS);
    }
    public int expectedTimeSpanPickUpAndEndDays(String pickUpDate, int leaseLength){
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

        return (int) today.until(endOfLease,ChronoUnit.DAYS);
    }


    public int getTotalPriceForAllRentedCars(){  //lau
        ArrayList<CarModel> allRentedCars = carService.getAllRentedCars();
        ArrayList<SubscriptionModel> allSubscriptions = getAllSubscriptions();

        int totalPriceForAllRentedCars = 0;

        for (CarModel car:allRentedCars) {
            if (car.isRented()){
                for (SubscriptionModel subscription : allSubscriptions) {
                    if(car.getChassisNumber().equals(subscription.getChassisNumber())){
                        //antal mdr. * total pris pr. mdr.
                        int leasingLengthInMonth = subscription.getLength();
                        int pricePrMonth = subscription.getTotalPriceMd();
                        totalPriceForAllRentedCars = totalPriceForAllRentedCars + leasingLengthInMonth * pricePrMonth;
                        System.out.println(subscription);
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



        int totalSum = 0;
        for (SubscriptionModel aSubscription :allSubscriptions) {
            String[] pickup = aSubscription.getPickupDate().split("-");
            //pickup day
            int pickupYear = Integer.parseInt(pickup[0]);
            int pickupMonth = Integer.parseInt(pickup[1]);
            int pickupDay = Integer.parseInt(pickup[2]);
            pickupDate = LocalDate.of(pickupYear, pickupMonth, pickupDay);
            //Delivery
            String[] delivery = aSubscription.getDeliveryDate().split("-");
            int deliveryYear = Integer.parseInt(delivery[0]);
            int deliveryMonth = Integer.parseInt(delivery[1]);
            int deliveryDay = Integer.parseInt(delivery[2]);
            LocalDate deliveryDate = LocalDate.of(deliveryYear, deliveryMonth, deliveryDay);

            //ny forloop
            for (int i = 0; i < allRentedCars.size(); i++) {

                //HVIS pick up er før d.d. og delivery er efter d.d.
                //ny condetion chassis == chassis
                if (pickupDate.isBefore(todaysDate) && deliveryDate.isAfter(todaysDate) && aSubscription.getChassisNumber().equals(allRentedCars.get(i).getChassisNumber())) {
                    nowleasing.add(aSubscription);

                    System.out.println(aSubscription);

                    Period periodBetweenDates = Period.between(pickupDate, todaysDate);
                    System.out.println("period: " + periodBetweenDates);
                    System.out.println("month: " + periodBetweenDates.getMonths());

                    int totalMonth = 0;
                    if (periodBetweenDates.getYears() <= 1) {
                        totalMonth += 12 * periodBetweenDates.getYears();
                    }
                    if (periodBetweenDates.getMonths() != 0) {
                        totalMonth += periodBetweenDates.getMonths();
                    }

                    if (periodBetweenDates.getMonths() == 0 && periodBetweenDates.getDays() > 0) {
                        totalMonth += 1;
                    }

                    int sum = totalMonth * aSubscription.getTotalPriceMd();
                    totalSum = totalSum + sum;
                }
                //ny slut scope fra forloop
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
