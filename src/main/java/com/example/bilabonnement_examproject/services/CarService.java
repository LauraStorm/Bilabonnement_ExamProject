package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;
import net.bytebuddy.description.type.TypeList;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private CRUDInterface<CarModel,Integer> carRepo;

    public CarService(CRUDInterface carRepo){
       this.carRepo = carRepo;
    }

    public CarService() {

    }

    public boolean isChassisNumberValid(String chassisNumber) {
        CarRepo carRepo = new CarRepo();

        boolean isValid = false;
        if (chassisNumber.length() == 17 && chassisNumber.matches("[0-9]+")) {

                if (!carRepo.getSingleEntity(chassisNumber).isRented()) {
                    isValid = true;
                }
            }
        return isValid;
    }

    public List<CarModel> getUnrentedCars(List<CarModel> allCars){
        List<CarModel> UnrentedCars = new ArrayList<CarModel>();

        for (int i = 0; i < allCars.size(); i++) {
            if (allCars.get(i).isRented() == false){
                UnrentedCars.add(allCars.get(i));
            } else {

            }
        }
        return UnrentedCars;
    }

    public List<CarModel> getRentedCars(List<CarModel> allCars){
        List<CarModel> rentedCars = new ArrayList<CarModel>();

        for (int i = 0; i < allCars.size(); i++) {
            if (allCars.get(i).isRented() == true){
                rentedCars.add(allCars.get(i));
            } else {

            }
        }
        return rentedCars;
    }

    public String convertBooleanTermsToString(boolean isRented){
        if (isRented == true){
            return "ja";
        } else {
            return "nej";
        }
    }

    public boolean addNewToFleet(CarModel car) {
        try {
            carRepo.createEntity(car);
            return true;
        } catch (Exception exception) {
            System.out.println("Something went wrong with inserting!");
            return false;

        }
    }

    public ArrayList<CarModel> getAllUnsoldCars(){
        ArrayList<CarModel> unsoldCarsList = new ArrayList<CarModel>();
        for (CarModel cars:carRepo.getAllEntities()
             ) {
            if (!cars.isSold()){
                unsoldCarsList.add(cars);
            }
        }
        return unsoldCarsList;
    }

    public ArrayList<CarModel> getRentedCarsToReturn(){
        ArrayList<CarModel> rentedCarsList = new ArrayList<CarModel>();

        for (CarModel rentedCar : carRepo.getAllEntities()) {
            if (rentedCar.isRented()){
                rentedCarsList.add(rentedCar);
            }
        }

        return rentedCarsList;
    }

    public ArrayList<CarModel> fillCarListWithADummyOption(ArrayList<CarModel> carArray){
        ArrayList<CarModel> carModelArrayListExtended = new ArrayList<CarModel>();
        carModelArrayListExtended.add(new CarModel("Stelnummer","Model","Farve"));
        carModelArrayListExtended.addAll(carArray);
        return carModelArrayListExtended;

    }


}

