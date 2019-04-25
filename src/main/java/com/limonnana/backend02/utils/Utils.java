package com.limonnana.backend02.utils;

import com.limonnana.backend02.entity.IpSecure;
import com.limonnana.backend02.repository.IpSecureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Component
public class Utils {

    @Autowired
    IpSecureRepository ipSecureRepository;

    public boolean isSecure(HttpServletRequest request){

        boolean result = false;

        String ip = request.getRemoteAddr();

        if(checkIp(ip)){
            result = true;
        }
        return result;
    }

    private boolean checkIp(String ip){
        boolean result = false;

        List<IpSecure> ipList = ipSecureRepository.findAll();

        for(IpSecure ipSecure : ipList){
            if(ipSecure != null && ipSecure.getIp() != null && ipSecure.getIp().equals(ip)){
                result = true;
                break;
            }
        }
        return result;
    }
}
