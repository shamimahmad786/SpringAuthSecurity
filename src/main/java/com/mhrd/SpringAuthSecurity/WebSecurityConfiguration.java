package com.mhrd.SpringAuthSecurity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.activation.DataSource;
//import javax.annotation.Resource;
//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import com.mhrd.SpringAuthSecurity.filter.CorsFilter;

//import com.mhrd.SpringAuthSecurity.Service.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, // (1)
		  securedEnabled = true, // (2)
		  jsr250Enabled = true) // (3)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
//	    @Autowired
//	    private UserDetailsService userDetailsService;
	    @Autowired
	    CustomUserDetailsService customUserDetailsService;

	    @Bean
	    protected AuthenticationManager getAuthenticationManager() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    }
	    
//	    @Bean
//		public PasswordEncoder passwordEncoder(){
//			PasswordEncoder encoder = new BCryptPasswordEncoder();
//			// System.out.println("encode--->"+encoder);
//			return encoder;
//		}


	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	    }
	    
//	    @Bean
//	    CorsFilter corsFilter() {
//	        CorsFilter filter = new CorsFilter();
//	        return filter;
//	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	//HttpServletRequest req = (HttpServletRequest) request;
	    	//// System.out.println("configuration--->"+req.getRequestURL());
//	    	http.authorizeRequests().anyRequest().authenticated().and().logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()).deleteCookies("JSESSIONID").invalidateHttpSession(false).permitAll();
//	    	http .formLogin()
//              .loginPage("/login")
//              .permitAll();
//	    	 http.addFilterBefore(corsFilter(), SessionManagementFilter.class);
	    	// System.out.println("1");
	        http
//	                .authorizeRequests()
//	                .antMatchers("/**").permitAll()
	        
//	                .cors().and()
	                .authorizeRequests()
//	                .antMatchers("//*").permitAll()
	                .antMatchers("/oauth/token/**","/api/getKey/**","/api/getCapcha/**","/api/checkUserLogin/**","/api/verifycaptcha/**","/api/**","/resources/**","/src/**","/js/**","/login.html/**","/meauth/**").permitAll()
	                    .anyRequest().authenticated()
	                    .and()         
//	                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
	                
	                
	                
	                //adds your custom CorsFilter
//	                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
//	                .and()
	                .formLogin()
	                    .loginPage("/login")
	                    .permitAll()
	                    .and()
	                    .logout().invalidateHttpSession(true)
	                    .clearAuthentication(true)
//	                    .logout(logout -> logout
//	                            .permitAll()
//	                            .logoutSuccessHandler((request, response, authentication) -> {
//	                                response.setStatus(HttpServletResponse.SC_OK);
//	                            }));
	                    .logoutSuccessHandler(logoutSuccessHandler());
//	                    .deleteCookies("JSESSIONID").invalidateHttpSession(false).permitAll();        
//	        http.authorizeRequests().anyRequest().authenticated().and().logout().logoutSuccessHandler(logoutSuccess).deleteCookies("JSESSIONID").invalidateHttpSession(false).permitAll();
	        
//	                http.logout()
//                   .invalidateHttpSession(true)
//                   .logoutSuccessUrl("/logout")
//                   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                   .deleteCookies("JSESSIONID").invalidateHttpSession(true)
//                   .permitAll();
//	        http.logout()
//	        .logoutSuccessUrl("/login");
	        
	        http.csrf().disable();
//	        http.cors().and().csrf().disable();
//	        http.cors().and().authorizeRequests()
//	        .anyRequest().authenticated(); 
	       // http.headers().addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"));
	    }
	    
	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("*"));
	        configuration.setAllowedHeaders(Arrays.asList("*"));
	       // configuration.setAllowCredentials(true);
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	    
	    
	    @Override
        public void configure(WebSecurity web) throws Exception {
	    	 web.ignoring().antMatchers(HttpMethod.OPTIONS, "/oauth/token");
            web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/assets/**","/fonts/**","/dis/**","/vendor1/**");
        }
	    
	    
	    @Bean
	    public LogoutSuccessHandler logoutSuccessHandler() {
	        return new CustomLogoutSuccessHandler();
	    }
	    
	    private AuthenticationEntryPoint authenticationEntryPoint() {
	    	  return new AuthenticationEntryPoint() {
//	    	    @Override
//	    	    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//	    	      httpServletResponse.getWriter().append("Not authenticated");
//	    	      httpServletResponse.setStatus(401);
//	    	    }

				@Override
				public void commence(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException authException) throws IOException, ServletException {
					// TODO Auto-generated method stub
//					request.getWriter().append("Not authenticated");
					response.setStatus(401);
				}
	    	  };
	    	}
	    
//	    @Bean
//	    CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.setAllowedOrigins(Arrays.asList("http://164.100.54.193"));
//	        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTION"));
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", configuration);
//	        return source;
//	    }
//	    
	    
	    
	    

}
