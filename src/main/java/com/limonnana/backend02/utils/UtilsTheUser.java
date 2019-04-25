package com.limonnana.backend02.utils;

import com.limonnana.backend02.entity.TheUser;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class UtilsTheUser {

    public void setDatesWithFormat(TheUser u){

       Date createdAt =  u.getCreatedAt();
       Date modifedAt = u.getUpdatedAt();
       u.setCreated(format(createdAt));
       u.setModified(format(modifedAt));
    }

    private String format(Date d){

        String pattern = "dd-MM-YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(d);
        return date;
    }
}
