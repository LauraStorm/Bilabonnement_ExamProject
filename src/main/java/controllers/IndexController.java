package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import utility.DatabaseConnectionManager;

public class IndexController {

    @GetMapping("/")
    public String getIndex(){
        DatabaseConnectionManager.getConnection();
        return "index";
    }
}
