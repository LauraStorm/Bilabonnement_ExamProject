package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import utility.DatabaseConnectionManager;

import java.sql.Connection;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
