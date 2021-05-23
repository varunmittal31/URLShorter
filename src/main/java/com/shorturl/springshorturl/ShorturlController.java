package com.shorturl.springshorturl;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShorturlController {

	@Autowired
	private ShorturlService service;

	/**
	 * Returns a short URL.
	 */
	@RequestMapping(value = "/test/shorturl/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity createUrl(HttpServletRequest request, @RequestBody String url) {

		System.out.println("url========="+url);
		
		UrlObject urlObject = new UrlObject();
		String id = service.createShortURL(url, request);

		urlObject.setShortURL(id);
		urlObject.setUrl(url);
		urlObject.setCreated(LocalDateTime.now());

		return new ResponseEntity<>(urlObject, HttpStatus.OK);
	}
	
	/**
	 * Redirect to original URL
	 */
	@RequestMapping(value = "/test/shorturl/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity redirectUrl(@PathVariable String id) {
		
		System.out.println("id========="+id);
		
		String url = service.getShortUrl(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		try {

			if (url == null) {
				Error error = new Error("key does not exists");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
			}

			System.out.println("url========="+url);
			httpHeaders.setLocation(new URI(url));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}

	

}
