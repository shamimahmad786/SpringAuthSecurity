package com.mhrd.SpringAuthSecurity.userDetails;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "oauth_client_details")
public class ClientApplicationDetails {

    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "web_server_redirect_uri")
    private String redirectUrl;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
