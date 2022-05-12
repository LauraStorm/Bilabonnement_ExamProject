package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.DamageRepo;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import java.sql.PreparedStatement;

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
}
