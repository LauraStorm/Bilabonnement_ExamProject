package com.example.bilabonnement_examproject.models;

//Laura og Rasmus
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

    //henter fra DB
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
//opretter til DB
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

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public int getRentersId() {
        return rentersId;
    }

    public boolean isSelfrisk() {
        return selfrisk;
    }

    public String getSelfriskString (boolean isSelfrisk){
        if (isSelfrisk == true){
            return "Ja";
        } else {
            return "nej";
        }
    }

    public boolean isDeliveryInsurance() {
        return deliveryInsurance;
    }

    public String getIsDeliveryInsuranceAsString (boolean isDeliveryInsurance){
        if (isDeliveryInsurance == true){
            return "Ja";
        } else {
            return "Nej";
        }
    }

    public int getTotalPriceMd() {
        return totalPriceMd;
    }

    public int getLength() {
        return length;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
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
