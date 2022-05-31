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
        ArrayList<CarModel> carList = new ArrayList<CarModel>();
        try {
            Connection conn = DatabaseConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cars");
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int wagonNumber = rs.getInt("wagon_number");
                    String chassisNumber = rs.getString("chassis_number");
                    String manufacturer = rs.getString("manufacturer");
                    String color = rs.getString("color");
                    String model = rs.getString("model");
                    boolean isRented = rs.getBoolean("rented");
                    boolean isSold = rs.getBoolean("sold");
                    String equipmentLevel = rs.getString("equipment_level");
                    double steelPrice = rs.getDouble("steel_price");
                    double registrationFee = rs.getDouble("registration_fee");
                    double carbonEmission = rs.getDouble("carbon_emission");
                    assert false;
                    carList.add(new CarModel(chassisNumber,wagonNumber,manufacturer,model,color,equipmentLevel,
                            steelPrice,registrationFee,carbonEmission,isRented,isSold));
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Something went wrong!");
        }
        return carList;
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
            int wagonNumber = rs.getInt("wagon_number");
            String chassisNumber = rs.getString("chassis_number");
            String manufacturer = rs.getString("manufacturer");
            String color = rs.getString("color");
            String model = rs.getString("model");
            boolean isRented = rs.getBoolean("rented");
            boolean isSold = rs.getBoolean("sold");
            String equipmentLevel = rs.getString("equipment_level");
            double steelPrice = rs.getDouble("steel_price");
            double registrationFee = rs.getDouble("registration_fee");
            double carbonEmission = rs.getDouble("carbon_emission");
            assert false;
            car = new CarModel(chassisNumber,wagonNumber,manufacturer,model,color,equipmentLevel,
                    steelPrice,registrationFee,carbonEmission,isRented,isSold);
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
    public boolean updateEntity(CarModel entity) {

        String updateCar = "UPDATE cars SET rented =?, sold=?, registration_fee=?, color=?, steel_price=? WHERE chassis_number =?";
        try {
            PreparedStatement pstmt = DatabaseConnectionManager.getConnection().prepareStatement(updateCar);
            pstmt.setBoolean(1,entity.isRented());
            pstmt.setBoolean(2,entity.isSold());
            pstmt.setDouble(3,entity.getRegistrationFee());
            pstmt.setString(4,entity.getColor());
            pstmt.setDouble(5,entity.getSteelPrice());
            pstmt.setString(6,entity.getChassisNumber());
            int i = pstmt.executeUpdate();
            System.out.println(i+ "records inserted");
            return true;
        }
        catch (SQLException sqlException) {
            System.out.println("Something went wrong!");
            sqlException.printStackTrace();
            return false;
        }
    }

    /*
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

     */
}
