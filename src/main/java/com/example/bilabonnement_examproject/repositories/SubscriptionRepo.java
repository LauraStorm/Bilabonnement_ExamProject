package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionRepo implements CRUDInterface<SubscriptionModel, Integer>{
    @Override
    public List<SubscriptionModel> getAllEntities() {
        ArrayList<SubscriptionModel> allSubscriptions = new ArrayList<SubscriptionModel>();


        try {
            Connection conn = DatabaseConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT  * FROM subscriptions");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int  id = rs.getInt("id");
                boolean selfRisk = rs.getBoolean("selfrisk");
                boolean deliveryInsurance = rs.getBoolean("delivery_insurance");
                int totalPrice = rs.getInt("total_price_pr_md");
                int length = rs.getInt("length");
                String subscriptionType = rs.getString("subscription_type");
                String chassisNumber = rs.getString("cars_chassis_number");
                int locationID = rs.getInt("locations_id");
                int rentersId = rs.getInt("renters_id");
                String pickUpDate = rs.getString("pickup_date");
                String deliveryDate = rs.getString("delivery_date");

                SubscriptionModel subscription = new SubscriptionModel(id,selfRisk,deliveryInsurance,totalPrice,length,subscriptionType,chassisNumber,locationID,rentersId,pickUpDate, deliveryDate);
                allSubscriptions.add(subscription);

            }
            System.out.println("All Subscriptions is found!");
        } catch (SQLException e) {
            System.out.println("Something went wrong with finding all subscriptions");
            e.printStackTrace();
        }
        return allSubscriptions;
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
                    "length, subscription_type, cars_chassis_number, locations_id, renters_id, pickup_date, delivery_date)" +
                    "VALUES (?,?,?,?,?,?,?,?,?, ?)");
            stmt.setBoolean(1,entity.isSelfrisk());
            stmt.setBoolean(2,entity.isDeliveryInsurance());
            stmt.setInt(3,entity.getTotalPriceMd());
            stmt.setInt(4,entity.getLength());
            stmt.setString(5,entity.getSubscriptionType());
            stmt.setString(6,entity.getChassisNumber());
            stmt.setInt(7,entity.getLocationId());
            stmt.setInt(8, entity.getRentersId());
            stmt.setString(9, entity.getPickupDate());
            stmt.setString(10,entity.getDeliveryDate());

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
