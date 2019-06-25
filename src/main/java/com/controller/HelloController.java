package com.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
//@CrossOrigin("http:localhost/4200")
@RequestMapping("/auth")
public class HelloController {
    @GetMapping("/")
    public String hello(){
        System.out.println("in home");
        return "home";
    }
}
