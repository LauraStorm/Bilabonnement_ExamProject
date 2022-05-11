package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.RenterModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RenterRepo implements CRUDInterface<RenterModel>{
    @Override
    public List<RenterModel> getAllEntities() {
        return null;
    }

    @Override
    public RenterModel getSingleEntity(Object id) {
        return null;
    }

    @Override
    public boolean createEntity(RenterModel entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO renters(first_name, last_name, adress, postcode, city, email, " +
                    "tlf_number, cpr_number, reg_number, account_number) VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, entity.getFirstName());
            stmt.setString(2,entity.getLastName());
            stmt.setString(3, entity.getAddress());
            stmt.setInt(4, entity.getPostcode());
            stmt.setString(5, entity.getCity());
            stmt.setString(6, entity.getEmail());
            stmt.setInt(7,entity.getTlf());
            stmt.setInt(8, entity.getCpr());
            stmt.setInt(9, entity.getRegNumber());
            stmt.setInt(10, entity.getAccountNumber());

            stmt.executeUpdate();
            System.out.println("renter created");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("renter not created");
        }

        return false;
    }

    @Override
    public boolean updateEntity(String key) {
        return false;
    }
}
