package com.mhrd.SpringAuthSecurity;

//import java.util.Collection;
//import java.util.Set;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.mhrd.SpringAuthSecurity.userDetails.User;


//
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.mhrd.SpringAuthSecurity.userDetails.Role;
import com.mhrd.SpringAuthSecurity.userDetails.User;

//import com.mhrd.SpringAuthSecurity.userDetails.Role;
//import com.mhrd.SpringAuthSecurity.userDetails.User;
//@Configuration
public class CustomUserDetail implements UserDetails{
	
	
	
	 private User user;
     
	    public CustomUserDetail(User user) {
	        this.user = user;
	    }
	 
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        List<Role> roles = user.getRoles();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	         
	        // System.out.println("get user name--->"+user.getPassword());
	        
	        for (Role role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	         
	        return authorities;
	    }
	 
	    @Override
	    public String getPassword() {
	        return user.getPassword();
	    }
	 
	    @Override
	    public String getUsername() {
	        return user.getUsername();
	    }
	 
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }
	 
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
	        return user.isEnabled();
	    }
}


//
//public class CustomUserDetail implements UserDetails{
//
//    private static final long serialVersionUID = 1L;
//    private User user;
//
//    Set<GrantedAuthority> authorities=null;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<GrantedAuthority> authorities)
//    {
//        this.authorities=authorities;
//    }
//
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    public boolean isAccountNonExpired() {
//        return user.isAccountNonExpired();
//    }
//
//    public boolean isAccountNonLocked() {
//        return user.isAccountNonLocked();
//    }
//
//    public boolean isCredentialsNonExpired() {
//        return user.isCredentialsNonExpired();
//    }
//
//    public boolean isEnabled() {
//        return user.isEnabled();
//    }
//
//}
//























