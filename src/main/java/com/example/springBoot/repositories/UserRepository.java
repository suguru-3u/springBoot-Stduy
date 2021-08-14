package com.example.springBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springBoot.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	public UserEntity findByEmail(String email);
	
	public UserEntity findById(int id);
	
	public UserEntity findByName(String name);
  
}