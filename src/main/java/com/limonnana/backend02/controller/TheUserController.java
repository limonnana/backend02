package com.limonnana.backend02.controller;

import com.google.gson.Gson;
import com.limonnana.backend02.entity.Authenticate;
import com.limonnana.backend02.entity.TheUser;
import com.limonnana.backend02.repository.TheUserRepository;
import com.limonnana.backend02.utils.UtilsTheUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "**", maxAge = 3600)
@RestController
@RequestMapping("/secure/user")
public class TheUserController {

    Logger logger = LoggerFactory.getLogger(TheUserController.class);

    @Autowired
    private TheUserRepository theUserRepository;

    @Autowired
    private UtilsTheUser utilsTheUser;



    @GetMapping(value="/findAll")
    public List findAll() {

        List<TheUser> l = theUserRepository.findAll();

        for(TheUser u : l){
            utilsTheUser.setDatesWithFormat(u);
        }

        return l;
    }

    @PostMapping(value="/create")
    public TheUser create(@RequestBody TheUser theUser) {

        return theUserRepository.save(theUser);
    }

    @GetMapping(value = "/getUser/{id}")
    public TheUser getTheUserById(@PathVariable("id") long id) {

        TheUser user = theUserRepository.findById(id).get();
        utilsTheUser.setDatesWithFormat(user);
        return user;
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<TheUser> update(@PathVariable("id") long id, @RequestBody TheUser theUser) {

        TheUser user = theUserRepository.findById(id).get();

        return theUserRepository.findById(id)
                .map(record -> {
                    record.setName(theUser.getName());
                    record.setEmail(theUser.getEmail());
                    record.setPhone(theUser.getPhone());
                    TheUser updated = theUserRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, @RequestBody TheUser theUser) {

        TheUser user = theUserRepository.findById(id).get();


        if (user != null && user.getName().length() > 1) {
            if(theUser.getPassword() != null && theUser.getPassword().length() > 1){
                user.setPassword(theUser.getPassword());
            }
            user.setEmail(theUser.getEmail());
            user.setName(theUser.getName());
            user.setPhone(theUser.getPhone());
            theUserRepository.updateUser(theUser.getName(), theUser.getEmail(), theUser.getPhone(), id);

        }
        return  "{\"message\":200}";

    }

    @DeleteMapping(path ={"/deleteUser/{id}"})
    public String delete(@PathVariable("id") long id) {

        theUserRepository.deleteById(id);

        return "{\"message\":200}";

    }

}