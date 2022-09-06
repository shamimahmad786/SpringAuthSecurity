package com.mhrd.SpringAuthSecurity.userDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.st7.authorizationserver.authorizationserver.model.ClientApplicationDetails;
//import com.st7.authorizationserver.authorizationserver.repository.ClientDetailsRepository;
//import com.st7.authorizationserver.authorizationserver.service.ClientDetailsService;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    ClientDetailsRepository clientDetailsRepository;

    @Override
    public List<ClientApplicationDetails> findClients() throws Exception {
        List<ClientApplicationDetails> clientsList = clientDetailsRepository.findAll();
        return clientsList;
    }
}
