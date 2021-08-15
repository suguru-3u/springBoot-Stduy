package com.example.springBoot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springBoot.entity.User;
import com.example.springBoot.repositories.TaskReoisitory;
import com.example.springBoot.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	TaskReoisitory taskReoisitory;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public User getUserInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    System.out.println(name);
	    User user = userRepository.findByName(name);
	    return user;
	  
	}
}
