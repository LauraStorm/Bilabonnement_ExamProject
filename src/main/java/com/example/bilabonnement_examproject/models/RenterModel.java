package com.example.bilabonnement_examproject.models;

//Laura og Rasmus
public class RenterModel {
    private int id;
    private String firstName;
    private  String lastName;
    private String address;
    private int postcode;
    private String city;
    private String email;
    private int tlf;
    private String cpr;
    private int regNumber;
    private String accountNumber;

    //henter fra DB
    public RenterModel(int id, String firstName, String lastName, String address, int postcode, String city, String email, int tlf, String cpr, int regNumber, String accountNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.email = email;
        this.tlf = tlf;
        this.cpr = cpr;
        this.regNumber = regNumber;
        this.accountNumber = accountNumber;
    }

    //opretter til DB
    public RenterModel(String firstName, String lastName, String address, int postcode, String city, String email, int tlf, String cpr, int regNumber, String accountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.email = email;
        this.tlf = tlf;
        this.cpr = cpr;
        this.regNumber = regNumber;
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public int getTlf() {
        return tlf;
    }

    public String getCpr() {
        return cpr;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "RenterModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", postcode=" + postcode +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", tlf=" + tlf +
                ", cpr=" + cpr +
                ", regNumber=" + regNumber +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
