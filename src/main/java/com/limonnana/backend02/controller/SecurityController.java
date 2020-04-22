package com.limonnana.backend02.controller;

import com.google.gson.Gson;
import com.limonnana.backend02.entity.*;
import com.limonnana.backend02.repository.UserRepository;
import com.limonnana.backend02.utils.UtilsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilsUser utilsUser;

    @Autowired
    private Environment env;


    @PostMapping(value="/authenticate", consumes = "application/json")
    public String authenticate(@RequestBody Login login){

        String token = "";
        String result = "Not Authorize";
        Gson gson = new Gson();


        User user = userRepository.findByEmail(login.getEmail());

        System.out.println(" user: " + user.getName());

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

    @PostMapping(value="/setAdmin", consumes = "application/json")
    public String setAdmin(@RequestBody Login login){

        RestApiResponse restApiResponse = new RestApiResponse();

        String email = env.getProperty("admin.email");
        String password = env.getProperty("admin.password");

        if(login.getEmail().equals(email) && login.getPassword().equals(password))
        {
            User u = userRepository.findByEmail(env.getProperty("admin.email"));
            if(u == null){
                u = new User();
                u.setEmail(email);
                u.setPassword(password);
                u.setName(env.getProperty("admin.name"));
                u.setPhone(env.getProperty("admin.phone"));
            }
            u.setRole(Role.ADMIN);
            userRepository.save(u);
        }
        else{
            restApiResponse.setStatus(RestApiResponse.FAILED_STATUS);
            restApiResponse.setResponse((RestApiResponse.FAILED));
            restApiResponse.setError((" Username and Password are incorrect "));
        }
        return restApiResponse.toJson();
    }



}
