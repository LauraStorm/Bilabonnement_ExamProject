package com.example.bilabonnement_examproject.models;

public class DamageReportModel {
    private int id;
    private String defectDescription;
    private double price;
    private String chassisNumber;


    public DamageReportModel(String defectDescription, double price) {
        this.defectDescription = defectDescription;
        this.price = price;
    }

    public DamageReportModel(int id, String defectDescription, double price, String chassisNumber) {
        this.id = id;
        this.defectDescription = defectDescription;
        this.price = price;
        this.chassisNumber = chassisNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefectDescription() {
        return defectDescription;
    }

    public void setDefectDescription(String defectDescription) {
        this.defectDescription = defectDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    @Override
    public String toString() {
        return "com.example.bilabonnement_examproject.models.DamageReportRepo{" +
                "id=" + id +
                ", defectDescription='" + defectDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
