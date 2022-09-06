package com.mhrd.SpringAuthSecurity.userDetails;

import java.util.List;

//import com.st7.authorizationserver.authorizationserver.model.ClientApplicationDetails;

public interface ClientDetailsService {

    public List<ClientApplicationDetails> findClients() throws Exception;
}
