package com.example.bilabonnement_examproject.controllers;
import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class CarController {
    private CarService carService = new CarService(new CarRepo());


	@GetMapping("/selectchassisnumber")
    public String selectChassisNumberShow(Model model, @RequestParam("key") int key) {
        switch (key){
            case 1: {
                model.addAttribute("header","Registrer skader på en bil");
                model.addAttribute("actionname","Anmeld skader");
                break;
            }
            case 2: {
                model.addAttribute("header","Opret en forhåndsaftale på en bil");
                model.addAttribute("actionname","Opret aftale");
                break;
            }
            case 3: {
                model.addAttribute("header","Meld en bil tilbageleveret");
                model.addAttribute("actionname","Tilbagelever");
                break;
            }
            case 4:{
                model.addAttribute("header","Sælg følgende biler");
                model.addAttribute("actionname","Sælg");
                break;
            }
            default:{
                model.addAttribute("header","Behandel en bil");
                model.addAttribute("actionname","Videre");
                break;
            }
        }
        model.addAttribute("key",key);
        model.addAttribute("availablecars",carService.fillCarListWithADummyOption(carService.getAllUnsoldCars()));
        model.addAttribute("car",new CarModel());
        return "select-chassisnumber-of-cars";
    }


	@PostMapping("/selectchassisnumber")
    public String selectChassisNumberSubmit(RedirectAttributes attributes, Model model,
                              @ModelAttribute CarModel car, @RequestParam("key") int key
                                            ) {
        model.addAttribute("car", car);
        return carService.selectChassisNumberPost(car,key,attributes);
    }




    @GetMapping("/register-car")
    public String getCarDetails(){
        return "register-car";
    }


	@PostMapping("/get-chassis-number")
    public String getChassisNumber(WebRequest dataFromForm, HttpSession session, RedirectAttributes attributes){
        String chassisNumberFromForm = dataFromForm.getParameter("chassis-number");
        return carService.getChassisNumberPost(chassisNumberFromForm,attributes,session);
    }

	@GetMapping("/rented-cars")
    public String getAllRentedCarsPage(Model car, RedirectAttributes flashAttribute){
        CarRepo carRepo = new CarRepo();
        CarService carService = new CarService();

        List<CarModel> rentedCars= carService.getRentedCars(carRepo.getAllEntities());

        if (rentedCars.size() == 0){
            flashAttribute.addFlashAttribute("error", "Der er ingen udlejede biler");
            return "view-leased-cars";
        } else {
            car.addAttribute("rentedCars", rentedCars);
            return "view-leased-cars";
        }

    }


	@GetMapping("/all-cars")
    public String getAllCars (Model car){
        CarRepo carRepo = new CarRepo();
        List<CarModel> allCars = carRepo.getAllEntities();
        Collections.sort(allCars);                             //Sortere - Se compareTo Metode i CarModel class

        car.addAttribute("allCars",allCars);
        return "view-all-cars";
    }


	@GetMapping("/available-cars")
    public String getAvailableCars(Model car, RedirectAttributes flashAttribute){
        CarService carService = new CarService();
        CarRepo carRepo = new CarRepo();

        List<CarModel> allCars = carRepo.getAllEntities();
        List<CarModel> allAvailableCars = carService.getUnrentedCars(allCars);

        if (allAvailableCars.size() == 0){
            flashAttribute.addFlashAttribute("error", "Der ingen ledige biler");
            return "view-available-cars";
        } else {
            car.addAttribute("availableCars",allAvailableCars);
            return "view-available-cars";
        }



    }

}
