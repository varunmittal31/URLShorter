package com.shorturl.springshorturl;

import static org.junit.Assert.assertEquals;

import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringShorturlApplicationTests {

	@Autowired
	private ShorturlService service;
	
	@Autowired
	private ShorturlController controller;

	@Mock
	private HttpServletRequest request;

	

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateURL() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/test/shorturl/");

		String shortURL = service.createShortURL("http://sample.com", request);
		assertEquals(shortURL,"http://localhost/test/shorturl/2c81d9bc");
	}

	@Test
	void validteReturnURL() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/test/shorturl/");
		// Put original url in map and create short URL 
		String shortURL = service.createShortURL("http://sample.com", request);
		
		// Get original url by key from map.
		String originlURL = service.getShortUrl(shortURL.substring(shortURL.lastIndexOf("/")+1,shortURL.length()));
		
		assertEquals(originlURL,"http://sample.com");	}
	
	@Test
	void redirectURL() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/test/shorturl/");
		// Put original url in map and create short URL 
		String shortURL = service.createShortURL("http://sample.com", request);
		
		// Get original url by key from map.
		request.setRequestURI("/test/shorturl/2c81d9bc");
		ResponseEntity response = controller.redirectUrl("2c81d9bc");
		
		assertEquals(response.getStatusCode(),HttpStatus.SEE_OTHER);	}

}
