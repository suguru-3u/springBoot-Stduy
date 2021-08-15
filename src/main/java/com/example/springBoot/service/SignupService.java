package com.example.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot.entity.User;
import com.example.springBoot.repositories.UserRepository;


@Service
public class SignupService {
	
	@Autowired
	UserRepository userRepository;
	
	public void registUser(User user) {
		System.out.println(user);
		userRepository.save(user);		
	}
}
