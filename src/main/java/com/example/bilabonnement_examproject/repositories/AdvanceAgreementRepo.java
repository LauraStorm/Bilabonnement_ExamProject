package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.AdvanceAgreementModel;
import com.example.bilabonnement_examproject.models.DamageReportModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvanceAgreementRepo implements CRUDInterface<AdvanceAgreementModel, Integer> {
    @Override
    public List<AdvanceAgreementModel> getAllEntities() {
        ArrayList<AdvanceAgreementModel> allAgreements = new ArrayList<AdvanceAgreementModel>();
        Connection connection = DatabaseConnectionManager.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM advance_agreement");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String chassisNumber = rs.getString("cars_chassis_number");
                String terms = rs.getString("terms");
                int kilometerDriven = rs.getInt("kilometer_driven");
                int extendKilometer = rs.getInt("extend_kilometer");
                int refusalPrice = rs.getInt("refusal_price");
                int purchasePrice = rs.getInt("purchase_price");
                int locationsId = rs.getInt("locationsid");

                allAgreements.add(
                        new AdvanceAgreementModel(id,chassisNumber,terms,kilometerDriven,
                                extendKilometer,refusalPrice,purchasePrice,locationsId));


            }
            System.out.println("All damages found");

        } catch (SQLException e) {
            System.out.println("something went wrong");
            e.printStackTrace();
        }
        return allAgreements;
    }

    @Override
    public AdvanceAgreementModel getSingleEntity(Integer integer) {
        return null;
    }

    @Override
    public boolean createEntity(AdvanceAgreementModel entity) {
        String createDamage = "INSERT INTO advance_agreement (id, chassis_number, kilometer_driven, terms, extend_kilometer, " +
                "refusal_price, purchase_price, locationsid)  VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = DatabaseConnectionManager.getConnection().prepareStatement(createDamage);
            pstmt.setInt(1, entity.getId());
            pstmt.setString(2, entity.getChassisNumber());
            pstmt.setInt(3,entity.getKilometersDriven());
            pstmt.setString(4, entity.getTerms());
            pstmt.setInt(5,entity.getExtendKilometer());
            pstmt.setInt(6,entity.getRefusalPrice());
            pstmt.setInt(7,entity.getPurchasePrice());
            pstmt.setInt(8,entity.getLocationId());

            int i = pstmt.executeUpdate();
            System.out.println(i + "records inserted");
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Something went wrong!");
            return false;
        }
    }

    @Override
    public boolean updateEntity(Integer key) {
        return false;
    }
}
