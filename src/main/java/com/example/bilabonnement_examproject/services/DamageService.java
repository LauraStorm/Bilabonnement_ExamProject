package com.example.bilabonnement_examproject.services;
import com.example.bilabonnement_examproject.models.AdvanceAgreementModel;
import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.AdvanceAgreementRepo;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.CarRepo;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//udarbejdet af Simon & Elisa
public class DamageService {
    private CRUDInterface<DamageReportModel, Integer> damageRepo;
    private AdvanceAgreementRepo advanceAgreementRepo = new AdvanceAgreementRepo();


    public DamageService(CRUDInterface damageRepo){
        this.damageRepo = damageRepo;
    }


    public boolean createDamageReport(DamageReportModel report){
        if (report.getPrice() > 0 && !String.valueOf(report.getPrice()).isEmpty()
        && !report.getDefectDescription().isEmpty()
        && report.getDefectDescription().length() > 2 && report.getDefectDescription().length() < 256
        && String.valueOf(report.getPrice()).length() < 11) {
            damageRepo.createEntity(report);
            return true;
        } else {
            return false;
        }
    }

    public List<DamageReportModel> showDamagesForACar(Model model, String chassisNumber){
        ArrayList<DamageReportModel> damageList = new ArrayList<DamageReportModel>();
        for (DamageReportModel damages:damageRepo.getAllEntities()
             ) {
            if (Objects.equals(damages.getChassisNumber(), chassisNumber)){
                assert false;
                damageList.add(damages);
            }
        }
        model.addAttribute("damagesforcar",damageList);
        return damageList;
    }

    public String damageDataFormPost(DamageReportModel damage, String chassisNumber, RedirectAttributes attributes){
        String result = "";
        damage.setChassisNumber(chassisNumber);
        if (damage.getDefectDescription().isEmpty()) {
            attributes.addFlashAttribute("error","Der mangler en beskrivelse!");
            result = "redirect:/damage?chassisnumber=" + chassisNumber;
        } else if (damage.getPrice() == 0.0){
            attributes.addFlashAttribute("error","Der mangler en pris!");
            result = "redirect:/damage?chassisnumber=" + chassisNumber;
        } else {
            createDamageReport(damage);
            result = "result-damage";
        }
        return result;
    }
}