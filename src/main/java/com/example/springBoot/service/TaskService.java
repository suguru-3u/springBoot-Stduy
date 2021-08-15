package com.example.springBoot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springBoot.entity.TaskEntity;
import com.example.springBoot.entity.User;
import com.example.springBoot.form.TaskForm;
import com.example.springBoot.form.TaskUpdateRequest;
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
	    User user = userRepository.findByName(name);
	    System.out.println(user);
	    TaskEntity task = new TaskEntity();
	    task.setTitle(taskform.getTitle());
	    task.setMain(taskform.getMain());
	    task.setUser(user);	    
		taskReoisitory.save(task);		
	}
	

	public List<TaskEntity> getTaskAll(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User user = userRepository.findByName(name);
	    List<TaskEntity> tasks = taskReoisitory.findByUserId(user.getId());
	    return tasks;
	}
	
	public TaskEntity getTask(int taskId) {
		TaskEntity task = taskReoisitory.findById(taskId);
	    return task;
	}
	
	public void updateTask(TaskUpdateRequest taskUpdateRequest) {
		TaskEntity task =  getTask(taskUpdateRequest.getId());
		
		task.setTitle(taskUpdateRequest.getTitle());
		task.setMain(taskUpdateRequest.getMain());    
		
		taskReoisitory.save(task);	
	}
}

