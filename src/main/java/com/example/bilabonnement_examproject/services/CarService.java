package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;
import net.bytebuddy.description.type.TypeList;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarService {
    private CRUDInterface<CarModel,String> carRepo;

    public CarService(CRUDInterface carRepo){
       this.carRepo = carRepo;
    }

    public CarService() {

    }

    public String selectChassisNumberPost(CarModel car, int key, RedirectAttributes attributes){
        String result = "";
        if (!car.getChassisNumber().equals("Vælg: Stelnummer") && !car.isSold()) {
            switch (key) {
                case 1: {
                    return  "redirect:/damage?chassisnumber=" + car.getChassisNumber();
                }
                case 2: {
                    return "redirect:/registeradvanceagreement?chassisnumber=" + car.getChassisNumber() + "&issold=false";
                }
                case 3: {
                    return  "redirect:/returncarsuccesspage?chassisnumber=" + car.getChassisNumber();
                }
                case 4:{
                    return "redirect:/registeradvanceagreement?chassisnumber=" + car.getChassisNumber() + "&issold=true";
                }
                default: {
                    attributes.addFlashAttribute("error", "Noget gik galt!");
                    result = "redirect:/selectchassisnumber?key=" + key;
                }
            }
        } else {
            switch (key) {
                case 1:
                case 4:
                case 2: {
                    attributes.addFlashAttribute("error", "Vælg venligst en mulighed!");
                    return  "redirect:/selectchassisnumber?key=" + key;
                }
                case 3: {
                    attributes.addFlashAttribute("error", "Vælg venligst en mulighed!");
                    result = "redirect:/selectchassisnumberreturn?key=" + key;
                }
            }
        }
        return result;
    }


    public String getChassisNumberPost(String chassisNumberFromForm, RedirectAttributes attributes, HttpSession session){
        String result = "";

        // kan være en metode
        String errorRented = "Bilen er udlejet";
        String errorTypo = "Stelnummeret er forkert";


        assert chassisNumberFromForm != null;
        if (isChassisNumberValid(chassisNumberFromForm)) {

            //hvis chassis number er valid og bilen er updatet bliver man sendt videre
            session.setAttribute("chassisSession", chassisNumberFromForm);
            CarModel car = carRepo.getSingleEntity(chassisNumberFromForm);
            car.setRented(true);
            carRepo.updateEntity(car); // opdaterer rented status til "true"
            result = "redirect:/create-renter-information";

        } else if(chassisNumberFromForm.length() != 17) {
            attributes.addFlashAttribute("error", errorTypo);//errormessage?
            result = "redirect:/register-car";

        } else {
            attributes.addFlashAttribute("error", errorRented);//errormessage?
            //hvis chassis number ikke er valid bliver useren på siden
            result = "redirect:/register-car";
        }
        return result;
    }


    public String submitCarToFleetPost(CarModel car, RedirectAttributes attributes){
        String result = "";
        if (car.getChassisNumber().isEmpty() ||
                car.getModel().isEmpty()) {
            attributes.addFlashAttribute("errormessage", "Alle felter skal være udfyldt!");
            result = "redirect:/registernewcartofleet";
        } else {
            addNewToFleet(car);
            result = "car-register-purchase-result";
        }
        return result;
    }


    public void selectCarToReturn(Model model,int key){
        model.addAttribute("key",key);
        model.addAttribute("availablecars",fillCarListWithADummyOption(
                getRentedCarsToReturn()));
    }

    public String returnCarSuccesPage(String chassisNumber, Model model){
        CarModel car = carRepo.getSingleEntity(chassisNumber);
        car.setRented(false);
        carRepo.updateEntity(car);
        model.addAttribute("car", car);
        return "return-car-success";
    }



// TODO NUMBER AND CHAR CERTAIN PLACES
    public boolean isChassisNumberValid(String chassisNumber) {
        CarRepo carRepo = new CarRepo();

        boolean isValid = false;
        if (chassisNumber.length() == 17 && chassisNumber.matches("[0-9]+")) {

                if (!carRepo.getSingleEntity(chassisNumber).isRented()) {
                    isValid = true;
                }
            }
        return isValid;
    }

    public List<CarModel> getUnrentedCars(List<CarModel> allCars){
        List<CarModel> UnrentedCars = new ArrayList<CarModel>();

        for (int i = 0; i < allCars.size(); i++) {
            if (allCars.get(i).isRented() == false){
                UnrentedCars.add(allCars.get(i));
            } else {

            }
        }
        return UnrentedCars;
    }

    public List<CarModel> getRentedCars(List<CarModel> allCars){
        List<CarModel> rentedCars = new ArrayList<CarModel>();

        for (int i = 0; i < allCars.size(); i++) {
            if (allCars.get(i).isRented() == true){
                rentedCars.add(allCars.get(i));
            } else {

            }
        }
        return rentedCars;
    }

    public String convertBooleanTermsToString(boolean isRented){
        if (isRented == true){
            return "ja";
        } else {
            return "nej";
        }
    }

    public boolean addNewToFleet(CarModel car) {
        try {
            carRepo.createEntity(car);
            return true;
        } catch (Exception exception) {
            System.out.println("Something went wrong inserting!");
            return false;

        }
    }

    public ArrayList<CarModel> getAllUnsoldCars(){
        ArrayList<CarModel> unsoldCarsList = new ArrayList<CarModel>();
        for (CarModel cars:carRepo.getAllEntities()
             ) {
            if (!cars.isSold()){
                unsoldCarsList.add(cars);
            }
        }
        return unsoldCarsList;
    }

    public ArrayList<CarModel> getRentedCarsToReturn(){
        ArrayList<CarModel> rentedCarsList = new ArrayList<CarModel>();
        for (CarModel cars:carRepo.getAllEntities()) {
            if (cars.isRented()){
                rentedCarsList.add(cars);
            }
        }
        return rentedCarsList;
    }


    public ArrayList<CarModel> getAllUnrentedCars(){
        CarRepo carRepo = new CarRepo();
        ArrayList<CarModel> unrentedCars = new ArrayList<CarModel>();
        for (CarModel cars:carRepo.getAllEntities()
        ) {
            if (!cars.isRented()){
                unrentedCars.add(cars);
            }
        }
        return unrentedCars;
    }

    public ArrayList<CarModel> getAllRentedCars(){
        CarRepo carRepo = new CarRepo();
        ArrayList<CarModel> rentedCars = new ArrayList<CarModel>();
        for (CarModel cars:carRepo.getAllEntities()
        ) {
            if (cars.isRented()){
                rentedCars.add(cars);
            }
        }
        return rentedCars;
    }

    public boolean isCarUnrented(String chassisNumber) {
        CarRepo carRepo = new CarRepo();
        boolean isCarUnrented = false;
        ArrayList<CarModel> unrentedCars = new ArrayList<CarModel>();
        for (CarModel cars : carRepo.getAllEntities()
        ) {
            if (!cars.isRented()) {
                unrentedCars.add(cars);
                for (CarModel carsUn : unrentedCars
                ) {
                    if (Objects.equals(carsUn.getChassisNumber(), chassisNumber)) {
                        isCarUnrented =  true;
                    } else {
                        isCarUnrented =  false;
                    }
                }
            }
        }
        return isCarUnrented;
    }


    public ArrayList<CarModel> fillCarListWithADummyOption(ArrayList<CarModel> carArray){
        ArrayList<CarModel> carModelArrayListExtended = new ArrayList<CarModel>();
        carModelArrayListExtended.add(new CarModel("Vælg: Stelnummer","Farve","Model","Mærke"));
        carModelArrayListExtended.addAll(carArray);
        return carModelArrayListExtended;

    }

    public CarModel changeRentedStatus(CarModel car){
        carRepo.updateEntity(car);
        return carRepo.getSingleEntity(car.getChassisNumber());
    }


}

