package com.mhrd.SpringAuthSecurity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;

public class CustomTokenEnhancer implements TokenEnhancer{
	@Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		// System.out.println("<---------------------------------------------Token ----------------------------------------------------------->");
	// System.out.println(" authentication.getAuthoritiesssssssssss();---->"+ authentication.getUserAuthentication());
		try {
        Object user =  authentication.getPrincipal();
//        User user = (User) authentication.getPrincipal();
//      // System.out.println("User name---->"+user.getClass().getField(string).getShort(object));  
        final Map<String, Object> additionalInfo = new HashMap<String, Object>();
//        additionalInfo.put("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        additionalInfo.put("customInfo", "some_stuff_here shamim");
//        additionalInfo.put("authorities", "NULL");
//        ((DefaultOAuth2AccessToken) accessToken).set
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
        return accessToken;
    }

//	@Override
//	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
