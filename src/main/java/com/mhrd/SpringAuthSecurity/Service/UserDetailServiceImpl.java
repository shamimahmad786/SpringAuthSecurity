package com.mhrd.SpringAuthSecurity.Service;
//import com.krishantha.rentcloud.authorizationserver.model.AuthUserDetail;
//import com.krishantha.rentcloud.authorizationserver.model.User;
//import com.krishantha.rentcloud.authorizationserver.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mhrd.SpringAuthSecurity.userDetails.AuthUserDetail;
import com.mhrd.SpringAuthSecurity.userDetails.User;
import com.mhrd.SpringAuthSecurity.userDetails.UserDetailsRepository;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
// System.out.println("user name---->"+name);
        Optional<User> optionalUser = userDetailRepository.findByUsername(name);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;


    }
}

