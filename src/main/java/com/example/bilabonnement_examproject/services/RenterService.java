package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.RenterModel;
import com.example.bilabonnement_examproject.repositories.RenterRepo;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

public class RenterService {
    private RenterRepo renterRepo = new RenterRepo();

    public boolean isPostCodeValid(int postcode){
        if (String.valueOf(postcode).length() == 4){
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmailValid(String email) {
        // regular expresion
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        if (email.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTlfValid(int tlf){
        if (String.valueOf(tlf).length() == 8){
            return true;
        } else {
            return false;
        }
    }

    //skal have et regex så den kun kan indeholde tal og -
    public boolean isCprValid(String cpr){
        if (cpr.length() == 11 && cpr.charAt(6)=='-'){
            return true;
        } else {
            return false;
        }
    }

    public boolean isRegNumberValid(int regNumber){
        if (String.valueOf(regNumber).length() == 4){
            return true;
        } else {
            return false;
        }
    }

    //tjekke at regnumber er de 4 første tal i accountnumber
    public boolean isAccountNumberValid(int regNumber, String accountNumber){
        if (accountNumber.length() == 10 && accountNumber.contains(String.valueOf(regNumber))){
            return true;
        }else{
            return false;
        }
    }

    public String renterServicePost(WebRequest dataFromForm, HttpSession session, RedirectAttributes attributes){
        RenterModel renterModel = null;
        String errorMessage = "Oplysninger ikke korrekt";

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
        if (isAccountNumberValid(Integer.parseInt(regNumber), accountNumber) &&
                isCprValid(cpr) && isEmailValid(email) &&
                isPostCodeValid(Integer.parseInt(postcode)) &&
                isRegNumberValid(Integer.parseInt(regNumber)) &&
                isTlfValid(Integer.parseInt(tlf))){

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
