package com.mhrd.SpringAuthSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.st7.authorizationserver.authorizationserver.AuthorizationServerApplication;

@SpringBootApplication

public class SpringAuthSecurityApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringAuthSecurityApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringAuthSecurityApplication.class);
	}
	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {

	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("*");
	        }
	    };
	   }
	
	
	

}
