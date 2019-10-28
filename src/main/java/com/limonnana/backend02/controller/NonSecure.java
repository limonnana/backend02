package com.limonnana.backend02.controller;

import com.limonnana.backend02.entity.Login;
import com.limonnana.backend02.entity.User;
import com.limonnana.backend02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "**", maxAge = 3600)
@RestController
public class NonSecure {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value="/register")
    public User register(@RequestBody Login login) {

        User u = new User();
        u.setEmail(login.getEmail());
        u.setPassword(login.getPassword());
        return userRepository.save(u);
    }

    @PostMapping(value="/emailTaken")
    public String isEmailTaken(@RequestBody Login login){
        String email = login.getEmail();
        System.out.println(email);
        String result = "false";
        User user = userRepository.findByEmail(email);
        if(user != null){
            result = "true";
        }
        return "{\"result\":\"" + result + "\"}";
    }
}
