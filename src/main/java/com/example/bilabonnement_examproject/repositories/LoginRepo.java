package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.LoginModel;
import com.example.bilabonnement_examproject.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepo implements CRUDInterface<LoginModel,Integer>{

    @Override
    public List<LoginModel> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        ArrayList<LoginModel> allLogin = new ArrayList<LoginModel>();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int deptID = rs.getInt("departments_id");

                LoginModel login = new LoginModel(id,username,password,deptID);
                allLogin.add(login);
            }
            System.out.println("All login found");

        } catch (SQLException e) {
            System.out.println("Somethings wrong finding all logins");
            e.printStackTrace();
        }
        return allLogin;
    }

    @Override
    public LoginModel getSingleEntity(Integer key) {
        Connection conn = DatabaseConnectionManager.getConnection();
        LoginModel login = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login WHERE id=?");
            stmt.setInt(1,key);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int deptID = rs.getInt("departments_id");

            login = new LoginModel(id,username,password,deptID);
            System.out.println("Login found");
            return login;


        } catch (SQLException e) {
            System.out.println("Somethings went wrong finding single login");
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public boolean createEntity(LoginModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(Integer key) {
        return false;
    }
}
