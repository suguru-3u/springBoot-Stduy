package com.example.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot.entity.UserEntity;
import com.example.springBoot.repositories.UserRepository;


@Service
public class SignupService {
	
	@Autowired
	UserRepository userRepository;
	
	public void registUser(UserEntity userEntity) {
		System.out.println(userEntity);
		userRepository.save(userEntity);		
	}
}
