package com.popjak.websiteproject.util;


import com.popjak.websiteproject.dao.*;
import org.springframework.stereotype.*;

import java.util.regex.*;
@Component
public class EmailStringCheck {

    private final DAO dao;

    public EmailStringCheck(DAO dao) {
        this.dao = dao;
    }

    public static boolean emailFormatCheck(String emailString) {

        /*
        This method validates emails using a Pattern matcher.
        If an email does not match the specified pattern, the method will return false.
         */

        String emailPattern = "(([a-zA-Z0-9]+)([\\.\\-_]?)([a-zA-Z0-9]+)([\\.\\-_]?)([a-zA-Z0-9]+)?)(@)([a-zA-Z]+.[A-Za-z]+\\.?([a-zA-Z0-9]+)\\.?([a-zA-Z0-9]+))";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(emailString.toLowerCase());
        return matcher.matches();
    }


}


