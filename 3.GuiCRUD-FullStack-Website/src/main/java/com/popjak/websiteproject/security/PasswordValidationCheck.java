package com.popjak.websiteproject.security;

import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

@Component
public class PasswordValidationCheck {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public PasswordValidationCheck(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public boolean passwordCheck(String userPassword, String inputOldPassword) {
        return bCryptPasswordEncoder.matches(inputOldPassword,userPassword );
    }

    public String passwordEncoder(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
