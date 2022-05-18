package com.example.bilabonnement_examproject.controllers;

import com.example.bilabonnement_examproject.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
/*
    @GetMapping("/")
    public String getloginPage(){
        return "index";
    }

 */

    @PostMapping("/")
    public String getLoginInfo (WebRequest dataFromForm, HttpSession session, RedirectAttributes errorMessage){
        LoginService loginService = new LoginService();
       // session.invalidate();
        //Gem data
        String username = dataFromForm.getParameter("username");
        String password = dataFromForm.getParameter("password");

        //tjek valid
        boolean isLoginValid = loginService.isLoginValid(username,password);



        if (isLoginValid == true){
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

    @GetMapping("/homepage")
    public String getHomePage(){
        return "homepage";
    }
}
