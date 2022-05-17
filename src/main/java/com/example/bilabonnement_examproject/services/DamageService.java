package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.CarRepo;

import java.util.ArrayList;
import java.util.List;

public class DamageService {
    private CRUDInterface<DamageReportModel, Integer> damageRepo;


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

    public List<DamageReportModel> showAllDamagesForCar(String chassisNumber){
        ArrayList<DamageReportModel> damageList = new ArrayList<DamageReportModel>();
        for (DamageReportModel damages:damageRepo.getAllEntities()
             ) {
            if (Objects.equals(damages.getChassisNumber(), chassisNumber)){
                assert false;
                damageList.add(damages);
            }
        }
        return damageList;
    }
}
