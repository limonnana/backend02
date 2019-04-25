package com.limonnana.backend02.controller;


import com.limonnana.backend02.entity.IpSecure;
import com.limonnana.backend02.entity.TheUser;
import com.limonnana.backend02.repository.IpSecureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ip")
public class IpSecureController {

    @Autowired
    IpSecureRepository ipSecureRepository;

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
