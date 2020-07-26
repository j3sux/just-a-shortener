package com.shortener.app.service;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shortener.app.model.Urlmap;
import com.shortener.app.repository.UrlmapRepository;

@Service
public class UrlmapService {

	@Autowired
	private UrlmapRepository urlmapRepository;
	
	public Urlmap getUrlbyAlias(String alias) {
		Urlmap res = urlmapRepository.findByAlias(alias.substring(1));
		if (res != null)
			return res;
		return null;
	}
	
	public Urlmap getUrlbyUrl(String url) {
		Urlmap res = urlmapRepository.findByUrl(url);
		if(res != null)
			return res;
		return null;
	}
	
	public Urlmap generateWithAlias(String url) {
		Urlmap res = this.getUrlbyUrl(url);
		if (res == null) 
			res= new Urlmap(url, doAlias(url));
		    urlmapRepository.save(res);
	return res;
	}
	
	private String doAlias(String url) {
		
    // TO DO  -  I Need more IoC style -  Refactor  and move to some Utils folder 
	/*	
		Any url containing “google”:
		    -   alias will have 5 characters length
		    -   alias will use only alpha characters
		Any url containing “yahoo”
		    -   alias will have 7 characters length
		    -   alias will use alphanumeric characters
		Any other url
		    -   alias will be based on the url itself by removing every special characters, vowels and numbers from it.
	*/	
		
		String alias = "";
		String sha1 = "";
		
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(url.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
        if (url.contains("google")) {
            // get first 5 alpha characters from the  sha1
        	alias = sha1.replaceAll("[^A-Za-z]","");
        	alias = alias.substring(0,6);
        	
        }else if (url.contains("yahoo")) {
        	 alias = sha1.substring(0,7);
        }else {
            alias = url.replaceAll("[^A-Za-z]","");
            alias = alias.replaceAll("[AaEeIiOoUu]", "");
        }
		return alias;
	}
	
}
