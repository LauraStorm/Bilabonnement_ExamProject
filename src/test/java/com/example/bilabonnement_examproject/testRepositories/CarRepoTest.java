package com.example.bilabonnement_examproject.testRepositories;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarRepoTest implements CRUDInterface<CarModel, String> {
    ArrayList<CarModel> carModelArrayList = new ArrayList<CarModel>(
            Arrays.asList(
                    new CarModel("WAD31102953019407",32123,"Opel","Crossland Sport 110 HK","Yellow","Business",321,1421,11,false,false),
                    new CarModel("DRE38102853019408", 652311,"Peugeot", "3008 Allure Pack 130 HK AUT", "Black","Sport",412,1234,12, false,false),
                    new CarModel("SAS38102953019402",5431, "Peugeot","2008 Allure PACK 130 HK LTD.", "Orange","Discover",124,124,54, false, true),
                    new CarModel("DOO38102953019402",1124, "Citroën","7 Performance Line Pack 130HK", "Orange","Discover",124,124,54, false, true),
                    new CarModel("PRE38102953019402",4124, "Peugeot","3008 GT 130HK AUT.", "Orange","Discover",124,124,54, false, true),
                    new CarModel("FVT38102953019402",454321, "Citroën","C3 Triumph Benzin 83 HK", "Orange","Discover",124,124,54, false, true),
                    new CarModel("GLW38102953019402",76543, "Peugeot","208 Active Pack 75 HK", "Orange","Discover",124,124,54, false, true),
                    new CarModel("AVS38102953019402",213412, "Citroën","C3 Le Mans PureTech 83 HK", "Orange","Discover",124,124,54, false, true),
                    new CarModel("ACC38102953019402",343221, "Citroën","Grand C4 Spacetourer COOL 130 HK", "Orange","Discover",124,124,54, false, true)
    ));


    @Override
    public List<CarModel> getAllEntities() {
        return carModelArrayList;
    }

    @Override
    public CarModel getSingleEntity(String s) {
        CarModel fetchedCar = null;
        for (CarModel car:carModelArrayList
        ) {
            if (car.getChassisNumber().equals(String.valueOf(s))) {
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
    public boolean updateEntity(CarModel entity) {
            CarModel fetchedCar = null;
            for (CarModel cars:carModelArrayList) {
                if (cars.getChassisNumber().equals(String.valueOf(entity.getChassisNumber()))) {
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
