package com.example.springBoot.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Table(name="users")
@Entity
public class UserEntity  implements UserDetails {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @OneToMany(mappedBy = "user")
    private List<TaskEntity> taskList;

    /* (非 Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return null;
    }
    
    /* (非 Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    @Override
    public String getUsername() {
    	return this.name;
    }
    
    /* (非 Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    
    @Override
    public boolean isAccountNonExpired() {
    	return true;
    }
    
    /* (非 Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
    	return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
    	return true;
    }
    
    @Override
    public boolean isEnabled() {
    	return true;
    }
    
    public UserEntity() {
    }
    
    public UserEntity(String name, String email , String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public UserEntity(Integer id ,String name, String email , String password) {
    	this.id = id ;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public int getUserId() {
    	return this.id;
    }

     
}