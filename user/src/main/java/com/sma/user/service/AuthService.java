package com.sma.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sma.user.entity.UserCredential;
import com.sma.user.repository.UserCredentialRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        UserCredential savedUser=    repository.save(credential);
        
        if(savedUser!=null) {
        	savedUser.setUsername(credential.getName().replaceAll("\\s", "")+savedUser.getId());
        	log.info(savedUser.getUsername());
        	repository.save(credential);
        }
        
        return "user "+savedUser.getUsername() +" added to the system";
    }

    public String generateToken(String username, String role) {
        return jwtService.generateToken(username, role);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
