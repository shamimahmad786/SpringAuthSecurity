//package com.mhrd.SpringAuthSecurity.filter;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//
//import org.springframework.web.WebApplicationInitializer;
//
//public class MainWebAppInitializer implements WebApplicationInitializer {
//   
//
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		// TODO Auto-generated method stub
//		servletContext.getSessionCookieConfig().setHttpOnly(true);        
//		servletContext.getSessionCookieConfig().setSecure(true);    
//	}
//}