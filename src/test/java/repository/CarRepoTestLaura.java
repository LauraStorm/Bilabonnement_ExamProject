package repository;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarRepoTestLaura implements CRUDInterface<CarModel,String> {
    @Override
    public List<CarModel> getAllEntities() {
        ArrayList<CarModel> allCars = new ArrayList<CarModel>(
                Arrays.asList(
                        new CarModel("11111111111111111","citroën c3 triumph", "hvid",false),
                        new CarModel("22222222222222222","citroën c3 triumph", "sort",true),
                        new CarModel("33333333333333333","opel crossland sport", "white jade",true),
                        new CarModel("44444444444444444","peugeot 2008 allure PACK", "sort onyx",true),
                        new CarModel("55555555555555555","peugeot 2008 allure PACK", "platinium",true)
                )
        );
        return allCars;
    }

    @Override
    public CarModel getSingleEntity(String s) {
        return null;
    }

    @Override
    public boolean createEntity(CarModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(String key) {
        return false;
    }
}
