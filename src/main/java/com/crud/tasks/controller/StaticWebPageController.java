package com.crud.tasks.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//@CrossOrigin(origins = "*")
@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, String> model){
        model.put("varialble", "My Thymeleaf variable");
        return "index";
    }
}
