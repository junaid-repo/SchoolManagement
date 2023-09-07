package com.management.school.smusers.services;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.management.school.smusers.dto.BaseOutput;
import com.management.school.smusers.entities.UserCredential;
import com.management.school.smusers.repositories.UserSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PasswordEncoder psdEncoder;

    @Autowired
    UserSaveRepository userSaveRepo;

    @Autowired
    JwtService jwtService;

    public BaseOutput registerUser(UserCredential user) {
        user.setPassword(psdEncoder.encode((user.getPassword())));
        UserCredential out = new UserCredential();
        BaseOutput response = new BaseOutput();
        String username = "";
        out = userSaveRepo.save(user);
        if (out.getId() > 0) {
            username = user.getName().replaceAll("\\s", "").toLowerCase() + String.valueOf(out.getId());
        }
        out.setUsername(username);

        out = userSaveRepo.save(out);

        response.setReturnCode(201l);
        response.setReturnMsg("User Created");
        response.setUsername(username);
        // TODO Auto-generated method stub
        return response;
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);

    }
}
