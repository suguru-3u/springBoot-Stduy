package com.example.springBoot.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springBoot.entity.UserEntity;
import com.example.springBoot.form.SignUpForm;
import com.example.springBoot.repositories.UserRepository;
import com.example.springBoot.service.SignupService;
import com.example.springBoot.util.Conversion;

@Controller
public class SecurityController {
	
	@Autowired
	SignupService signupService;
	
	@Autowired
	Conversion conversion;
	
	@Autowired
	UserRepository accountRepository;
	
	
    @RequestMapping(value = {"/", "/home"})
    public String home() {
    	UserEntity account = accountRepository.findByEmail("test2@gmail.com");
    	System.out.println(account);
    	System.out.println("dd");
        return "home";
    }

    @Transactional
    @RequestMapping(value="/hello", method=RequestMethod.POST)
    public ModelAndView helloPost(ModelAndView mav) {
       System.out.println("helloPOST");
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       System.out.println(auth);
       //Principalからログインユーザの情報を取得
       String userName = auth.getName();
       mav.addObject("userName", userName);
       mav.setViewName("hello");
       System.out.println("helloPOST2");
       return mav;
    }   
    
    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public ModelAndView helloGet(ModelAndView mav) {
       System.out.println("helloGET");
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       System.out.println(auth);
       //Principalからログインユーザの情報を取得
       String userName = auth.getName();
       mav.addObject("userName", userName);
       mav.setViewName("hello");
       return mav;
    }   
    
    @RequestMapping(value="/singup", method=RequestMethod.GET)
    public String singup() {
       return "singup";
    }   
    
    @RequestMapping(value="/singup", method=RequestMethod.POST)
    public ModelAndView signup(SignUpForm signupForm, ModelAndView mav) {
        signupService.registUser(conversion.signupCon(signupForm));
        mav.setViewName("login");
        return mav;
    }
}