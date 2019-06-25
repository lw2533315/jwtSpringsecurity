package com.controller;

import com.jwt.JwtTokenUtil;
import com.model.JwtUser;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value ="/auth" , method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class AuthenticationController {
    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;




    @PostMapping("/emp")
    public void signup(@RequestBody User user){
        System.out.println("login method");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);

    }

    //check the login user with db and generate string
    @PostMapping("/emplogin")
    public String empLogin(@RequestBody User user){
        System.out.println("in login");
        return userService.login(user);

    }
}
