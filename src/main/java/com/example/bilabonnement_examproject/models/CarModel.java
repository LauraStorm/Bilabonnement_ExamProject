package com.example.bilabonnement_examproject.models;

import com.example.bilabonnement_examproject.services.CarService;

//alle

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

    // bruges til drop-down forslag
    public CarModel(String chassisNumber, String color, String model, String manufacturer) {
        this.chassisNumber = chassisNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
    }

    public void setWagonNumber(int wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    public void setEquipmentLevel(String equipmentLevel) {
        this.equipmentLevel = equipmentLevel;
    }

    public void setSteelPrice(double steelPrice) {
        this.steelPrice = steelPrice;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public void setCarbonEmission(double carbonEmission) {
        this.carbonEmission = carbonEmission;
    }

    public CarModel(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public CarModel(String chassisNumber, int wagonNumber, String manufacturer, String model, String color, String equipmentLevel,
                    double steelPrice, double registrationFee, double carbonEmission, boolean isRented, boolean isSold) {
        this.chassisNumber = chassisNumber;
        this.wagonNumber = wagonNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.equipmentLevel = equipmentLevel;
        this.steelPrice = steelPrice;
        this.registrationFee = registrationFee;
        this.carbonEmission = carbonEmission;
        this.isRented = isRented;
        this.isSold = isSold;
    }

    public CarModel(){
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
        isRented = rented;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public String getEquipmentLevel() {
        return equipmentLevel;
    }

    public double getSteelPrice() {
        return steelPrice;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public double getCarbonEmission() {
        return carbonEmission;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "chassisNumber='" + chassisNumber + '\'' +
                ", wagonNumber=" + wagonNumber +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", equipmentLevel='" + equipmentLevel + '\'' +
                ", steelPrice=" + steelPrice +
                ", registrationFee=" + registrationFee +
                ", carbonEmission=" + carbonEmission +
                ", isRented=" + isRented +
                ", isSold=" + isSold +
                '}';
    }

    //Laura
    @Override
    public int compareTo(CarModel aCar) {
        if (this.isRented == aCar.isRented && aCar.isRentedToString(aCar.isRented()) == "ja"){
            return 1;
        }else if (this.isRented != aCar.isRented && aCar.isRentedToString(aCar.isRented()) != "ja"){
            return -1;
        } else {
            return 0;
        }
    }
}