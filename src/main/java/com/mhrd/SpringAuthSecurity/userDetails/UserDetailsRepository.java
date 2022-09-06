package com.mhrd.SpringAuthSecurity.userDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

//import com.st7.authorizationserver.authorizationserver.model.User;

//@EnableJpaRepositories
@Repository
public interface UserDetailsRepository extends JpaRepository<User, Integer> {
//	@Query("SELECT user from User user INNER JOIN FETCH user.authorities as authorities WHERE user.username = :username")
    Optional<User> findByUsername(String username);
}
