package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.CarModel;

import java.util.List;

public class CarRepo implements CRUDInterface<CarModel>{
    @Override
    public List<CarModel> getAllEntities() {
        return null;
    }

    @Override
    public CarModel getSingleEntity(int id) {
        return null;
    }

    @Override
    public boolean createEntity(CarModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(int key) {
        return false;
    }
}
