package com.example.springBoot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springBoot.entity.TaskEntity;
import com.example.springBoot.entity.UserEntity;
import com.example.springBoot.form.TaskForm;
import com.example.springBoot.repositories.TaskReoisitory;
import com.example.springBoot.repositories.UserRepository;

@Service
public class TaskService {

	@Autowired
	TaskReoisitory taskReoisitory;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public void createTask(TaskForm taskform) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println(auth);
	    System.out.println(taskform);
	    //Principalからログインユーザの情報を取得
	    String name = auth.getName();
	    System.out.println(name);
	    UserEntity user = userRepository.findByName(name);
	    System.out.println(user);
	    TaskEntity task = new TaskEntity();
	    task.setTitle(taskform.getTitle());
	    task.setMain(taskform.getMain());
	    task.setUser(user);	    
		taskReoisitory.save(task);		
	}
}

