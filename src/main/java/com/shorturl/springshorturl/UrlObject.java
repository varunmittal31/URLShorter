package com.shorturl.springshorturl;

import java.time.LocalDateTime;



public class UrlObject {
	
	    private String shortURL;
	    private String url;
	    private LocalDateTime created;
	
	    
	   
	    public String getShortURL() {
			return shortURL;
		}
	    public void setShortURL(String shortURL) {
			this.shortURL = shortURL;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	    public void setCreated(LocalDateTime created) {
			this.created = created;
		}
		public String getUrl() {
			return url;
		}
		public LocalDateTime getCreated() {
			return created;
		}
	    
	    

}
