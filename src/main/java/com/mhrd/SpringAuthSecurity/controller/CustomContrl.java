package com.mhrd.SpringAuthSecurity.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//import com.example.MOERADTEACHER.modal.TeacherFormStatus;
//import com.example.MOERADTEACHER.modal.TeacherProfile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhrd.SpringAuthSecurity.bean.CaptchaUtil;
import com.mhrd.SpringAuthSecurity.bean.CustomBean;
import com.mhrd.SpringAuthSecurity.filter.CustomDataEncriptor;
import com.mhrd.SpringAuthSecurity.filter.RSAUtil;
import com.mhrd.SpringAuthSecurity.filter.RSAUtils;

import io.micrometer.core.instrument.util.StringUtils;
//import org.apache.commons.lang3.StringUtils;

//import com.me.fis.util.ApiPaths;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomContrl {

	 @RequestMapping(value = "/cust", method = RequestMethod.GET)
	   
	    public String logout() {
	     // System.out.println("custom logout");  
//		 String authHeader = request.getHeader("Authorization");
	        
//	        if (authHeader != null) {
//	            String tokenValue = authHeader.replace("Bearer", "").trim();
//	            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//	            tokenStore.removeAccessToken(accessToken);
//	        }
	     return "shamim";
	    }
	 
	 
		
		@RequestMapping(value = "/getCapcha", method = RequestMethod.GET)
		public ResponseEntity<?> getCapcha(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
			 response.setContentType("image/jpeg");
			 final String FILE_TYPE = "jpeg";
			 
			 
			  String captchaStr=CaptchaUtil.generateCaptchatMethod2(6);
	          
	          try {
	              int width=225;         int height=80;
	              
	              Color bg = new Color(92,0,11);
	              Color fg = new Color(255,255,255);
	              
	              Font font = new Font("Arial", Font.BOLD, 40);
	              BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.OPAQUE);
	              Graphics g = cpimg.createGraphics();
	              g.setFont(font);
	              g.setColor(bg);
	              g.fillRect(0, 0, width, height);
	              g.setColor(fg);
	              g.drawString(captchaStr,30,55);   
	              
	              HttpSession session = request.getSession(true);
	              session.setAttribute("CAPTCHA", captchaStr);
	              
	              // System.out.println("session captcga--->"+session.getAttribute("CAPTCHA"));
	              
	             OutputStream outputStream = response.getOutputStream();
	                 
	             ImageIO.write(cpimg, FILE_TYPE, outputStream);
	             outputStream.close();
	             
	          } catch (Exception e) {
	                  e.printStackTrace();
	          }
			 
			 
//			Map<String,Object> mp=new HashMap<String,Object>();
//			ServletContext context = ((HttpServletRequest) req).getSession().getServletContext();
//			// System.out.println("req--->"+context.getAttribute("_public_key"));
//			mp.put("key",context.getAttribute("_public_key"));
//			return ResponseEntity.ok(mp);
			return null;
		}
		
		  @RequestMapping(value = "/verifycaptcha", method = RequestMethod.POST )
		    public ResponseEntity<?> getProfileById(@RequestBody String data,HttpSession session,ServletRequest request) throws Exception {    
		        Map<Object,Object> mp=new HashMap<Object,Object>();
		        CustomDataEncriptor obj=new CustomDataEncriptor();
//		        Enumeration<String> attributes = ((HttpServletRequest) session).getSession().getAttributeNames();
//		        // System.out.println(attributes.hasMoreElements());
//		        while (attributes.hasMoreElements()) {
//		            String attribute = (String) attributes.nextElement();
//		            // System.out.println(attribute+" : "+((HttpServletRequest) request).getSession().getAttribute(attribute));
//		        }
		        
		        ObjectMapper mapperObj = new ObjectMapper();
		        CustomBean tdata=new CustomBean();
				
				try {
					tdata = mapperObj.readValue(data, new TypeReference<CustomBean>() {
					});
//					formData = mapperObj.readValue(data, new TypeReference<TeacherFormStatus>() {
//					});
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		        
		        // System.out.println("text--->"+tdata.getCaptchaText());
		        
		        
//		        // System.out.println("captchaText--->"+captchaText);
		        
//		        captchaText=   "lrcjCI0Sb8vKvS4dFg1YxGfofIz+YVZbiilfia3zI93PiBKhBWlk0LAqpp9sA5jaMIawXPKdAupq5Jxfo/Xl6v7bG9LRSEab9s8XyC3JygrTF+Fcwy2hUxQKE6O1WwzdgTjcDB3Z8jtX2r9Ncyvv5ra8e4NmS2TrXcNLaJAUuXw=";
		        
		        ServletContext context = ((HttpServletRequest) request).getSession().getServletContext();
//		        PrivateKey privateKey = (PrivateKey) context.getAttribute("_private_key");
		        
		        String privateKey = (String) context.getAttribute("_private_key");
		        
		        // System.out.println("private key--->"+privateKey);
		        // System.out.println( RSAUtil.decrypt(tdata.getCaptchaText(), privateKey));
		       
//		   // System.out.println("RSA Plan text------>"+RSAUtil.decrypt(data.getCaptchaText(), privateKey));    
		        
		        String captcha=(String)session.getAttribute("CAPTCHA");
		        // System.out.println("Original capcha---->"+captcha);
		          if(StringUtils.isBlank(captcha)|| (!StringUtils.isBlank(captcha) && !captcha.equals(RSAUtil.decrypt(tdata.getCaptchaText(), privateKey)))){
		                captcha="";
		                mp.put("status", obj.encrypt("secret", "000"));
		                return ResponseEntity.ok(mp);	
		            }
		          else  {     
		        	  // System.out.println("In else condition"+obj.encrypt("secret", "111".toString()));
		        	  mp.put("status", obj.encrypt("secret", "111"));
//		        	  mp.put("status","111");
		              return ResponseEntity.ok( mp);  
		          }
		            
		    }
		  
		  @RequestMapping(value = "/getKey", method = RequestMethod.GET)
			public ResponseEntity<?> getKey(HttpServletRequest req) throws AuthenticationException {
				Map<String,Object> mp=new HashMap<String,Object>();
				ServletContext context = ((HttpServletRequest) req).getSession().getServletContext();
				// System.out.println("req--->"+context.getAttribute("_public_key"));
				mp.put("key",context.getAttribute("_public_key"));
				return ResponseEntity.ok(mp);
			}	
		  
		  @RequestMapping(value = "/checkUserLogin", method = RequestMethod.GET)
			public Map<String,Object> checkUserLogin(HttpServletRequest req) throws AuthenticationException {
			  
			  Map<String,Object> mp=new HashMap<String,Object>();
			  
			  String username=null;
			  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
// System.out.println(authentication);
			    if (authentication instanceof AnonymousAuthenticationToken) {
			    	// System.out.println("In if condition--->"+authentication.getCredentials());
			    	mp.put("status", 0);
			    }else {
			    	// System.out.println("In else condition--->"+authentication.getName());
			    	username=authentication.getName();
			    	mp.put("status", 1);
			    	mp.put("username", authentication.getName());
			    }
				return mp; 

			   
			}
	 
	 
}
