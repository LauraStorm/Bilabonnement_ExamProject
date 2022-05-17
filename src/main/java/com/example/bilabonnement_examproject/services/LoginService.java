package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.LoginModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.LoginRepo;

import java.util.List;

public class LoginService {
    private CRUDInterface<LoginModel,Integer> loginRepo = new LoginRepo();

    public LoginService (CRUDInterface loginRepo){
        this.loginRepo = loginRepo;
    }

    public LoginService (){
    }

    public boolean isLoginValid (String username, String password){
        List<LoginModel> allLogin = loginRepo.getAllEntities();

        for (LoginModel aLogin : allLogin) {
            String aUserName = aLogin.getUsername();
            String aPassword = aLogin.getPassword();

            if (username.equals(aUserName)  && password.equals(aPassword)){
                return true;
            }

        }
        return false;
    }
}
