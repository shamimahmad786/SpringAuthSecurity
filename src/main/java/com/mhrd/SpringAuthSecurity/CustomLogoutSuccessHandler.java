package com.mhrd.SpringAuthSecurity;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Auditable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class CustomLogoutSuccessHandler extends 
  SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

//	@Autowired 
//    private AuditService auditService; 

    @Override
    public void onLogoutSuccess(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Authentication authentication) 
      throws IOException, ServletException {

    	// System.out.println("parameter--->"+request.getParameter("returnTo"));
        String refererUrl = request.getHeader("Referer");
        // System.out.println("request.getParameter(\"returnTo\")============>");
//        String authorization = request.getHeader("Authorization");
//  	  // System.out.println("authorization--->"+authorization);
        if(request.getParameter("returnTo").indexOf("http") >-1) {
//        	  String authorization = request.getHeader("Authorization");
//        	  // System.out.println("authorization--->"+authorization);
//        	// System.out.println("In index off");
            ServletContext context = ((HttpServletRequest) request).getSession().getServletContext();
//	        PrivateKey privateKey = (PrivateKey) context.getAttribute("_private_key");
            context.setAttribute("_private_key",null);
		    context.setAttribute("_public_key", null);
	        String privateKey = (String) context.getAttribute("_private_key");
        	request.getSession().invalidate();
        	
        	
        }
        
//        auditable.track("Logout from: " + refererUrl);
// System.out.println("refererUrl---->"+refererUrl);
response.setStatus(HttpStatus.OK.value());
response.sendRedirect(request.getParameter("returnTo"));



        super.onLogoutSuccess(request, response, authentication);
    }
}
