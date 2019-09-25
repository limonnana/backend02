package com.limonnana.backend02.controller;

import com.google.gson.Gson;
import com.limonnana.backend02.entity.Authenticate;
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
    public String authenticate(@RequestBody String json, @RequestHeader Map<String, String> headers){

        System.out.println(json);

        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });

        String token = "";
        String result = "Not Authorize";
        Gson gson = new Gson();

        Authenticate a = gson.fromJson(json, Authenticate.class);

        User user = userRepository.findByEmail(a.getUsername());

        if(user != null && user.getPassword().equals(a.getPassword())){
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
