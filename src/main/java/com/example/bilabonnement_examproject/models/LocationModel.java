package com.example.bilabonnement_examproject.models;

//Laura og Rasmus
public class LocationModel {
    private int id;
    private String address;
    private String city;
    private int postcode;
    private int deliveryPrice;

    public LocationModel() {
    }

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

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public int getPostcode() {
        return postcode;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
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
