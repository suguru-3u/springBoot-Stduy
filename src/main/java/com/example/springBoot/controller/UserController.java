package com.example.springBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springBoot.entity.TaskEntity;
import com.example.springBoot.entity.User;
import com.example.springBoot.service.TaskService;
import com.example.springBoot.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView userHomeGET(ModelAndView mav) {
		
		User user = userService.getUserInfo();
		mav.addObject("userName", user.getName());
		
		List<TaskEntity> tasks = taskService.getTaskAll();
		mav.addObject("taskSize", tasks.size());
		
		mav.setViewName("user/home");
		
		return mav;
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public ModelAndView userHomePost(ModelAndView mav) {
		
		User user = userService.getUserInfo();
		mav.addObject("userName", user.getName());
		
		List<TaskEntity> tasks = taskService.getTaskAll();
		mav.addObject("taskSize", tasks.size());
		
		mav.setViewName("user/home");
		
		return mav;
	}
	
}
