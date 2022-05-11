package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

public class CarService {
    private final CRUDInterface<CarModel,Integer> carRepo;

    public CarService(CRUDInterface carRepo){
        this.carRepo = carRepo;
    }

    public boolean isChassisNumberValid(String chassisNumber) {
        boolean isValid = false;
        if (chassisNumber.length() == 17
                && !chassisNumber.matches("[a-zA-Z]+")
                && chassisNumber.matches("[0-9]+")) {
            for (CarModel car : carRepo.getAllEntities()
            ) {
                if (car.getChassisNumber().equals(chassisNumber)) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }
}

