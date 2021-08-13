package com.example.springBoot.dom;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.springBoot.entity.UserEntity;
import com.example.springBoot.repositories.UserRepository;
 	
@Transactional
@Component
public class JpaUserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    UserRepository accountRepository;
 
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
    	
    	System.out.println(email);
    	
    	if  (email == null || "".equals(email))   {
   		 	System.out.println("空白");
            throw new UsernameNotFoundException("Wrong email or password");
        }
 
    	System.out.println("cc");
    	UserEntity account = accountRepository.findByEmail(email);
    	
    	System.out.println("dd");
    	
    	System.out.println(account);
    	
    	if  (account   ==  null)   {
    		 System.out.println("Wrong email or password");
             throw new UsernameNotFoundException("Wrong email or password");
         }
    	 
        return account;
    }
}