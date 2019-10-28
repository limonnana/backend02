package com.limonnana.backend02.utils;

import com.limonnana.backend02.entity.User;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;


@Component
public class UtilsUser {

    public void setDatesWithFormat(User u){

       Date createdAt =  u.getCreatedAt();
       Date modifedAt = u.getUpdatedAt();
       u.setCreated(format(createdAt));
       u.setModified(format(modifedAt));
    }

    /**
     * Generates a JWT Token as accepted by a service like Zoom. Adjust the header and payload to fit the
     * service you are interacting with. Be sure to account for all spaces in header/payload! Also token string
     * is without padding. If you need padding, be sure to remove "withoutPadding()" calls.
     * @param  secretKey  Secret key used during encoding.
     * @return returns JWT token string based on header, payload and secretKey
     */
    public String generateJWTToken(String secretKey) throws RuntimeException {
        String header = "{\"typ\":\"JWT\",\"alg\":\"HS256\"}";
        String base64UrlHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes());

        // JWT token expires 60 seconds from now
        long timeSecs = (System.currentTimeMillis() / 1000) + 60;

        String payload = "{\"iss\":\"some_key\",\"exp\":" + String.valueOf(timeSecs) + "}";
        String base64UrlPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());

        try {
            String base64UrlSignature = hmacEncode(base64UrlHeader + "." + base64UrlPayload, secretKey);

            return base64UrlHeader + "." + base64UrlPayload + "." + base64UrlSignature;
        } catch (Exception e) {
            throw new RuntimeException("Unable to generate a JWT token.");
        }
    }

    /**
     * Helper method that encodes data using HmacSHA256 and key.
     * @param  data data to encode
     * @param  key  Secret key used during encoding.
     * @return Base64UrlEncoded string without padding
     */
    private String hmacEncode(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(sha256_HMAC.doFinal(data.getBytes()));
    }

    private String format(Date d){

        String pattern = "dd-MM-YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(d);
        return date;
    }
}
