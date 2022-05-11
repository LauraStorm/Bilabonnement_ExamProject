package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.LocationModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationRepo implements CRUDInterface<LocationModel, Integer>{
    @Override
    public List<LocationModel> getAllEntities() {
        return null;
    }

    @Override
    public LocationModel getSingleEntity(Integer id) {
        return null;
    }

    @Override
    public boolean createEntity(LocationModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(Integer key) {
        return false;
    }

    public LocationModel getSingleLocationByCityAndZipcode(String city, int postcode, String address){

        LocationModel location = null;

        Connection conn = DatabaseConnectionManager.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM locations WHERE address=? AND city=? AND postcode=?");
            stmt.setString(1,address);
            stmt.setString(2, city);
            stmt.setInt(3,postcode);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            String address1 = rs.getString("address");
            String city1 = rs.getString("city");
            int postcode1 = rs.getInt("postcode");
            int deliveryPrice = rs.getInt("delivery_price");



            location = new LocationModel(id, address1, city1, postcode1, deliveryPrice);
            System.out.println(location);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("location not found");
        }

        return location;
    }


}
