package com.example.bilabonnement_examproject.models;

public class LocationModel {
    private int id;
    private String address;
    private String city;
    private int postcode;
    private int deliveryPrice;

    public LocationModel(int id, String address, String city, int postcode, int deliveryPrice) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.deliveryPrice = deliveryPrice;
    }

    public LocationModel(String address, String city, int postcode, int deliveryPrice) {
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.deliveryPrice = deliveryPrice;
    }

    public LocationModel(String address, String city, int postcode) {
        this.address = address;
        this.city = city;
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postcode=" + postcode +
                ", deliveryPrice=" + deliveryPrice +
                '}';
    }
}
