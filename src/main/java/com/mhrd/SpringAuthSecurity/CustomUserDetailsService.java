package com.mhrd.SpringAuthSecurity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mhrd.SpringAuthSecurity.userDetails.AuthUserDetail;
import com.mhrd.SpringAuthSecurity.userDetails.User;
import com.mhrd.SpringAuthSecurity.userDetails.UserDetailsRepository;

//import com.st7.authorizationserver.authorizationserver.model.AuthUserDetail;
//import com.st7.authorizationserver.authorizationserver.model.User;

//import com.st7.authorizationserver.authorizationserver.repository.UserDetailsRepository;
@Configuration
//@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService  {

	@Autowired
    UserDetailsRepository userDetailsRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

//        CustomUserDetail customUserDetail=new CustomUserDetail();
//        customUserDetail.setUser(null);
//        customUserDetail.setAuthorities(null);
//		return customUserDetail;
		
        Optional<User> optionalUser = userDetailsRepository.findByUsername(username);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or Password wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());

        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
	}
//	  @Autowired
//	    private UserDAO userDAO;
//
//	    public CustomUserDetail loadUserByUsername(String name) throws UsernameNotFoundException, DataAccessException {
//	        // returns the get(0) of the user list obtained from the db
//	        User domainUser = userDAO.getUser(name);
//
//
//	        Set<Role> roles = domainUser.getRole();
//	        logger.debug("role of the user" + roles);
//
//	        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//	        for(Role role: roles){
//	            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//	            logger.debug("role" + role + " role.getRole()" + (role.getRole()));
//	        }
//
//	        CustomUserDetail customUserDetail=new CustomUserDetail();
//	        customUserDetail.setUser(domainUser);
//	        customUserDetail.setAuthorities(authorities);
//
//	        return customUserDetail;
//
//	    }
}

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserDAO userDAO;
//
//    public CustomUserDetail loadUserByUsername(String name) throws UsernameNotFoundException, DataAccessException {
//        // returns the get(0) of the user list obtained from the db
//        User domainUser = userDAO.getUser(name);
//
//
//        Set<Role> roles = domainUser.getRole();
//        logger.debug("role of the user" + roles);
//
//        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        for(Role role: roles){
//            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//            logger.debug("role" + role + " role.getRole()" + (role.getRole()));
//        }
//
//        CustomUserDetail customUserDetail=new CustomUserDetail();
//        customUserDetail.setUser(domainUser);
//        customUserDetail.setAuthorities(authorities);
//
//        return customUserDetail;
//
//    }
//
//}