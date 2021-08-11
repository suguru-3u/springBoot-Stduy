package com.example.springBoot.form;

import lombok.Data;

@Data
public class SignUpForm {

//    @Id
//    private Integer id;
//    @Column(name = "login_id")
	private String name;
    private String email;
    private String password;
}