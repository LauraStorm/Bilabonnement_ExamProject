package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.RenterModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//Laura og Rasmus
public class RenterRepo implements CRUDInterface<RenterModel, Integer>{

    @Override
    public List<RenterModel> getAllEntities() {
        return null;
    }

    @Override
    public RenterModel getSingleEntity(Integer id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        RenterModel renter = null;


        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM renters WHERE id=?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();


            rs.next();
            int renterId = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String address = rs.getString("adress");
            int postcode =rs.getInt("postcode");
            String city = rs.getString("city");
            String email = rs.getString("email");
            int tlf = rs.getInt("tlf_number");
            String cpr = rs.getString("cpr_number");
            int regNr =rs.getInt("reg_number");
            String accountNumber =rs.getString("account_number");


            renter = new RenterModel(renterId,firstName,lastName,address,postcode,city,email,tlf,cpr,regNr,accountNumber);
            System.out.println(renter);
            return renter;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("renter not found");
            return null;
        }
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
            stmt.setString(8, entity.getCpr());
            stmt.setInt(9, entity.getRegNumber());
            stmt.setString(10, entity.getAccountNumber());

            stmt.executeUpdate();
            System.out.println("renter created");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("renter not created");
        }

        return false;
    }

    @Override
    public boolean updateEntity(RenterModel renter) {
        return false;
    }


    public int getRenterId(RenterModel renterToFindId){
        Connection conn = DatabaseConnectionManager.getConnection();
        RenterModel renter = null;
        int id = 0;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM renters WHERE first_name=? AND last_name=? AND adress=?" +
                    "AND postcode=? AND city=? AND email=? AND tlf_number=? AND cpr_number=?" );
            stmt.setString(1,renterToFindId.getFirstName());
            stmt.setString(2,renterToFindId.getLastName());
            stmt.setString(3,renterToFindId.getAddress());
            stmt.setInt(4,renterToFindId.getPostcode());
            stmt.setString(5,renterToFindId.getCity());
            stmt.setString(6,renterToFindId.getEmail());
            stmt.setInt(7, renterToFindId.getTlf());
            stmt.setString(8, renterToFindId.getCpr());

            ResultSet rs = stmt.executeQuery();
            rs.next();
            id = rs.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
