package com.example.bilabonnement_examproject.models;

public class CarModel {
    private String chassisNumber;
    private String model;
    private String color;
    private boolean isRented;

    public CarModel(String chassisNumber, String model, String color, boolean isRented) {
        this.chassisNumber = chassisNumber;
        this.model = model;
        this.color = color;
        this.isRented = isRented;
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

    public void setRented(boolean rented) {
        isRented = rented;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "chassisNumber='" + chassisNumber + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", isRented=" + isRented +
                '}';
    }
}
