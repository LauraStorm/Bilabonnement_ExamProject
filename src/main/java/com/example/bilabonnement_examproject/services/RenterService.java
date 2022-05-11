package com.example.bilabonnement_examproject.services;

public class RenterService {

    public boolean isPostCodeValid(int postcode){
        if (String.valueOf(postcode).length() == 4){
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmailValid(String email){

    }


}
