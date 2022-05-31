package com.example.bilabonnement_examproject.models;

import java.util.HashMap;
import java.util.Random;

//Simon
public class AdvanceAgreementModel {
    private int id;
    private String chassisNumber;
    private String terms;
    private int kilometersDriven;
    private int extendKilometer;
    private int refusalPrice;
    private int purchasePrice;
    private int locationId;

    public AdvanceAgreementModel() {
    }

    public AdvanceAgreementModel(int id, String chassisNumber, String terms, int kilometersDriven,
                                 int extendKilometer, int refusalPrice, int purchasePrice, int locationId) {
        this.id = id;
        this.chassisNumber = chassisNumber;
        this.terms = terms;
        this.kilometersDriven = kilometersDriven;
        this.extendKilometer = extendKilometer;
        this.refusalPrice = refusalPrice;
        this.purchasePrice = purchasePrice;
        this.locationId = locationId;
    }

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

    public int getKilometersDriven() {
        return kilometersDriven;
    }

    public int getExtendKilometer() {
        return extendKilometer;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getRefusalPrice() {
        return refusalPrice;
    }

    public void setRefusalPrice(int refusalPrice) {
        this.refusalPrice = refusalPrice;
    }

    public int getLocationId() {
        return locationId;
    }

}

