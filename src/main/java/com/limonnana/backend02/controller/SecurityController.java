package com.limonnana.backend02.controller;

import com.google.gson.Gson;
import com.limonnana.backend02.entity.Authenticate;
import com.limonnana.backend02.entity.Login;
import com.limonnana.backend02.entity.User;
import com.limonnana.backend02.repository.UserRepository;
import com.limonnana.backend02.utils.UtilsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilsUser utilsUser;

    @PostMapping(value="/authenticate", consumes = "application/json")
    public String authenticate(@RequestBody Login login){

        String token = "";
        String result = "Not Authorize";
        Gson gson = new Gson();


        User user = userRepository.findByEmail(login.getEmail());

        if(user != null && user.getPassword().equals(login.getPassword())){
            token = utilsUser.generateJWTToken("avocado1");
            user.setToken(token);
            userRepository.save(user);
            user.setPassword("******");
        }else{
            user = new User();
            user.setName(result);
        }
        result = gson.toJson(user);

        return  result;
    }



}
