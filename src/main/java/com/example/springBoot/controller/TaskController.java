package com.example.springBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springBoot.entity.TaskEntity;
import com.example.springBoot.form.TaskForm;
import com.example.springBoot.form.TaskUpdateRequest;
import com.example.springBoot.service.TaskService;

@Controller
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(value="/task/index", method=RequestMethod.POST)
	public ModelAndView indexPost(ModelAndView mav) {
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   System.out.println(auth);
	   String userName = auth.getName();
	   mav.addObject("userName", userName);
       mav.setViewName("task/index");
       List<TaskEntity> tasks = taskService.getTaskAll();
       System.out.println(tasks);
       mav.addObject("tasks", tasks);
     return mav;
    }   
	
	@RequestMapping(value="/task/index", method=RequestMethod.GET)
	public ModelAndView indexGet(ModelAndView mav) {
		
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   System.out.println(auth);
	   String userName = auth.getName();
	   mav.addObject("userName", userName);
	   
       List<TaskEntity> tasks = taskService.getTaskAll();
       System.out.println(tasks);
       mav.addObject("tasks", tasks);
       
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
	   
	   List<TaskEntity> tasks = taskService.getTaskAll();
	   mav.addObject("tasks", tasks);
	   
       mav.setViewName("task/index");
     return mav;
    }   
	 
	 @RequestMapping(value="/task/create", method=RequestMethod.GET)
	 public ModelAndView taskCreateGet(TaskForm taskform, ModelAndView mav) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 System.out.println(auth);
		 String userName = auth.getName();
		 mav.addObject("userName", userName);
		 mav.addObject("TaskForm", new TaskForm());
	     mav.setViewName("task/create");
//	   taskService.createTask(taskform);
//	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	   String userName = auth.getName();
//	   mav.addObject("userName", userName);
//	   mav.addObject("TaskForm", new TaskForm());
//       mav.setViewName("task/index");
//       List<TaskEntity> tasks = taskService.getTaskAll();
//       System.out.println(tasks);
//       mav.addObject("tasks", tasks);
     return mav;
    }   
	 
	 @RequestMapping(value="/task/edit/{id}", method=RequestMethod.GET)
	 public ModelAndView taskEdit(@PathVariable int id, ModelAndView mav){
	  
       TaskEntity task = taskService.getTask(id);
       TaskUpdateRequest taskUpdateRequest = new TaskUpdateRequest();
       taskUpdateRequest.setId(task.getId());
       taskUpdateRequest.setTitle(task.getTitle());
       taskUpdateRequest.setMain(task.getMain());
//       taskUpdateRequest.setUser(task.getUser());
       
       mav.addObject("TaskUpdateRequest", taskUpdateRequest);
       
       mav.setViewName("task/edit");
       
     return mav;
    }   
	 
	@RequestMapping(value="/task/update", method=RequestMethod.POST)
	public ModelAndView taskUpdate(TaskUpdateRequest taskUpdateRequest, ModelAndView mav) {
		
		taskService.updateTask(taskUpdateRequest);
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userName = auth.getName();
	    mav.addObject("userName", userName);
	    mav.addObject("TaskForm", new TaskForm());
        mav.setViewName("task/index");
        List<TaskEntity> tasks = taskService.getTaskAll();
        System.out.println(tasks);
        mav.addObject("tasks", tasks);
        
    return mav;
    }   
	 
}
