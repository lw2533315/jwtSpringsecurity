package com.service;

import com.model.User;
import org.springframework.security.core.AuthenticationException;

public interface UserService {
    User findByUsername(String username);
    void save(User user);
    String login(User user) throws AuthenticationException;

}
