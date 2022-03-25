package com.colpatria.akp.demo.controllers;
import java.util.List;
import com.colpatria.akp.demo.dtos.*;
import com.colpatria.akp.demo.entities.User;
import com.colpatria.akp.demo.security.JwtUtility;
import com.colpatria.akp.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.jsonwebtoken.Claims;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService _userService;

    @GetMapping("/users")
    public List<User> findAll(){
        return _userService.findAll();
    }

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUserByToken(@RequestHeader (name="Authorization") String token){
        Claims claim = JwtUtility.getClaimsFromTokenString(token);
        User user = _userService.findByUsername(claim.getSubject().toString());
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return ResponseEntity.ok(new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getName(),
            user.getLastname(),
            user.getCreatedAt(),
            user.getUpdatedAt())
        );
    }

    @RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUser(@PathVariable int userId, @RequestHeader (name="Authorization") String token){
        Claims claim = JwtUtility.getClaimsFromTokenString(token);
        UserResponse user = _userService.findById(userId);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found userId: "+userId);
        }
        if(!user.getUsername().equals(claim.getSubject().toString())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public User Post(@RequestBody UserRequest user){
        var result = _userService.findByUsername(user.lastname);
        if(result != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the username must be unique");

        User newUser = new User(0, user.name, user.lastname, user.password);
        _userService.save(newUser);
        return newUser;
    }
}