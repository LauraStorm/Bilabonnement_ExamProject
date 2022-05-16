package com.example.bilabonnement_examproject.models;

import com.example.bilabonnement_examproject.services.CarService;

public class CarModel {
    private String chassisNumber;
    private String model;
    private String color;
    private boolean isRented;


    public CarModel(String chassisNumber, String model, String color, boolean rented) {
        this.chassisNumber = chassisNumber;
        this.model = model;
        this.color = color;
        this.isRented = rented;
    }

    public CarModel(){

    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isRented() {
        return isRented;
    }

    public String isRentedToString(boolean isRented){
        CarService carService = new CarService();
        return carService.convertBooleanTermsToString(isRented);
    }

    public void setRented(boolean rented) {
        this.isRented = isRented;
    }


    @Override
    public String toString() {
        CarService carService = new CarService();
        return "CarModel{" +
                "chassisNumber='" + chassisNumber + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", isRented=" + carService.convertBooleanTermsToString(isRented) +
                '}';
    }
}

