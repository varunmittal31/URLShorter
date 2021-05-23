package com.shorturl.springshorturl;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import com.google.common.hash.Hashing;

@Configuration
public class ShorturlService {

	
	HashMap<String, String> map= new HashMap<String,String>();


	/**
	 * Get original url
	 * 
	 * @param id
	 * @return
	 */
	public String getShortUrl(String id) {

		String url = (String) map.get(id);
		return url;
	}

	/**
	 * Convert URL to short URL by using hashing functions
	 * 
	 * @param url
	 * @return
	 */
	public String createShortURL(String url, HttpServletRequest request) {

		final String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();

		map.put(id, url);
		
		String shortURL = getCurrentUrl(request,id);
		return shortURL;

	}
	
	// To create short URL
	public static String getCurrentUrl(HttpServletRequest request,String id){
	    URL url;
	    URI uri = null;
		try {
			url = new URL(request.getRequestURL().toString());
		
	    String host  = url.getHost();
	    String userInfo = url.getUserInfo();
	    String scheme = url.getProtocol();
	    int port = url.getPort();

	    String requestURI = request.getRequestURI();
		
		uri = new URI(scheme,userInfo,host,port,requestURI+id,null,null);
		
		}  
		 catch (Exception e) {
			e.printStackTrace();
		}
		
	    return uri.toString();
	
	}

}
