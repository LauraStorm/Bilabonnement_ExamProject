package repository;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarRepoTest implements CRUDInterface<CarModel, String> {
    private ArrayList<CarModel> carModelArrayList = new ArrayList<CarModel>(
            Arrays.asList(
                    /*
            new CarModel("52931102953019407", "Opel Crossland Sport 110 HK", "Yellow", true),
            new CarModel("52938102853019408", "Peugeot 3008 Allure Pack 130 HK AUT", "Black", false),
            new CarModel("52738102953019402", "Peugeot 2008 Allure PACK 130 HK LTD.", "Orange", true),
            new CarModel("52938102953619401", "DS 7 Performance Line Pack 130HK", "Blue", false),
            new CarModel("52938102953019403", "Peugeot 3008 GT 130HK AUT.", "Grey", true),
            new CarModel("52538102953019401", "Citroën C3 Triumph Benzin 83 HK", "Grey", false),
            new CarModel("52938152953019400", "Ny Peugeot 208 Active Pack 75 HK", "Green", true),
            new CarModel("52938105953010406", "Ny C3 Le Mans PureTech 83 HK", "White", true),
            new CarModel("52938162953019402", "Citroën Grand C4 Spacetourer COOL 130 HK", "Red", true))

                     */
            ));

    @Override
    public List<CarModel> getAllEntities() {
        return carModelArrayList;
    }

    @Override
    public CarModel getSingleEntity(String id) {
        CarModel fetchedCar = null;
        for (CarModel car:carModelArrayList
        ) {
            if (car.getChassisNumber().equals(String.valueOf(id))) {
                fetchedCar = car;
            }
        }
        return fetchedCar;
    }



    @Override
    public boolean createEntity(CarModel entity) {
        if (!entity.getChassisNumber().isEmpty() && !entity.getModel().isEmpty() &&
        !entity.getColor().isEmpty() && !entity.isRented()) {
            carModelArrayList.add(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateEntity(CarModel car) {
        CarModel fetchedCar = null;
        for (CarModel cars:carModelArrayList) {
            if (cars.getChassisNumber().equals(String.valueOf(car.getChassisNumber()))) {
                fetchedCar = cars;
                if (cars.isRented()) {
                    fetchedCar.setRented(false);
                } else {
                    fetchedCar.setRented(true);
                }
                return true;
            }
        }
        return false;
    }
}
