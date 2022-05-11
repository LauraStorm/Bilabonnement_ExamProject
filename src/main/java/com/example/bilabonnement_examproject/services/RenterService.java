package com.example.bilabonnement_examproject.services;

public class RenterService {

    public boolean isPostCodeValid(int postcode){
        if (String.valueOf(postcode).length() == 4){
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmailValid(String email) {
        // regular expresion
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        if (email.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTlfValid(int tlf){
        if (String.valueOf(tlf).length() == 8){
            return true;
        } else {
            return false;
        }
    }

    //skal have et regex så den kun kan indeholde tal og -
    public boolean isCprValid(String cpr){
        if (cpr.length() == 11 && cpr.charAt(6)=='-'){
            return true;
        } else {
            return false;
        }
    }

    public boolean isRegNumberValid(int regNumber){
        if (String.valueOf(regNumber).length() == 4){
            return true;
        } else {
            return false;
        }
    }

    //tjekke at regnumber er de 4 første tal i accountnumber
    public boolean isAccountNumberValid(int regNumber, String accountNumber){
        if (accountNumber.length() == 10 && accountNumber.contains(String.valueOf(regNumber))){
            return true;
        }else{
            return false;
        }
    }
}
