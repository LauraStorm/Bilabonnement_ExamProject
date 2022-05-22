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
    private LoginService loginService = new LoginService();


    @PostMapping("/")
    public String getLoginInfo (WebRequest dataFromForm, HttpSession session, RedirectAttributes errorMessage){
       return loginService.loginPage(dataFromForm,errorMessage,session);
    }

	@GetMapping("/homepage")
    public String getHomePage(HttpSession session){
        return "homepage";
    }
}
