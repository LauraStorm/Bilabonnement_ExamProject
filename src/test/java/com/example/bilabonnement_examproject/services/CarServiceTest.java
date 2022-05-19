/*
package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import org.junit.jupiter.api.Test;
import repository.CarRepoTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    @Test
    void getRentedCars() {
        //Arrange
        CRUDInterface<CarModel, String> testCarsRepo = new CarRepoTest();
        CarService carService = new CarService(testCarsRepo);

        /*
        ArrayList<CarModel> carModelArrayList = new ArrayList<CarModel>(
                Arrays.asList(
                        new CarModel("52931102953019407", "Opel Crossland Sport 110 HK", "Yellow", true),
                        new CarModel("52938102853019408", "Peugeot 3008 Allure Pack 130 HK AUT", "Black", false),
                        new CarModel("52738102953019402", "Peugeot 2008 Allure PACK 130 HK LTD.", "Orange", true),
                        new CarModel("52938102953619401", "DS 7 Performance Line Pack 130HK", "Blue", false),
                        new CarModel("52938102953019403", "Peugeot 3008 GT 130HK AUT.", "Grey", true),
                        new CarModel("52538102953019401", "Citroën C3 Triumph Benzin 83 HK", "Grey", false),
                        new CarModel("52938152953019400", "Ny Peugeot 208 Active Pack 75 HK", "Green", true),
                        new CarModel("52938105953010406", "Ny C3 Le Mans PureTech 83 HK", "White", true),
                        new CarModel("52938162953019402", "Citroën Grand C4 Spacetourer COOL 130 HK", "Red", true))
        );

         */

        //Act
        /*
        List<CarModel> valid = new ArrayList<CarModel>(
                Arrays.asList(
                        new CarModel("52931102953019407", "Opel Crossland Sport 110 HK", "Yellow", true),
                        new CarModel("52738102953019402", "Peugeot 2008 Allure PACK 130 HK LTD.", "Orange", true),
                        new CarModel("52938102953019403", "Peugeot 3008 GT 130HK AUT.", "Grey", true),
                        new CarModel("52938152953019400", "Ny Peugeot 208 Active Pack 75 HK", "Green", true),
                        new CarModel("52938105953010406", "Ny C3 Le Mans PureTech 83 HK", "White", true),
                        new CarModel("52938162953019402", "Citroën Grand C4 Spacetourer COOL 130 HK", "Red", true)



        ));

         */

        /*
        List<CarModel> invalid = new ArrayList<CarModel>(
                Arrays.asList(
                        new CarModel("52931102953019407", "Opel Crossland Sport 110 HK", "Yellow", false),
                        new CarModel("52738102953019402", "Peugeot 2008 Allure PACK 130 HK LTD.", "Orange", false),
                        new CarModel("52938102953019403", "Peugeot 3008 GT 130HK AUT.", "Grey", false),
                        new CarModel("52938152953019400", "Ny Peugeot 208 Active Pack 75 HK", "Green", true),
                        new CarModel("52938105953010406", "Ny C3 Le Mans PureTech 83 HK", "White", true))

        );


        List<CarModel> validCarRented = carService.getRentedCars(carModelArrayList);

        List<CarModel> validateIsRented = carService.getRentedCars(valid);

        List<CarModel> validateIsNotRented = carService.getRentedCars(invalid);


        //Assert
        assertEquals(validateIsRented.size(), validCarRented.size());  //Denne er godkendt!
        assertEquals(validateIsNotRented, validCarRented.size()); //Denne skal fejle

    }
}
*/