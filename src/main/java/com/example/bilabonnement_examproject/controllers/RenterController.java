package com.example.bilabonnement_examproject.controllers;
import com.example.bilabonnement_examproject.models.RenterModel;
import com.example.bilabonnement_examproject.repositories.RenterRepo;
import com.example.bilabonnement_examproject.services.RenterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class RenterController {

    @GetMapping("/create-renter-information")
    public String getCreateRenterPage(){
        return "create-renter-information";
    }


	@PostMapping("/get-renter-information")
    public String getRenterDetails(WebRequest dataFromForm, HttpSession session,  RedirectAttributes attributes){
        RenterRepo renterRepo = new RenterRepo();
        RenterService renterService = new RenterService();
        RenterModel renterModel = null;
        String errorMessage = "Invalid bruger data";

        String firstName = dataFromForm.getParameter("firstName");
        String lastName = dataFromForm.getParameter("lastName");
        String address = dataFromForm.getParameter("address");
        String postcode = dataFromForm.getParameter("postcode");
        String city = dataFromForm.getParameter("city");
        String email = dataFromForm.getParameter("email");
        String tlf = dataFromForm.getParameter("tlf");
        String cpr = dataFromForm.getParameter("cpr");
        String regNumber = dataFromForm.getParameter("regNumber");
        String accountNumber = dataFromForm.getParameter("accountNumber");

        if (regNumber ==""){
            regNumber = "0";
        }
        //validere inputs og redirecter til næste side hvis de er valide ellers bliver
        //man på siden
        if (renterService.isAccountNumberValid(Integer.parseInt(regNumber), accountNumber) &&
        renterService.isCprValid(cpr) && renterService.isEmailValid(email) &&
        renterService.isPostCodeValid(Integer.parseInt(postcode)) &&
        renterService.isRegNumberValid(Integer.parseInt(regNumber)) &&
        renterService.isTlfValid(Integer.parseInt(tlf))){

            renterModel = new RenterModel(firstName, lastName, address, Integer.parseInt(postcode),
                    city, email, Integer.parseInt(tlf), cpr, Integer.parseInt(regNumber),
                    accountNumber);

            renterRepo.createEntity(renterModel);

            int renterId = renterRepo.getRenterId(renterModel);

            session.setAttribute("renterId", renterId);

            return "redirect:/register-location";
        } else {
            attributes.addFlashAttribute("error", errorMessage);
            return "redirect:/create-renter-information";
        }
    }
}