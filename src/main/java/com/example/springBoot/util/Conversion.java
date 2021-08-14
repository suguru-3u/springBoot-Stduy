package com.example.springBoot.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.springBoot.entity.User;
import com.example.springBoot.form.SignUpForm;

@Component
public class Conversion {
	
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User signupCon(SignUpForm form) {
        return new User(
//                form.getI(),
                form.getName(),
                form.getEmail(),
                passwordEncoder.encode(form.getPassword()));
    }
}