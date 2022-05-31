
package repository;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarRepoTestLaura implements CRUDInterface<CarModel,String> {
    ArrayList<CarModel> allCars = new ArrayList<CarModel>(
            Arrays.asList(
                    new CarModel("11111111111111111",123456789,"Supreme","citroën c3 triumph","hvid", "x",12,100,1,false,false),
                    new CarModel("22222222222222222",123456789,"Supreme","citroën c3 triumph","sort", "x",12,100,1,true,false),
                    new CarModel("33333333333333333",123456789,"Supreme","opel crossland sport","white jade", "x",12,100,1,true,false),
                    new CarModel("44444444444444444",123456789,"Supreme","peugeot 2008 allure PACK","sort onyx", "x",12,100,1,true,false),
                    new CarModel("55555555555555555",123456789,"Supreme","peugeot 2008 allure PACK","platinium", "x",12,100,1,true,false)
            )
    );

    @Override
    public List<CarModel> getAllEntities() {

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
    public boolean updateEntity(CarModel entity) {
        return false;
    }
}


