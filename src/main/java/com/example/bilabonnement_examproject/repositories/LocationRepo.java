package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Laura og Rasmus
public class LocationRepo implements CRUDInterface<LocationModel, Integer>{
    @Override
    public List<LocationModel> getAllEntities() {
        ArrayList<LocationModel> allLocations = new ArrayList<LocationModel>();
        Connection connection = DatabaseConnectionManager.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM locations");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String postcode = rs.getString("postcode");
                int deliveryPrice = rs.getInt("delivery_price");

                LocationModel location = new LocationModel(id,address,city,Integer.parseInt(postcode),deliveryPrice);
                allLocations.add(location);
            }

            System.out.println("All locations found");
        } catch (SQLException e) {
            System.out.println("something went wrong with findig all locations");
            e.printStackTrace();
        }
        return allLocations;
    }

    @Override
    public LocationModel getSingleEntity(Integer id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        LocationModel location = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM locations WHERE id=?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            int locationId = rs.getInt("id");
            String address = rs.getString("address");
            String city =rs.getString("city");
            int postcode = rs.getInt("postcode");
            int deliveryPrice = rs.getInt("delivery_price");

            location = new LocationModel(locationId,address,city,postcode,deliveryPrice);
            return location;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createEntity(LocationModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(LocationModel location) {
        return false;
    }
}
