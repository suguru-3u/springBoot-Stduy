package com.example.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springBoot.form.SignUpForm;
import com.example.springBoot.service.SignupService;
import com.example.springBoot.util.Conversion;

@Controller
public class SecurityController {
	
	@Autowired
	SignupService signupService;
	
	@Autowired
	Conversion conversion;
	
	
    @RequestMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }

    @RequestMapping(value="/hello", method=RequestMethod.POST)
    public String hello() {
       return "hello";
    }   
    
    @RequestMapping(value="/singup", method=RequestMethod.GET)
    public String singup() {
       System.out.println("a");
       return "singup";
    }   
    
    @RequestMapping(value="/singup", method=RequestMethod.POST)
    public ModelAndView signup(SignUpForm signupForm, ModelAndView mav) {
    	System.out.println("b");
        signupService.registUser(conversion.signupCon(signupForm));
        mav.setViewName("login");
        return mav;
    }
}