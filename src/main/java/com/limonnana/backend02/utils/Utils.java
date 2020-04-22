package com.limonnana.backend02.utils;

import com.google.gson.Gson;
import com.limonnana.backend02.entity.IpSecure;
import com.limonnana.backend02.entity.JsonSecurity;
import com.limonnana.backend02.entity.User;
import com.limonnana.backend02.repository.IpSecureRepository;
import com.limonnana.backend02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Component
public class Utils {

    @Autowired
    IpSecureRepository ipSecureRepository;

    @Autowired
    UserRepository userRepository;

    public boolean isSecure(HttpServletRequest request){

        boolean result = false;

        String ip = request.getRemoteAddr();

        if(checkIp(ip)){
            result = true;
        }
        return result;
    }

    public boolean checkToken(HttpServletRequest request){
        boolean result = true;

        Map m = getHeadersInfo(request);
        Iterator<Map.Entry<String, String>> iterator = m.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        String tokenJson = request.getHeader(HttpHeaders.AUTHORIZATION);
        String tokenJson1 = request.getHeader("authorization");

        System.out.println("Token: " + tokenJson);
        System.out.println("Token1: " + tokenJson1);

        //result = isTokenOK(tokenJson);

        System.out.println("isThisTokenOk: " + isTokenOK(tokenJson));

        return result;
    }

    private boolean isTokenOK(String tokenJason){

        boolean result = false;

        if(tokenJason != null && tokenJason.length()> 4) {
            Gson gson = new Gson();
            JsonSecurity js = gson.fromJson(tokenJason, JsonSecurity.class);

            User user = userRepository.findByEmail(js.getUsername());
            String usernameFromDb = user.getEmail();
            String tokenFromDb = user.getToken();

            if (usernameFromDb.equals(js.getUsername()) && tokenFromDb.equals(js.getToken())) {
                result = true;
            }
        }
       return  result;
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

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}
