package com.limonnana.backend02.controller;

import com.google.gson.Gson;
import com.limonnana.backend02.entity.Authenticate;
import com.limonnana.backend02.entity.TheUser;
import com.limonnana.backend02.repository.TheUserRepository;
import com.limonnana.backend02.utils.UtilsTheUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class SecurityController {

    @Autowired
    private TheUserRepository theUserRepository;

    @Autowired
    private UtilsTheUser utilsTheUser;

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

        TheUser user = theUserRepository.findByEmail(a.getUsername());

        if(user != null && user.getPassword().equals(a.getPassword())){
            token = utilsTheUser.generateJWTToken("avocado1");
            user.setToken(token);
            theUserRepository.save(user);
            user.setPassword("******");
        }else{
            user = new TheUser();
            user.setName(result);
        }
        result = gson.toJson(user);

        return  result;
    }

    @GetMapping(value="/emailTaken/{email}")
    public String isEmailTaken(@PathVariable("email") String email){
        System.out.println(email);
        String result = "{\"result\":\"false\"}";
        TheUser user = theUserRepository.findByEmail(email);
        if(user != null){
            result = "true";
        }
        return "{\"result\":\"" + result + "\"}";
    }

}
