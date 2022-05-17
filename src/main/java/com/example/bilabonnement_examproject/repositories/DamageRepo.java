package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;
import org.yaml.snakeyaml.events.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DamageRepo implements CRUDInterface<DamageReportModel, Integer> {

    @Override
    public List<DamageReportModel> getAllEntities() {
        ArrayList<DamageReportModel> allDamages = new ArrayList<DamageReportModel>();
        Connection connection = DatabaseConnectionManager.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM defects");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String defectDescription = rs.getString("defect_description");
                int price = rs.getInt("price");
                String carsChassisNumber = rs.getString("cars_chassis_number");

                DamageReportModel damageReportModel = new DamageReportModel(id, defectDescription, price, carsChassisNumber);
                allDamages.add(damageReportModel);

            }
            System.out.println("All damages found");

        } catch (SQLException e) {
            System.out.println("something went wrong");
            e.printStackTrace();
        }
        return allDamages;
    }

    @Override
    public DamageReportModel getSingleEntity(Integer integer) {
        return null;
    }

    @Override
    public boolean createEntity(DamageReportModel entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into defects (defect_description, price, cars_chassis_number) values (?,?,?)");
            stmt.setString(1, entity.getDefectDescription());
            stmt.setInt(2, (int) entity.getPrice());
            stmt.setString(3, entity.getChassisNumber());
            int i = stmt.executeUpdate();
            System.out.println(i + " reports inserted!");
            return true;
        } catch (SQLException sqlException) {
            System.out.println("something went wrong!");
            sqlException.printStackTrace();
            return false;
        }
    }

        @Override
    public boolean updateEntity(Integer key) {
        return false;
    }

    public DamageReportModel getDamageReportModel(String defectDescription, int price){

        DamageReportModel reportModel = null;

        Connection conn = DatabaseConnectionManager.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM defects WHERE defect_description=? AND price=?");
            stmt.setString(1,defectDescription);
            stmt.setInt(2, price);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            String defectDescription1 = rs.getString("defect_description");
            int price1 = rs.getInt("price");
            String chassisNumber1 = rs.getString("cars_chassis_number");

            reportModel = new DamageReportModel(id, defectDescription1, price1, chassisNumber1);
            System.out.println(reportModel);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Damage Report not found");
        }

        return reportModel;
    }
}
