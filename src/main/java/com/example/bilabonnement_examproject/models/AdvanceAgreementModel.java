package com.example.bilabonnement_examproject.models;

import java.util.HashMap;
import java.util.Random;

public class AdvanceAgreementModel {
    private int id;
    private String chassisNumber;
    private String terms;
    private int kilometersDriven;
    private int extendKilometer;
    private double refusalPrice;
    private int purchasePrice;
    private int locationId;

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

    public void setKilometersDriven(int kilometersDriven) {
        this.kilometersDriven = kilometersDriven;
    }

    public int getExtendKilometer() {
        return extendKilometer;
    }

    public void setExtendKilometer(int extendKilometer) {
        this.extendKilometer = extendKilometer;
    }


    public int getPurchasePrice() {
        return purchasePrice;
    }




    public double getRefusalPrice() {
        return refusalPrice;
    }

    public void setRefusalPrice(double refusalPrice) {
        this.refusalPrice = refusalPrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void setId() {
        Random rnd = new Random();
        this.id = rnd.nextInt(100) + 9999999;
    }
}

