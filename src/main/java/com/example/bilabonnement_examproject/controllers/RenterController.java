package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.models.RenterModel;
import com.example.bilabonnement_examproject.repositories.RenterRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class RenterController {

    @GetMapping("/create-renter-information")
    public String getCreateRenterPage(HttpSession session){
        //System.out.println("from renter " + session.getAttribute("chassisSession"));

        //RenterModel renterModel = new RenterModel("tim", "tim", "kea", 6089, "kbh", "jsjds@jskdk.ksdk", 12345678, 982939000, 92892003, 82839923);
        //RenterRepo renterRepo = new RenterRepo();
        //renterRepo.createEntity(renterModel);
        return "create-renter-information";
    }

    @PostMapping("/get-renter-information")
    public String getRenterDetails(WebRequest dataFromForm, HttpSession session){
        RenterRepo renterRepo = new RenterRepo();
        RenterModel renterModel = null;

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

        renterModel = new RenterModel(firstName, lastName, address, Integer.parseInt(postcode),
                city, email, Integer.parseInt(tlf), Integer.parseInt(cpr), Integer.parseInt(regNumber),
                Integer.parseInt(accountNumber));

        renterRepo.createEntity(renterModel);

        int renterId = renterRepo.getRenterId(renterModel);

        session.setAttribute("renterId", renterId);

        return "redirect:/register-location";
    }
}
