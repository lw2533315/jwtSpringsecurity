package com.controller;

import com.model.JwtUser;
import com.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://loalhost:4200", maxAge = 3600)
@RequestMapping("/token")
public class TokenController {

    @Autowired
    JwtGenerator jwtGenerator;
    //generate token
    @PostMapping
    public String generate(@RequestBody JwtUser jwtuser){
        return jwtGenerator.generate(jwtuser);
    }
}
