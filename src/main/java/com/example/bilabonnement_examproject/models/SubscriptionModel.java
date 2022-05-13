package com.example.bilabonnement_examproject.models;

public class SubscriptionModel {
    private int id;
    private int locationId;
    private String chassisNumber;
    private int rentersId;
    private boolean selfrisk;
    private boolean deliveryInsurance;
    private int totalPriceMd;
    private int length;
    private String subscriptionType;
    private String pickupDate;
    private String deliveryDate;

    public SubscriptionModel(int id,boolean selfrisk,boolean deliveryInsurance, int totalPriceMd, int length, String subscriptionType,String chassisNumber, int locationId, int rentersId, String pickupDate, String deliveryDate) {
        this.id = id;
        this.locationId = locationId;
        this.chassisNumber = chassisNumber;
        this.rentersId = rentersId;
        this.selfrisk = selfrisk;
        this.deliveryInsurance = deliveryInsurance;
        this.totalPriceMd = totalPriceMd;
        this.length = length;
        this.subscriptionType = subscriptionType;
        this.pickupDate = pickupDate;
        this.deliveryDate = deliveryDate;
    }

    public SubscriptionModel(boolean selfrisk, boolean deliveryInsurance, int totalPriceMd, int length, String subscriptionType, String chassisNumber, int locationId,  int rentersId, String pickupDate, String deliveryDate) {
        this.locationId = locationId;
        this.chassisNumber = chassisNumber;
        this.rentersId = rentersId;
        this.selfrisk = selfrisk;
        this.deliveryInsurance = deliveryInsurance;
        this.totalPriceMd = totalPriceMd;
        this.length = length;
        this.subscriptionType = subscriptionType;
        this.pickupDate = pickupDate;
        this.deliveryDate = deliveryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public int getRentersId() {
        return rentersId;
    }

    public void setRentersId(int rentersId) {
        this.rentersId = rentersId;
    }

    public boolean isSelfrisk() {
        return selfrisk;
    }

    public void setSelfrisk(boolean selfrisk) {
        this.selfrisk = selfrisk;
    }

    public boolean isDeliveryInsurance() {
        return deliveryInsurance;
    }

    public void setDeliveryInsurance(boolean deliveryInsurance) {
        this.deliveryInsurance = deliveryInsurance;
    }

    public int getTotalPriceMd() {
        return totalPriceMd;
    }

    public void setTotalPriceMd(int totalPriceMd) {
        this.totalPriceMd = totalPriceMd;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "SubscriptionModel{" +
                "id=" + id +
                ", locationId=" + locationId +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", rentersId=" + rentersId +
                ", selfrisk=" + selfrisk +
                ", deliveryInsurance=" + deliveryInsurance +
                ", totalPriceMd=" + totalPriceMd +
                ", length=" + length +
                ", subscriptionType='" + subscriptionType + '\'' +
                ", pickupDate='" + pickupDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                '}';
    }
}
