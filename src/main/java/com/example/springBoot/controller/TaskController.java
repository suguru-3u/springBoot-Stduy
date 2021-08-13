package com.example.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springBoot.form.TaskForm;
import com.example.springBoot.service.TaskService;

@Controller
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(value="/task/index", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   System.out.println(auth);
	   String userName = auth.getName();
	   mav.addObject("userName", userName);
	   mav.addObject("TaskForm", new TaskForm());
       mav.setViewName("task/index");
     return mav;
    }   
	 
	 @RequestMapping(value="/task/create", method=RequestMethod.POST)
	 public ModelAndView signup(TaskForm taskform, ModelAndView mav) {
	   taskService.createTask(taskform);
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   System.out.println(auth);
	   String userName = auth.getName();
	   mav.addObject("userName", userName);
	   mav.addObject("TaskForm", new TaskForm());
       mav.setViewName("task/index");
     return mav;
    }   
}
