package com.example.emmanager.service;
import com.example.emmanager.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    UserDetails loadUserByUsername(String username);
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long userId);
    User getLoggedUser();
}
