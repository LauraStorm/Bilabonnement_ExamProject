package com.example.bilabonnement_examproject.models;

import com.example.bilabonnement_examproject.services.CarService;

public class CarModel implements Comparable<CarModel>{
    private String chassisNumber;
    private int wagonNumber;
    private String manufacturer;
    private String model;
    private String color;
    private String equipmentLevel;
    private double steelPrice;
    private double registrationFee;
    private double carbonEmission;
    private boolean isRented;
    private boolean isSold;


    public CarModel(String chassisNumber, String model, String color) {
        this.chassisNumber = chassisNumber;
        this.model = model;
        this.color = color;
    }

    public CarModel(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

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

    public boolean isSold() {
        return isSold;
    }

    public String isSoldToString (boolean isSold){
        CarService carService = new CarService();
        return carService.convertBooleanTermsToString(isSold);
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public int getWagonNumber() {
        return wagonNumber;
    }

    public void setWagonNumber(int wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEquipmentLevel() {
        return equipmentLevel;
    }

    public void setEquipmentLevel(String equipmentLevel) {
        this.equipmentLevel = equipmentLevel;
    }

    public double getSteelPrice() {
        return steelPrice;
    }

    public void setSteelPrice(double steelPrice) {
        this.steelPrice = steelPrice;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public double getCarbonEmission() {
        return carbonEmission;
    }

    public void setCarbonEmission(double carbonEmission) {
        this.carbonEmission = carbonEmission;
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


    @Override
    public int compareTo(CarModel aCar) {
        if (this.isRented == aCar.isRented()){
            return 1;
        } else if (this.isRented != aCar.isRented()){
            return -1;
        } else {
            return 0;
        }

    }
}

