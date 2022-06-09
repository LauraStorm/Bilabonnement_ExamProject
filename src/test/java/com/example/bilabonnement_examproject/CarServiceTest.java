
package com.example.bilabonnement_examproject;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.services.CarService;
import com.example.bilabonnement_examproject.testRepositories.CarRepoTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith((MockitoExtension, class))
public class CarServiceTest {


    public CarModel getRandomCar(){
        CarRepoTest carRepoTest = new CarRepoTest();
        Random random = new Random();
        int rndInt = random.nextInt(carRepoTest.getAllEntities().size());
        return carRepoTest.getAllEntities().get(rndInt);
    }
    @Test
    void addNewToFleet(){
        CarService carService = new CarService(new CarRepoTest());

        //Arrange
        boolean expectedCarIsAdded = true;
        boolean expectedChassisNumberIsOver18Chars = false;
        boolean expectedManufacturerInValid = false;

        //Act
        CarModel carToCheck = getRandomCar();
        boolean isAdded = carService.addNewToFleet(carToCheck);
        carToCheck.setChassisNumber("527381029530194021");
        boolean isOver18chars = carService.addNewToFleet(carToCheck);
        carToCheck.setManufacturer("VVW");
        boolean manufacturerIsInValid = carService.addNewToFleet(carToCheck);

        //Assert
        assertEquals(expectedCarIsAdded,isAdded);
        assertEquals(expectedChassisNumberIsOver18Chars,isOver18chars);
        assertEquals(expectedManufacturerInValid,manufacturerIsInValid);

    }

/*
    @Test
    void isChassisNumberValid() {


        //Arrange
        CarRepoTest testRepo = new CarRepoTest();
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

 */
}


