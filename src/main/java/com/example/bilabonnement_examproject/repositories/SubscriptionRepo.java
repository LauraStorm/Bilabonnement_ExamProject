package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.SubscriptionModel;

import java.util.List;

public class SubscriptionRepo implements CRUDInterface<SubscriptionModel>{
    @Override
    public List<SubscriptionModel> getAllEntities() {
        return null;
    }

    @Override
    public SubscriptionModel getSingleEntity(int id) {
        return null;
    }

    @Override
    public boolean createEntity(SubscriptionModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(int key) {
        return false;
    }
}
