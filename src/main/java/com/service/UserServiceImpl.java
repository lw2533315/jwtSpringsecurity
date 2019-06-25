package com.service;

import com.jwt.JwtTokenUtil;
import com.model.User;
import com.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    MyRepository myRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public User findByUsername(String username) {
        return myRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        myRepository.save(user);
    }

    @Override
    public String login(User user) throws AuthenticationException {
        System.out.println("userserviceimpl   login   0");
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication auth = authenticationManager.authenticate(upToken);
        System.out.println("userserviceimpl   login   2");
        SecurityContextHolder.getContext().setAuthentication(auth);
        System.out.println("userserviceimpl   login   3");
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }

}
