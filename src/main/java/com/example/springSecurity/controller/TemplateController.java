package com.example.springSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

@RequestMapping("/")
@Controller
public class TemplateController {


    @GetMapping("login")
    public String getLogin(){
        return "login";
    }
    @GetMapping("courses")
    public String getCourses(){
        return "courses";
    }

}
