package com.example.springBoot.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TaskForm {
	
	
	@NotBlank(message="空白は不可")
	private String title;
	
	@NotBlank(message="空白は不可")
	private String main;
	
	
}





