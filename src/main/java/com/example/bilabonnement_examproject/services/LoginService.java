package com.example.bilabonnement_examproject.services;

import com.example.bilabonnement_examproject.models.CarModel;
import com.example.bilabonnement_examproject.models.LoginModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;
import com.example.bilabonnement_examproject.repositories.LoginRepo;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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


    public String loginPage(WebRequest dataFromForm, RedirectAttributes errorMessage
    , HttpSession session) {
        //session.invalidate();

        //Gem data
        String username = dataFromForm.getParameter("username");
        String password = dataFromForm.getParameter("password");

        //tjek valid
        boolean isLoginValid = isLoginValid(username, password);

        if (isLoginValid == true) {
            //Return "homepage" if true
            session.setAttribute("password", password);
            return "redirect:/homepage";
        } else {
            //Return redirect: index + besked
            errorMessage.addFlashAttribute("error", "Brugernavn eller password er ikke gyldig");
            System.out.println("Error message - Brugernavn eller password er ikke gyldig");
            return "redirect:/";
        }
    }
}
