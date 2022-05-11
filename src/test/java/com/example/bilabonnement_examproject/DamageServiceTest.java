package com.example.bilabonnement_examproject;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repository.CarRepoTest;
import com.example.bilabonnement_examproject.repository.DamageReportRepoTest;
import com.example.bilabonnement_examproject.services.CarService;
import com.example.bilabonnement_examproject.services.DamageService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DamageServiceTest {


    @Test
    void createDamageReport() {

        String stringWith255Chars = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
   "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
   "aaaaaaaaaaaaaaaaaaaaaaaaabbbb";

        //Arrange
        CRUDInterface<DamageReportModel> testRepo = new DamageReportRepoTest();
        DamageService damageService = new DamageService(testRepo);
        boolean expectedDescriptionIsOnlyDigits = false;
        boolean expectedDescriptionIsEmpty = false;
        boolean expectedDescriptionOneLetter = false;
        boolean expectedDescriptionTwoLetters = false;
        boolean expectedDescriptionThreeLetters = true;
        boolean expectedDescriptionFourLetters = true;
        boolean expectedDescriptionIsJustOverMax = false;
        boolean expectedDescriptionIsOnMax = true;
        boolean expectedDescriptionIsJustUnderMax = true;
        boolean expectedAllIsEmpty = false;
        boolean expectedPriceIsJustOverMax = false;
        boolean expectedPriceIsOnMax = false;
        boolean expectedPriceIsJustUnderMax = true;
        boolean expectedPriceIsBelowZero = false;
        boolean expectedPriceIsZero = false;
        boolean expectedPriceIsJustOverZero = true;
        boolean expectedPriceIsNormalPrice = true;


        //Act
        boolean descriptionIsOnlyDigits = damageService.createDamageReport(
                new DamageReportModel("1234567890",100));
        boolean descriptionIsEmpty = damageService.createDamageReport(
                new DamageReportModel("",100));
        boolean descriptionIsOneLetter = damageService.createDamageReport(
                new DamageReportModel("i",100));
        boolean descriptionIsTwoLetters = damageService.createDamageReport(
                new DamageReportModel("po",100));
        boolean descriptionIsThreeLetters = damageService.createDamageReport(
                new DamageReportModel("dor",100));
        boolean descriptionIsFourLetters = damageService.createDamageReport(
                new DamageReportModel("dore",100));
        boolean descriptionIsJustOverMax = damageService.createDamageReport(
                new DamageReportModel(stringWith255Chars +"aa",100));
        boolean descriptionIsOnMax = damageService.createDamageReport(
                new DamageReportModel(stringWith255Chars + "a",100));
        boolean descriptionIsJustUnderMax = damageService.createDamageReport(
                new DamageReportModel(stringWith255Chars,100));
        boolean allIsEmpty = damageService.createDamageReport(
                new DamageReportModel("",0));
        boolean priceIsJustOverMax = damageService.createDamageReport(
                new DamageReportModel("Døren er lås i hænglserne",9999999+1)); //husk at ændre int(9) price i databasen
        boolean priceIsOnMax = damageService.createDamageReport(
                new DamageReportModel("Bagruden",99999999));
        boolean priceIsJustUnderMax = damageService.createDamageReport(
                new DamageReportModel("Udstødningen hænger meget ",9999999-1));
        boolean priceIsBelowZero = damageService.createDamageReport(
                new DamageReportModel("sidespejle er smadret",-1));
        boolean priceIsZero = damageService.createDamageReport(
                new DamageReportModel("karrosseri skal skiftes",0));
        boolean priceIsJustOverZero = damageService.createDamageReport(
                new DamageReportModel("hul i taget",1));
        boolean priceIsNormalPrice = damageService.createDamageReport(
                new DamageReportModel("bilradio væk/stjålet?",255));

        //Assert
        assertEquals(expectedDescriptionIsOnlyDigits,descriptionIsOnlyDigits);
        assertEquals(expectedDescriptionIsEmpty,descriptionIsEmpty);
        assertEquals(expectedDescriptionOneLetter,descriptionIsOneLetter);
        assertEquals(expectedDescriptionTwoLetters,descriptionIsTwoLetters);
        assertEquals(expectedDescriptionThreeLetters,descriptionIsThreeLetters);
        assertEquals(expectedDescriptionFourLetters,descriptionIsFourLetters);
        assertEquals(expectedDescriptionIsJustOverMax,descriptionIsJustOverMax);
        assertEquals(expectedDescriptionIsOnMax,descriptionIsOnMax);
        assertEquals(expectedDescriptionIsJustUnderMax,descriptionIsJustUnderMax);
        assertEquals(expectedAllIsEmpty,allIsEmpty);
        assertEquals(expectedPriceIsJustOverMax,priceIsJustOverMax);
        assertEquals(expectedPriceIsOnMax,priceIsOnMax);
        //assertEquals(expectedPriceIsJustUnderMax,priceIsJustUnderMax);
        assertEquals(expectedPriceIsBelowZero,priceIsBelowZero);
        assertEquals(expectedPriceIsZero,priceIsZero);
        //assertEquals(expectedPriceIsJustOverZero,priceIsJustOverZero);
        //assertEquals(expectedPriceIsNormalPrice,priceIsNormalPrice);

    }


}
