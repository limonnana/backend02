package com.limonnana.backend02.controller;

import com.limonnana.backend02.entity.User;
import com.limonnana.backend02.repository.UserRepository;
import com.limonnana.backend02.utils.UtilsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "**", maxAge = 3600)
@RestController
@RequestMapping("/secure/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilsUser utilsUser;



    @GetMapping(value="/findAll")
    public List findAll(@RequestHeader Map<String, String> m) {

        List<User> l = userRepository.findAll();

        for(User u : l){
            utilsUser.setDatesWithFormat(u);
        }

        return l;
    }

    @PostMapping(value="/create")
    public User create(@RequestBody User user) {

        return userRepository.save(user);
    }

    @GetMapping(value = "/getUser/{id}")
    public User getTheUserById(@PathVariable("id") long id) {

        User user = userRepository.findById(id).get();
        utilsUser.setDatesWithFormat(user);
        return user;
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> update(@PathVariable("id") long id, @RequestBody User theUser) {

        User user = userRepository.findById(id).get();

        return userRepository.findById(id)
                .map(record -> {
                    record.setName(theUser.getName());
                    record.setEmail(theUser.getEmail());
                    record.setPhone(theUser.getPhone());
                    User updated = userRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, @RequestBody User theUser) {

        User user = userRepository.findById(id).get();


        if (user != null && user.getName().length() > 1) {
            if(theUser.getPassword() != null && theUser.getPassword().length() > 1){
                user.setPassword(theUser.getPassword());
            }
            user.setEmail(theUser.getEmail());
            user.setName(theUser.getName());
            user.setPhone(theUser.getPhone());
            userRepository.updateUser(theUser.getName(), theUser.getEmail(), theUser.getPhone(), id);

        }
        return  "{\"message\":200}";

    }

    @DeleteMapping(path ={"/deleteUser/{id}"})
    public String delete(@PathVariable("id") long id) {

        userRepository.deleteById(id);

        return "{\"message\":200}";

    }

}