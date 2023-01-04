package com.example.emmanager.controller;

import com.example.emmanager.model.User;
import com.example.emmanager.security.PasswordConfig;
import com.example.emmanager.security.jwt.JwtUtils;
import com.example.emmanager.security.payload.JwtResponse;
import com.example.emmanager.security.payload.LoginRequest;
import com.example.emmanager.security.payload.SignupRequest;
import com.example.emmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        User user = new User(
                signupRequest.getUsername(),
                passwordConfig.passwordEncoder().encode(signupRequest.getPassword())
        );
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
       // user.setJobs(null);


        return ResponseEntity.ok(new JwtResponse(
                jwt,
                user.getId(),
                user.getUsername()
        ));
    }

}
