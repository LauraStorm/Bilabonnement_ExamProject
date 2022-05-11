package repository;

import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DamageReportRepoTest implements CRUDInterface<DamageReportModel, Integer> {
    private ArrayList<DamageReportModel> damageReportModelsList  = new ArrayList<DamageReportModel>(
            Arrays.asList(
                    new DamageReportModel(1, "Manglende Sidespejl", 799, "52938102853019408"),
                    new DamageReportModel(2, "Totalskadet forrude", 999, "52931102953019407"),
                    new DamageReportModel(3, "Bule i førrerdøren", 222, "52938152953019400"),
                    new DamageReportModel(4, "Punktering i venstre baghjul", 400, "52931102953019407"),
                    new DamageReportModel(5, "Langtlys i højre side defekt", 111, "52931102953019407"),
                    new DamageReportModel(6, "Kofanger skæv", 540, "52931102953019407"),
                    new DamageReportModel(7, "Udstødningsrøret hænger lavt", 111, "52938152953019400"),
                    new DamageReportModel(8, "Airbag udløst", 2000, "52938162953019402"))
    );

    @Override
    public List<DamageReportModel> getAllEntities() {
        return damageReportModelsList;
    }

    @Override
    public DamageReportModel getSingleEntity(Integer id) {
        DamageReportModel fetchedDamage = null;
        for (DamageReportModel damage:damageReportModelsList
        ) {
            if (damage.getId() == id) {
                fetchedDamage = damage;
            }
        }
        return fetchedDamage;
    }

    @Override
    public boolean createEntity(DamageReportModel entity) {
        if (!entity.getDefectDescription().isEmpty() && !entity.getChassisNumber().isEmpty() &&
                entity.getPrice() > 0 && entity.getId() > 0) {
            damageReportModelsList.add(entity);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean updateEntity(Integer key) {
        return false;
    }
}

