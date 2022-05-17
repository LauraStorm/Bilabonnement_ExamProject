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
import java.util.List;

@Controller
public class CarController {
    private CarService carService = new CarService(new CarRepo());

    @GetMapping("/selectchassisnumber")
    public String carIdForm(Model model, @RequestParam(value = "key") int key) {
        model.addAttribute("key",key);
        model.addAttribute("availablecars",carService.fillCarListWithADummyOption(carService.getAllUnsoldCars()));
        model.addAttribute("car",new CarModel());
        return "select-chassisnumber-of-cars";
    }

    @PostMapping("/selectchassisnumber")
    public String carIdSubmit(RedirectAttributes attributes, Model model,
                              @ModelAttribute CarModel car, @RequestParam(value = "key") int key) {
        model.addAttribute("car", car);
        if (!car.getChassisNumber().equals("Stelnummer")) {
        switch (key) {
            case 1: {
                return "redirect:/damage?chassisnumber=" + car.getChassisNumber();
            }
            case 2: {
                return "redirect:/registeradvanceagreement?chassisnumber=" + car.getChassisNumber();
            }
            default: {
                attributes.addFlashAttribute("error", "Noget gik galt!");
                return "redirect:/selectchassisnumber?key="+key;
            }
        }
        } else {
            attributes.addFlashAttribute("error","Vælg venligst en mulighed!");
            return "redirect:/selectchassisnumber?key="+key;
        }
    }


    @GetMapping("/register-car")
    public String getCarDetails(){
        return "register-car";
    }

    @PostMapping("/get-chassis-number")
    public String getChassisNumber(WebRequest dataFromForm, HttpSession session, RedirectAttributes attributes){
        String result = "";
        CarRepo carRepo = new CarRepo();
        CarService carService = new CarService();
        String chassisNumberFromForm = dataFromForm.getParameter("chassis-number");
        String errorRented = "Bilen er udlejet";
        String errorTypo = "Stelnummeret er forkert";


        assert chassisNumberFromForm != null;
        if (carService.isChassisNumberValid(chassisNumberFromForm)) {

            //hvis chassis number er valid og bilen er updatet bliver man sendt videre
            session.setAttribute("chassisSession", chassisNumberFromForm);
            boolean isUpdated = carRepo.updateEntity(chassisNumberFromForm);
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

    @GetMapping("/rented-cars")
    public String getAllRentedCarsPage(Model car){
        CarRepo carRepo = new CarRepo();
        CarService carService = new CarService();
        List<CarModel> rentedCars= carService.getRentedCars(carRepo.getAllEntities());

        car.addAttribute("rentedCars", rentedCars);

        return "view-leased-cars";
    }

}
