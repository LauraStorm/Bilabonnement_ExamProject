package repositories;

import models.RenterModel;

import java.util.List;

public class RenterRepo implements CRUDInterface<RenterModel>{
    @Override
    public List<RenterModel> getAllEntities() {
        return null;
    }

    @Override
    public RenterModel getSingleEntity(int id) {
        return null;
    }

    @Override
    public boolean createEntity(RenterModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(int id) {
        return false;
    }
}
