package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepo implements CRUDInterface<CarModel, String>{
    @Override
    public List<CarModel> getAllEntities() {
        ArrayList<CarModel> allCars = new ArrayList<CarModel>();

        try {
            Connection conn = DatabaseConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cars");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String chassisNumber = rs.getString("chassis_number");
                String model = rs.getString("model");
                String color = rs.getString("color");
                boolean rented = rs.getBoolean("rented");

                CarModel car = new CarModel(chassisNumber,model,color,rented);
                allCars.add(car);
            }
            System.out.println("All cars is found");

        } catch (SQLException e) {
            System.out.println("something went wrong with finding all cars");
            e.printStackTrace();
        }

        return allCars;
    }

    @Override
    public CarModel getSingleEntity(String id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        CarModel car = null;


        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cars WHERE chassis_number=?");
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();


            rs.next();
            String chassisNumber = rs.getString("chassis_number");
            String model = rs.getString("model");
            String color = rs.getString("color");
            boolean isRented = rs.getBoolean("rented");

            car = new CarModel(chassisNumber, model, color, isRented);
            System.out.println(car);
            return car;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("chassis number not found");

            return null;
        }

    }

    @Override
    public boolean createEntity(CarModel entity) {
        String createDamage = "INSERT INTO cars (chassis_number, model, color, rented, wagon_number, manufacturer, " +
                "equipment_level, steel_price, registration_fee, carbon_emission, sold)  VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = DatabaseConnectionManager.getConnection().prepareStatement(createDamage);
            pstmt.setString(1,entity.getChassisNumber());
            pstmt.setString(2,entity.getModel());
            pstmt.setString(3,entity.getColor());
            pstmt.setBoolean(4,entity.isRented());
            pstmt.setInt(5,entity.getWagonNumber());
            pstmt.setString(6,entity.getManufacturer());
            pstmt.setString(7,entity.getEquipmentLevel());
            pstmt.setDouble(8,entity.getSteelPrice());
            pstmt.setDouble(9,entity.getRegistrationFee());
            pstmt.setDouble(10,entity.getCarbonEmission());
            pstmt.setBoolean(11,entity.isSold());

            int i = pstmt.executeUpdate();
            System.out.println(i+ "records inserted");
            return true;
        }
        catch (SQLException sqlException) {
            System.out.println("Something went wrong!");
            return false;
        }
    }

    @Override
    public boolean updateEntity(String key) {

        Connection conn = DatabaseConnectionManager.getConnection();
        CarModel car = getSingleEntity(key);
        boolean returnValue = true;

        //hvis bilen er fundet kan vi update isrented
        if (car != null) {

            boolean changeIsRented = true;

            if (car.isRented()) {
                // hvis bilen er rented bliver tinyint sat 0/false              = bilen er afleveret
                changeIsRented = false;
            } else if (!car.isRented()) {
                // hvis bilen ikke er rented bliver tinyint sat 1/true          = bilen er udlejet
                changeIsRented = true;
            } else {
                returnValue = false;
            }

            try {
                PreparedStatement stmt = conn.prepareStatement("UPDATE cars SET rented=? WHERE chassis_number=?");
                stmt.setBoolean(1, changeIsRented);
                stmt.setString(2, car.getChassisNumber());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("update fail");
            }
            //hvis car er null kan vi ikke update isrented
        }else if (car == null){
            returnValue = false;
        }


        return returnValue;
    }

    public CarModel changeRentedStatus(String chassisNumber) {

        Connection conn = DatabaseConnectionManager.getConnection();

        CarModel carModel = getSingleEntity(chassisNumber);

        boolean rentedStatus = true;

        if (carModel.isRented()){
            rentedStatus = false;
        }

        try {

            String sql = "UPDATE cars SET rented=" + rentedStatus + " WHERE chassis_number="+chassisNumber;
            PreparedStatement stmt = conn.prepareStatement(sql);

            int update = stmt.executeUpdate();

            if (update > 0){
                System.out.println("Update complete");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update fail");
        }

        return carModel;
    }
}
