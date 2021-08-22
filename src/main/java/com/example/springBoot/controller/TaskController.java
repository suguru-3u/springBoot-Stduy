package com.example.springBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	
	
	
	
	@RequestMapping(value="/task/index", method=RequestMethod.GET)
	public ModelAndView indexGet(ModelAndView mav) {
		
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   String userName = auth.getName();
	   mav.addObject("userName", userName);
	   
       List<TaskEntity> tasks = taskService.getTaskAll();
       System.out.println(tasks);
       mav.addObject("tasks", tasks);
       
       mav.setViewName("task/index");
     return mav;
    }   
	 
	 @RequestMapping(value="/task/create", method=RequestMethod.POST)
	 public ModelAndView signup(@Validated TaskForm taskform,BindingResult result ,ModelAndView mav) {
		 
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		mav.addObject("userName", userName);
		
		ModelAndView  res =  null ;
	    if (result.hasErrors()) {
	    	
	    	mav.addObject("TaskForm",taskform);
		    mav.setViewName("/task/new");
		    
		    res = mav ;
	    }else {
	    	
	    	taskService.createTask(taskform);	    	
	    	res = new ModelAndView("redirect:index");
	    		
	    }
     return res;
    }   
	 
	 @RequestMapping(value="/task/new", method=RequestMethod.GET)
	 public ModelAndView taskCreateGet(TaskForm taskform, ModelAndView mav) {
		 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String userName = auth.getName();
		 mav.addObject("userName", userName);
		 
		 mav.addObject("TaskForm", new TaskForm());
		 
	     mav.setViewName("task/new");
	     
     return mav;
    }   
	 
	 @RequestMapping(value="/task/edit/{id}", method=RequestMethod.GET)
	 public ModelAndView taskEdit(@PathVariable int id, ModelAndView mav){
	  
       TaskEntity task = taskService.getTask(id);
       TaskUpdateRequest taskUpdateRequest = new TaskUpdateRequest();
       taskUpdateRequest.setId(task.getId());
       taskUpdateRequest.setTitle(task.getTitle());
       taskUpdateRequest.setMain(task.getMain());
       
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
	
	@RequestMapping(value="/task/delete/{id}", method=RequestMethod.POST)
	public ModelAndView taskDelete(@PathVariable int id, ModelAndView mav) {
		
		taskService.taskDelete(id);
		
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
