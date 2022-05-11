package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionRepo implements CRUDInterface<SubscriptionModel, Integer>{
    @Override
    public List<SubscriptionModel> getAllEntities() {
        return null;
    }

    @Override
        public SubscriptionModel getSingleEntity(Integer id) {
        return null;
    }

    @Override
    public boolean createEntity(SubscriptionModel entity) {
        Connection conn = DatabaseConnectionManager.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO subscriptions(selfrisk, delivery_insurance, total_price_pr_md," +
                    "length, subscription_type, cars_chassis_number, locations_id, renters_id, pickup_date)" +
                    "VALUES (?,?,?,?,?,?,?,?,?)");
            stmt.setBoolean(1,entity.isSelfrisk());
            stmt.setBoolean(2,entity.isDeliveryInsurance());
            stmt.setInt(3,entity.getTotalPriceMd());
            stmt.setInt(4,entity.getLength());
            stmt.setString(5,entity.getSubscriptionType());
            stmt.setString(6,entity.getChassisNumber());
            stmt.setInt(7,entity.getLocationId());
            stmt.setInt(8, entity.getRentersId());
            stmt.setString(9, entity.getPickupDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateEntity(Integer key) {
        return false;
    }
}
