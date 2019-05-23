package com.limonnana.backend02.controller;


import com.limonnana.backend02.entity.IpSecure;
import com.limonnana.backend02.entity.TheUser;
import com.limonnana.backend02.repository.IpSecureRepository;
import com.limonnana.backend02.repository.TheUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IpSecureController {

    @Autowired
    IpSecureRepository ipSecureRepository;

    @Autowired
    private TheUserRepository theUserRepository;

    @GetMapping(value = "/initialize")
    public String initialize() {

        String result = "Is already running";

        List<IpSecure> ipList = ipSecureRepository.findAll();

        if(ipList != null & !ipList.isEmpty()){
            result = "true";
        }else{
            IpSecure ip = new IpSecure();
            ip.setIp("0:0:0:0:0:0:0:1");
            ipSecureRepository.save(ip);

            IpSecure ip1 = new IpSecure();
            ip1.setIp("127.0.0.1");
            ipSecureRepository.save(ip1);
        }

        TheUser user = theUserRepository.findByEmail("rosenzvaig@gmail.com");

        if(user == null){
            user = new TheUser();
            user.setEmail("rosenzvaig@gmail.com");
            user.setName("Eyal Rosenzvaig");
            user.setPhone("0532744117");
            user.setPassword("changeThisPassword");
            theUserRepository.save(user);
            result = " User has been created ";
        }

        return result;
    }

    @PostMapping(value="/create")
    public String create(@RequestBody IpSecure ipSecure) {
        String result = "Anauthorized";

        if(ipSecure.getUsername().equals("limonnana") && ipSecure.getPassword().equals("avocado1")){
            IpSecure ip = new IpSecure();
            ip.setUsername("");
            ip.setPassword("");
            ip.setIp(ipSecure.getIp());
            ipSecureRepository.save(ip);
            result = "Success";
        }

        return result;
    }


}
