package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.LocationModel;

import java.util.List;

public class LocationRepo implements CRUDInterface<LocationModel>{
    @Override
    public List<LocationModel> getAllEntities() {
        return null;
    }

    @Override
    public LocationModel getSingleEntity(int id) {
        return null;
    }

    @Override
    public boolean createEntity(LocationModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(int key) {
        return false;
    }

    public LocationModel getSingleEntityByCityAndZipcode(String city, int ZipCode){return null; }
}
