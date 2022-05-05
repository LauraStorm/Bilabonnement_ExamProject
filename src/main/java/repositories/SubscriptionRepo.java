package repositories;

import models.SubscriptionModel;

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
    public boolean updateEntity(int id) {
        return false;
    }
}
