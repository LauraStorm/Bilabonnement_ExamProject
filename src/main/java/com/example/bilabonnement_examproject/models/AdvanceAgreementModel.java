package com.example.bilabonnement_examproject.models;

import java.util.HashMap;
import java.util.Random;

public class AdvanceAgreementModel {
    private int id;
    private String chassisNumber;
    private String terms;
    private double kilometersDriven;
    private double extendKilometerPrice;
    private double refusalTotalPrice;
    private Double purchasePrice;
    private String pickUpLocation;

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public double getKilometersDriven() {
        return kilometersDriven;
    }

    public void setKilometersDriven(double kilometersDriven) {
        this.kilometersDriven = kilometersDriven;
    }

    public double getExtendKilometerPrice() {
        return extendKilometerPrice;
    }

    public void setExtendKilometerPrice(double extendKilometerPrice) {
        this.extendKilometerPrice = extendKilometerPrice;
    }

    public double getRefusalTotalPrice() {
        return refusalTotalPrice;
    }

    public void setRefusalTotalPrice(double refusalTotalPrice) {
        this.refusalTotalPrice = refusalTotalPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public void setId() {
        Random rnd = new Random();
        this.id = rnd.nextInt(100) + 9999999;
    }
}

