package com.example.bilabonnement_examproject;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import repository.CarRepoTest;
import com.example.bilabonnement_examproject.services.CarService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarServiceTest {
    private CRUDInterface<CarModel,Integer> carRepo;

    public CarServiceTest(CRUDInterface carRepo){
        this.carRepo = carRepo;
    }

    public CarServiceTest() {

    }

    @Test
    void isChassisNumberValid() {


        //Arrange
        CRUDInterface<CarModel,Integer> testRepo = new CarRepoTest();
        CarService carService = new CarService(testRepo);
        boolean expectedIsNotFound = false;
        boolean expectedIs18DigitsLong = false;
        boolean expectedIsFound = true;
        boolean expectedIsOnlyDigits = true;
        boolean expectedIsLetter = false;


        //Act
        boolean isNotFound = carService.isChassisNumberValid("0000");
        boolean is18DigitsLong = carService.isChassisNumberValid("493023496503965912");
        boolean isFound = carService.isChassisNumberValid("52938152953019400");
        boolean isOnlyDigits = carService.isChassisNumberValid("52938102953619401");
        boolean isLetter = carService.isChassisNumberValid("A93023496I03965912");

        //Assert
        assertEquals(expectedIsNotFound,isNotFound);
        assertEquals(expectedIs18DigitsLong,is18DigitsLong);
        assertEquals(expectedIsFound,isFound);
        assertEquals(expectedIsOnlyDigits,isOnlyDigits);
        assertEquals(expectedIsLetter,isLetter);
    }
}
