package com.mhrd.SpringAuthSecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccess  implements LogoutSuccessHandler{
//	@Override
//	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
//	        throws IOException, ServletException {
//	    if (authentication != null && authentication.getDetails() != null) {
//	        try {
//	            httpServletRequest.getSession().invalidate();
//	            // you can add more codes here when the user successfully logs
//	            // out,
//	            // such as updating the database for last active.
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            e = null;
//	        }
//	    }
//
//	    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//
//	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		// System.out.println("Logout called---->");
		 if (authentication != null && authentication.getDetails() != null) {
		        try {
//		        	request.getSession().invalidate();
		            // you can add more codes here when the user successfully logs
		            // out,
		            // such as updating the database for last active.
		        } catch (Exception e) {
		            e.printStackTrace();
		            e = null;
		        }
		    }

		 response.setStatus(HttpServletResponse.SC_OK);
		
	}

}
