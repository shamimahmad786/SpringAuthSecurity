package com.mhrd.SpringAuthSecurity.userDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.st7.authorizationserver.authorizationserver.model.ClientApplicationDetails;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientApplicationDetails, Integer> {

    List<ClientApplicationDetails> findAll();
}
