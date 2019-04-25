package com.limonnana.backend02.controller;

import com.limonnana.backend02.entity.TheUser;
import com.limonnana.backend02.repository.TheUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/secure/user")

























































public class TheUserController {

    Logger logger = LoggerFactory.getLogger(TheUserController.class);


    @Autowired
    private TheUserRepository theUserRepository;



    @GetMapping(value="/findAll")
    public List findAll() {
        return theUserRepository.findAll();
    }

    @PostMapping(value="/create")
    public TheUser create(@RequestBody TheUser theUser) {
        return theUserRepository.save(theUser);
    }

    @GetMapping(value = "/getUser/{id}")
    public TheUser getTheUserById(@PathVariable("id") long id) {

        TheUser user = theUserRepository.findById(id).get();
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

    @PostMapping(value = "/updateUser/{id}")
    public ResponseEntity<TheUser> updateUser(@PathVariable("id") long id, @RequestBody TheUser theUser) {

        TheUser user = theUserRepository.getOne(id);

        if (user != null) {
            user.setEmail(theUser.getEmail());
            user.setName(theUser.getName());
            user.setPassword(theUser.getPassword());
            user.setPhone(theUser.getPhone());
            theUserRepository.updateUser(theUser.getName(), theUser.getEmail(), theUser.getPhone(), theUser.getPassword(), id);

        }
        return ResponseEntity.ok().body(user);

    }

    @DeleteMapping(path ={"/deleteUser/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        theUserRepository.deleteById(id);

        return ResponseEntity.ok().build();

    }

}