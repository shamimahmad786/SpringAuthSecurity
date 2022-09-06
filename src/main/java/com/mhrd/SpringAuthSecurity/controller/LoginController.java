package com.mhrd.SpringAuthSecurity.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhrd.SpringAuthSecurity.CustomUserDetail;
import com.mhrd.SpringAuthSecurity.userDetails.AuthUserDetail;
import com.mhrd.SpringAuthSecurity.userDetails.ClientApplicationDetails;
import com.mhrd.SpringAuthSecurity.userDetails.ClientDetailsService;

//import com.st7.authorizationserver.authorizationserver.model.ClientApplicationDetails;
//import com.st7.authorizationserver.authorizationserver.service.ClientDetailsService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@SessionAttributes("authorizationRequest")
public class LoginController{
//     @Autowired
//	AuthorizationRequest authorizationRequest;
	
    @Autowired
    ClientDetailsService clientDetailsService;
    private Object implicitLock = new Object();
    @GetMapping("/login")
    public String login( ServletRequest request) {
//    	// System.out.println("password--->"+password);
//    	// System.out.println("formData--->"+formData.get("passowrd"));
    	ServletContext context = ((HttpServletRequest) request).getSession().getServletContext();
    	
    	// System.out.println(context.getAttribute("authorizationRequest"));
    	// System.out.println("---------------------------For login------------------------------");
//    	// System.out.println(authorizationRequest.getClientId());
//    	// System.out.println("client id---->"+client_id);

        return "login";
    }
    
    
    @RequestMapping(value="/login",
            method=RequestMethod.POST)
public String login1(@RequestBody MultiValueMap<String, String> formData){
// your code goes here
    	// System.out.println("called form data");
    	return null;
}
    
  
    
    
//    @GetMapping("/oauth/authorize/**")
//    public String login1() {
//    	// System.out.println("---------------------------auth For login------------------------------");
//        return "login";
//    }
    

    @PostMapping("/userLogin")
    @PreAuthorize("permitAll()")
    public String userLogin() {
    	// System.out.println("---------------------------login------------------------------");
        // System.out.println("Login successful..............");
        return "homepage";
    }

//    @Override
//    @RequestMapping({"/oauth/authorize"})
//	public ModelAndView authorize(Map<String, Object> model, Map<String, String> parameters,
//			SessionStatus sessionStatus, Principal principal) {
//		// TODO Auto-generated method stub
//    	AuthorizationRequest authorizationRequest = this.getOAuth2RequestFactory().createAuthorizationRequest(parameters);
//    	// System.out.println(authorizationRequest.getClientId());
//		return super.authorize(model, parameters, sessionStatus, principal);
//	}
//
////    @Override
//    private OAuth2AccessToken getAccessTokenForImplicitGrant(TokenRequest tokenRequest, OAuth2Request storedOAuth2Request) {
//        OAuth2AccessToken accessToken = null;
//        synchronized(this.implicitLock) {
//            accessToken = this.getTokenGranter().grant("implicit", new ImplicitTokenRequest(tokenRequest, storedOAuth2Request));
//            return accessToken;
//        }
//    }


	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
    	// System.out.println("logout called");
        new SecurityContextLogoutHandler().logout(request, null, null);
        model.addAttribute("logoutFlag", "true");
        model.addAttribute("logoutText", "You have been logged out !");
//        return null;
        return "login";
    }

    @GetMapping("/")
    public ModelAndView home(Model clientDetails,Principal principal) {
        List<ClientApplicationDetails> clients = null;

        try {
            clients = clientDetailsService.findClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        // System.out.println("client---->"+clients.get(0).getClientName());
        clientDetails.addAttribute("clients", clients);
        
        AuthUserDetail myUserDetails = (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String  userId=myUserDetails.getUsername();
        // System.out.println("user name--->"+userId);
        clientDetails.addAttribute("username", userId);
        String json = "";
        
        List<String> list = new ArrayList<String>();
        list.add("List A");
        list.add("List B");
        list.add("List C");
        list.add("List D");
        list.add("List E");
        
        try {
            json = mapper.writeValueAsString(clients);
            // System.out.println("json--->"+json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView modelAndView =  new ModelAndView("home");
        modelAndView.addObject("clients", clients);
        modelAndView.addObject("username", userId);
        return modelAndView;
        
//        if (principal != null) {
//            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
//            Authentication authentication = oAuth2Authentication.getUserAuthentication();
//            Map<String, String> details = new LinkedHashMap<>();
//            details = (Map<String, String>) authentication.getDetails();
////            logger.info("details = " + details);  // id, email, name, link etc.
//            Map<String, String> map = new LinkedHashMap<>();
//            map.put("email", details.get("email"));
//            // System.out.println(map);
////            return map;
//        }
        
        
//        return "home";
//        return new ModelAndView("home",clientDetails);
    }


}
