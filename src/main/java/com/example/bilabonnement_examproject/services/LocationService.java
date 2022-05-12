package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.repositories.LocationRepo;

import java.util.ArrayList;
import java.util.List;

public class LocationService {

    //lav unit test
    public boolean isLocationValid(String city, String address, int postcode){
        LocationRepo locationRepo = new LocationRepo();
        List<LocationModel> locations = locationRepo.getAllEntities();
        LocationModel locationModel = null;



        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getCity().equalsIgnoreCase(city) && locations.get(i).getAddress().equalsIgnoreCase(address)
            && locations.get(i).getPostcode()==postcode){

                locationModel = locations.get(i);

            } else{
            }
        }

        if (locationModel == null){
            return false;
        } else {
            return true;
        }
    }
}
