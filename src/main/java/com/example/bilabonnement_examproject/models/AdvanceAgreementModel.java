package com.example.bilabonnement_examproject.models;

import java.util.HashMap;
import java.util.Random;

public class AdvanceAgreementModel {
    private int id;
    private String terms;
    private double kilometersDriven;
    private String damage;
    private int damagePrice;
    private HashMap<String, Integer> refusalHashMap;
    private double extendKilometerPrice;
    private double refusalTotalPrice;
    private Double purchasePrice;
    private String pickUpLocation;

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

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getDamagePrice() {
        return damagePrice;
    }

    public void setDamagePrice(int damagePrice) {
        this.damagePrice = damagePrice;
    }

    public HashMap<String, Integer> getRefusalHashMap() {
        return refusalHashMap;
    }

    public void setRefusalHashMap(HashMap<String, Integer> refusalHashMap) {
        this.refusalHashMap = refusalHashMap;
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

