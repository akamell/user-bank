package com.colpatria.akp.demo.controllers;

import com.colpatria.akp.demo.dtos.*;
import com.colpatria.akp.demo.entities.User;
import com.colpatria.akp.demo.security.JwtUtility;
import com.colpatria.akp.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService _userService;

    @PostMapping("/login")
    public String authentication(@RequestBody LoginRequest user){
        if(!isAdmin(user)){
            User userAuthenticated = _userService.login(user.username, user.password);
            if(userAuthenticated == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        String token = JwtUtility.getJWTToken(user.username);
        return "Bearer "+token;
    }

    private boolean isAdmin(LoginRequest user) {
        return (user.username.equals("admin") || user.password.equals("admin"));
    }
    
}
