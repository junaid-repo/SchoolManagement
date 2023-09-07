package com.management.school.smusers.controller;

import com.management.school.smusers.dto.AuthRequest;
import com.management.school.smusers.dto.BaseOutput;
import com.management.school.smusers.entities.UserCredential;
import com.management.school.smusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sm/user")
public class UsersController {
    @Autowired
    UserService serv;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/auth/register")
    ResponseEntity<BaseOutput> register(@RequestBody UserCredential user) {
        BaseOutput response = new BaseOutput();
        response = serv.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/auth/generateToken")
    ResponseEntity<String> generateToken(@RequestBody AuthRequest au) {
        String response = "";
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(au.getUsername(), au.getPassword()));
        if (authenticate.isAuthenticated()) {
            response = serv.generateToken(au.getUsername());
        } else {
            throw new RuntimeException("Invalid access");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
