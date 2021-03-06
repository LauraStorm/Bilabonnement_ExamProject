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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public void setAccountNumber(String accountNumber) {
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
