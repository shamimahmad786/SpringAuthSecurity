package com.mhrd.SpringAuthSecurity.filter;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.commons.codec.binary.Base64;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter("/*")
//@Order(-1)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomCorsFilter  implements Filter{
	
	

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req1 = (HttpServletRequest) req;
		ServletContext context = ((HttpServletRequest) req).getSession().getServletContext();
//		PrivateKey privateKeys = (PrivateKey) context.getAttribute("_private_key");
		
		String privateKeys =  (String) context.getAttribute("_private_key");
		
//		// System.out.println("Request Name---------->"+req1.getRequestURL());
		
		
		
		if(req.getParameter("password") !=null) {
			
//			// System.out.println("Old password--->"+req.getParameter("password"));	
			 CustomDataEncriptor obj=new CustomDataEncriptor();
			// // System.out.println("decriptedPassword--->"+obj.decryptText( req.getParameter("password"),"secret"));
			 Map<String, String[]> extraParams = new TreeMap<String, String[]>();
//				extraParams.put("password",new String[]{obj.decryptText( req.getParameter("password"),"secret")});
				try {
					// System.out.println(RSAUtil.decrypt(req.getParameter("password"), privateKeys));
					extraParams.put("password",new String[]{ RSAUtil.decrypt(req.getParameter("password"), privateKeys)});
				} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
						| NoSuchAlgorithmException | NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				// System.out.println("extraParams---->"+extraParams.get("password"));
				
				  HttpServletRequest wrappedRequest = new AddableHttpRequest(req1,extraParams);
			        filterChain.doFilter(wrappedRequest, res);
		
			        
		
			        // For dynan
			        
			        
			        
			        
	     //	// System.out.println(obj.decrypt("7C83DA8F90BB750E4AB6C87BFE4B19CCDEC93C2D055AB93C96EA06F87F997D8D", "FC916C3A1239A79DB9696FA6DD014B4F", "secret", req.getParameter("password")));
			
		//	// System.out.println("privateKeys--->"+privateKeys);
		//	// System.out.println("original password--->"+(RSAUtils.decrypt(req.getParameter("password"), privateKeys)));
		}
    	//// System.out.println("configuration--->"+req1.getParameter("client_id"));
//		// System.out.println("configuration--->"+req1.getContentLength() );
//		// System.out.println(req1.getRemoteHost());
//		// System.out.println(req1.getRemotePort());
//		// System.out.println(req1.getLocalPort());
//		// System.out.println(req1.getServerPort());
//		// System.out.println(req1.getServletPath());
//		// System.out.println(req1.getContextPath());
//		// System.out.println(req1.getAttribute("client_id"));
//		req1.getParameterValues("password")[0] = "newValue";
//		HttpServletRequestWrapper req2=	new HttpServletRequestWrapper(req1);
//		req1.getParameterMap().put("password", new String[]{"value1"});
//		AddableHttpRequest  obj=(AddableHttpRequest) new HttpServletRequestWrapper(req1);
//		req2.
		
		
		
	
		
			  
		//// System.out.println("encripted password req1---->"+wrappedRequest.getParameter("password"));
		
		 Enumeration<String> headerNames = req1.getHeaderNames();
//         while(headerNames.hasMoreElements()) {
//            String paramName = (String)headerNames.nextElement();
//            System.out.print(" Name " + paramName + " ");
//            String paramValue = req1.getHeader(paramName);
//            // System.out.println(" Value  " + paramValue + " ");
//         }
//         String Uri = req1.getRequestURL()+"?"+req1.getQueryString();
//		// System.out.println("url--->"+Uri);
		 HttpServletResponse response = (HttpServletResponse) res;
	        HttpServletRequest request = (HttpServletRequest) req;
	        
//	        Cookie[] allCookies = request.getCookies();
//	        if (allCookies != null) {
//	            Cookie session = 
//	              Arrays.stream(allCookies).filter(x -> x.getName().equals("JSESSIONID"))
//	                    .findFirst().orElse(null);
//
//	            if (session != null) {
//	            	
//	            	// System.out.println("In true");
//	                session.setHttpOnly(true);
//	                session.setSecure(true);
//	                response.addCookie(session);
//	            }
//	        }
	       
	        
//	        // System.out.println("::::::::::::::::::::::Filter:::::::::::::::::::::::::::::::::");
	        
//	        response.setHeader("Access-Control-Allow-Origin", "*");
//	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//	        response.setHeader("Access-Control-Max-Age", "3600");
//	        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization");
	        
	        
//	 	   ServletContext context = ((HttpServletRequest) request).getSession().getServletContext();
		   
//		   HttpServletRequest req = (HttpServletRequest) request;
//	        HttpSession session = request.getSession(true);
//            // System.out.println(session.getAttribute("CAPTCHA"));
//		  // System.out.println("Private key--->"+context.getAttribute("_private_key"));
//		  // System.out.println("Public key---->"+context.getAttribute("_public_key"));
		   
		   if(context.getAttribute("_private_key") ==null) {
			   KeyPairGenerator keyGen = null;
			try {
				keyGen = KeyPairGenerator.getInstance("RSA");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        keyGen.initialize(1024);
		        KeyPair pair = keyGen.generateKeyPair();
		       
			   
//			   KeyPair keyPair = RSAUtil.generateKeyPair();
//		       PrivateKey privateKey = keyPair.getPrivate();
//		       // System.out.println("privateKey---->"+privateKey.getEncoded());
		       context.setAttribute("_private_key",Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded()));
		       context.setAttribute("_public_key", Base64.getEncoder().encodeToString(pair.getPublic().getEncoded()));
//		       context.setAttribute("_public_key", Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
		       
		      // context.setAttribute("_private_key","MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANPWLDXtOU3SKztejMe/SmhiImRIrFWCkrZmvNeZvAXsICVYip6BXcY22OxtvgMNUiJOxB3BvRkxTMz8dr0QyMFu+xPBHcVk3kVYquUZ0iF4a04UKHyto3yJzbO4nyWO0RGG/hyuTfUpB1x7EPB3+pJviTvcd9a0VRHge0CF30QzAgMBAAECgYEAhMYnvcEreqhxamvPx18Rjy17KuoWAh6uQF9Sm7wDCp8+YsoFUGX7VcKI4l/Cif0ubsx5xcDp+kFZRt4yujwr51Xs68HtO/JNO4xxA4jhl3LzkdyzCOio3c+GEBP8L4flOaPeIZkVIZACEep9WFjYuruIHhFYKuDLBAmQOPmju+ECQQDrRpCTUPAQqSliZUGT9j/3CcVDXxTEbr000hmaUUrccfXbWecpo0de2I87c3tNLlaeniFnuflsCOdWRaTBPtkxAkEA5n8PkpTAJHFZwALROWhiO/qni3w/XCXbEa0F0RK1H1Fuai+jnWqdNHzasbAGQGXqglsxIdIfti8uaA4q8owaowJADpbxoDEEsgPLbS6aQnKixM72TJc40nWLhhsBO3CPE9x9QnzwuMRHSLplJ2qh2sdk17E2oRgHP4vNzKvE67baAQJARE7XcJtArgwhivPKyXaT1i6cRIwXwtk9KOnb1W/z2UoqrLFdjaMw34M41HvT/nW1n9gioWFCIJ2u5Qt90s+OfQJACrG9N9zikcHQ6UgFbqlk9Pyxx7mTbfC90qrNpUgXcrQyagFCpm4BViMMJRilfQG4nyXyDCag9uFaHTkpaXUV2A==");
		     //  context.setAttribute("_public_key", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDT1iw17TlN0is7XozHv0poYiJkSKxVgpK2ZrzXmbwF7CAlWIqegV3GNtjsbb4DDVIiTsQdwb0ZMUzM/Ha9EMjBbvsTwR3FZN5FWKrlGdIheGtOFCh8raN8ic2zuJ8ljtERhv4crk31KQdcexDwd/qSb4k73HfWtFUR4HtAhd9EMwIDAQAB");
		     
		       //		       // System.out.println("privateKey---->"+privateKey);
//		       Map<String, Object> publicKeyMap = new HashMap<>();
//		       publicKeyMap.put("publicKey", Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
//		       // System.out.println("Public key at filter------------------------>"+ Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
//		  // System.out.println("Private Key---->"+keyPair.getPrivate());
		   }
//		   // System.out.println("request url----->"+req.getRequestURI());
	        
	        
	        
		
	        
//	        // System.out.println(request.getHeader("Origin"));
//	        response.setHeader("Access-Control-Allow-Origin", "http://10.25.26.251:4200,https://demopgi.udiseplus.gov.in,https://pgi.udiseplus.gov.in");
	       response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
//	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
//	        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
	        response.setHeader("Access-Control-Allow-Headers", "*");
	//        response.setHeader("Access-Control-Allow-Credentials", "true");
	        response.setHeader("Access-Control-Max-Age", "180");
	        
//	        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
//	            response.setStatus(HttpServletResponse.SC_OK);
//	        } else {
//	        	filterChain.doFilter(req, res);
//	        }
	        
	        
	        filterChain.doFilter(req, res);

//	        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//	        	// System.out.println(request.getMethod());
//	        	// System.out.println("In option");
//	            response.setStatus(HttpServletResponse.SC_OK);
//	            filterChain.doFilter(req, res);
//	        } else {
//	        	// System.out.println("in else condition");
//	        	filterChain.doFilter(req, res);
//	        }
//	        filterChain.doFilter(req, res);
		
	}

}
