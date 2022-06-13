package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.repositories.SubscriptionRepo;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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

    //Laura og Rasmus
    public String subscriptionServicePost(WebRequest dataFromForm, HttpSession session, RedirectAttributes attributes){
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
            System.out.println("længden er " + lenght + "typen er " + findType(lenght));

            subscriptionModel = new SubscriptionModel(Boolean.parseBoolean(StringTooBooleanTerms(selfrisk)),
                    Boolean.parseBoolean(StringTooBooleanTerms(deliveryInsurance)), Integer.parseInt(totalPrice),
                    Integer.parseInt(lenght), findType(lenght), chassisNumber,
                    Integer.parseInt(locationId), Integer.parseInt(rentersId),
                    pickupDate, getDeliveryDate(pickupDate, Integer.parseInt(lenght)));

            subscriptionRepo.createEntity(subscriptionModel);

            session.setAttribute("justCreatedSubscription", subscriptionModel);

            return "redirect:/receipt";
        }
    }

    //Laura og Rasmus
    public ArrayList<SubscriptionModel> getAllSubscriptions(){
        ArrayList<SubscriptionModel> subscriptionModels = new ArrayList<SubscriptionModel>(subscriptionRepo.getAllEntities());
        return subscriptionModels;
    }

    //Laura og Rasmus
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

    //Laura og Rasmus
    public String findType (String length){
        String type = "";

        if (length.equals("4")){
            type = "limited";
        } else {
            type = "unlimited";
        }
        return type;
    }

    //Simon
    public int getExpectedRevenueForCurrentSubs(){
        SubscriptionRepo subscriptionRepo = new SubscriptionRepo();
        ArrayList<CarModel> allRentedCars = carService.getAllRentedCars();
        ArrayList<SubscriptionModel> allSubscriptions = getAllSubscriptions();
        HashMap<String,Integer> hashSetChassisNumbersAndID = new HashMap<String,Integer>();


        for (SubscriptionModel subscriptionsForHash:allSubscriptions
             ) {
            hashSetChassisNumbersAndID.put(subscriptionsForHash.getChassisNumber(),subscriptionsForHash.getId());
        }
        int expectedRevenue = 0;

            for (CarModel rentedCars:allRentedCars) {
                if (hashSetChassisNumbersAndID.containsKey(rentedCars.getChassisNumber()))
                {
                  SubscriptionModel subToGetPrice = subscriptionRepo.getSingleEntity(hashSetChassisNumbersAndID.
                          get(rentedCars.getChassisNumber()));
                    int daysUntilPickUpDate = expectedTimeSpanPickUpAndEndDays(
                            subToGetPrice.getPickupDate(), 0);
                if (daysUntilPickUpDate < 0) {
                    //antal mdr. * total pris pr. mdr.
                    int leasingLengthInMonthFromToday = expectedTimeSpanPickUpAndEndMonths(subToGetPrice.getPickupDate(),
                            subToGetPrice.getLength());
                    int pricePrMonth = subToGetPrice.getTotalPriceMd();
                    expectedRevenue += (pricePrMonth * leasingLengthInMonthFromToday);
                }
            }
                        }
        System.out.println(expectedRevenue);
        return expectedRevenue;
    }


    //Simon
    public int getTotalRevenueFromCurrentSubs(){
        SubscriptionRepo subscriptionRepo = new SubscriptionRepo();
        ArrayList<CarModel> allRentedCars = carService.getAllRentedCars();
        ArrayList<SubscriptionModel> allSubscriptions = getAllSubscriptions();
        ArrayList<SubscriptionModel> uniqueSubscriptions = new ArrayList<SubscriptionModel>();
        HashMap<String,Integer> hashSetChassisNumbersAndID = new HashMap<String,Integer>();


        for (SubscriptionModel subscriptionsForHash:allSubscriptions
        ) {
            hashSetChassisNumbersAndID.put(subscriptionsForHash.getChassisNumber(),subscriptionsForHash.getId());
        }
        int expectedRevenue = 0;

        for (CarModel rentedCars:allRentedCars) {
            if (hashSetChassisNumbersAndID.containsKey(rentedCars.getChassisNumber()))
            {
                SubscriptionModel subToGetPrice = subscriptionRepo.getSingleEntity(hashSetChassisNumbersAndID.
                        get(rentedCars.getChassisNumber()));
                int daysUntilPickUpDate = expectedTimeSpanPickUpAndEndDays(
                        subToGetPrice.getPickupDate(), 0);
                if (daysUntilPickUpDate < 0) {
                    //antal mdr. * total pris pr. mdr.
                    int totalMonthLength = subToGetPrice.getLength();
                    int pricePrMonth = subToGetPrice.getTotalPriceMd();
                    expectedRevenue += (pricePrMonth * totalMonthLength);
                }
            }
        }
        System.out.println(expectedRevenue);
        return expectedRevenue;
    }


    //Rasmus
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

    //Simon
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

    //Simon
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
}
