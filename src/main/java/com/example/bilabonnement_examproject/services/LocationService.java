package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.repositories.LocationRepo;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//udarbejdet af Simon & Laura & Ramus
public class LocationService {
    LocationRepo locationRepo = new LocationRepo();

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

    public ArrayList<LocationModel> getSelectLocationListForView() {
        ArrayList<LocationModel> locationModelsWithExtra = new ArrayList<LocationModel>();
        locationModelsWithExtra.add(new LocationModel("Vælg adresse", "Vælg by", -1, -1));
        locationModelsWithExtra.addAll(locationRepo.getAllEntities());
        return locationModelsWithExtra;
    }

    public String locationsDetilsSelectPost(HttpSession session, LocationModel location,
                                            RedirectAttributes attributes){
        String errorMessage = "Lokation skal vælges";
        session.setAttribute("locationIdSession", location.getId());
        if (location.getId() == 0) {

            attributes.addFlashAttribute("error", errorMessage);

            return "redirect:/register-location";

        } else {
            return "redirect:/create-subscription";
        }
    }
}
