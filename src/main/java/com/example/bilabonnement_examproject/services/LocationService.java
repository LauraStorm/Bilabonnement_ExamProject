package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.repositories.LocationRepo;

import java.util.ArrayList;
import java.util.List;

public class LocationService {

    public boolean isLocationValid(String city, String address, int postcode){
        LocationRepo locationRepo = new LocationRepo();
        List<LocationModel> locations = locationRepo.getAllEntities();
        boolean isValid = true;

        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getCity() == city && locations.get(i).getAddress() == address
            && locations.get(i).getPostcode()==postcode){
                isValid = true;
            } else{
                isValid = false;
            }
        }

        return isValid;
    }
}
