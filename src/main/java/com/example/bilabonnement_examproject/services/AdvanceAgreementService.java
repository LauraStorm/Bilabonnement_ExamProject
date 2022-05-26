package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.AdvanceAgreementModel;
import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.AdvanceAgreementRepo;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import com.example.bilabonnement_examproject.repositories.DamageRepo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

//udarbejdet af Simon & Elisa
public class AdvanceAgreementService {
    private AdvanceAgreementRepo agreementRepo = new AdvanceAgreementRepo();
    private CarRepo carRepo = new CarRepo();
    private DamageRepo damageRepo = new DamageRepo();
    private DamageService damageService = new DamageService(new DamageRepo());
    private LocationService locationService = new LocationService();



    public int getTotalRefusalPrice(String chassisNumber){
        int sum = 0;
        for (DamageReportModel damages:damageRepo.getAllEntities()
        ) {
            if (Objects.equals(damages.getChassisNumber(), chassisNumber)){
                sum += damages.getPrice();
            }
        }
        return sum;
    }

    public String getAdvanceAgreement(Model model, String chassisNumber, boolean isSold) {
        AdvanceAgreementModel advanceAgreement = agreementRepo.getSingleEntity(chassisNumber);
        CarModel car = carRepo.getSingleEntity(chassisNumber);
        if (Objects.equals(advanceAgreement.getChassisNumber(), chassisNumber)){
            model.addAttribute("advanceagreement", advanceAgreement);
            damageService.showDamagesForACar(model,chassisNumber);
            model.addAttribute("car", car);
            if (isSold){
                car.setSold(true);
                carRepo.updateEntity(car);
                model.addAttribute("header","Bilen er nu solgt");
                return "register-advance-agreement-result";
            } else {
                model.addAttribute("header","Forhåndsaftale er nu oprettet");
                return "register-advance-agreement-result";
            }
        } else  {
                model.addAttribute("chassisnumber", chassisNumber);
                model.addAttribute("locations", locationService.getSelectLocationListForView());
                return "register-advance-agreement";
        }
    }

    public boolean createAgreement(AdvanceAgreementModel advanceAgreement, String chassisNumber) {
        try {
            advanceAgreement.setChassisNumber(chassisNumber);
            int extendKilometersPrice = (int) (advanceAgreement.getExtendKilometer() * 0.75);
            advanceAgreement.setRefusalPrice(getTotalRefusalPrice(chassisNumber) + extendKilometersPrice);
            agreementRepo.createEntity(advanceAgreement);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String postAdvanceAgreement(AdvanceAgreementModel advanceAgreement,
                                       RedirectAttributes attributes,
                                       String chassisNumber,
                                       Model model){
        String result = "";
        model.addAttribute("car",new CarRepo().getSingleEntity(chassisNumber));
        damageService.showDamagesForACar(model,chassisNumber);
        if (advanceAgreement.getTerms().isEmpty() && advanceAgreement.getLocationId() == 0) {
            attributes.addFlashAttribute("errormessage", "Vilkår og afhentningssted skal være udfyldt!");
            result = "redirect:/registeradvanceagreement?chassisnumber="+chassisNumber;
        } else {
                createAgreement(advanceAgreement, chassisNumber);
                result = "register-advance-agreement-result";
        }
        return result;
    }

}
