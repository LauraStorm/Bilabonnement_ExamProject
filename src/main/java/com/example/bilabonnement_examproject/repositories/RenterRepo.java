package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.RenterModel;

import java.util.List;

public class RenterRepo implements CRUDInterface<RenterModel>{
    @Override
    public List<RenterModel> getAllEntities() {
        return null;
    }

    @Override
    public RenterModel getSingleEntity(Object id) {
        return null;
    }

    @Override
    public boolean createEntity(RenterModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(String key) {
        return false;
    }
}
