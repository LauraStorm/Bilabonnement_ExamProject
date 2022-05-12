package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

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
        if (chassisNumber.length() == 17 && !chassisNumber.matches("[a-zA-Z]+")
                && chassisNumber.matches("[0-9]+")) {

            for (CarModel carModel: carRepo.getAllEntities()) {
                if (carModel.getChassisNumber().equals(chassisNumber)) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    public List<CarModel> getRentedCars(List<CarModel> allCars){
        CarRepo carRepo = new CarRepo();
        //List<CarModel> allCars = carRepo.getAllEntities();
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
}

