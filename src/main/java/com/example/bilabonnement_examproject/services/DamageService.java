package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;

public class DamageService {
    private final CRUDInterface<DamageReportModel> damageRepo;

    public DamageService(CRUDInterface damageRepo){
        this.damageRepo = damageRepo;
    }

    public boolean createDamageReport(DamageReportModel report){
        if (report.getPrice() > 0 && !String.valueOf(report.getPrice()).isEmpty()
        && !report.getDefectDescription().isEmpty() && report.getDefectDescription().matches("[a-zA-Z]+")
        && report.getDefectDescription().length() > 2 && report.getDefectDescription().length() < 256
        && String.valueOf(report.getPrice()).length() < 11) {
            return true;
        } else {
            return false;
        }
    }
}
