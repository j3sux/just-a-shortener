package com.shortener.app.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.app.model.Urlmap;
import com.shortener.app.service.UrlmapService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/")
public class UrlRestController {
	
	
	@Autowired
	private UrlmapService  urlmapService;
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
    public void findAlias(HttpServletRequest req, HttpServletResponse response) {
		
		// TO DO - This can be better, i think now is kind ugly 
		String fullPath =  req.getRequestURI().substring(req.getContextPath().length());
		Urlmap res = urlmapService.getUrlbyAlias(fullPath);
		
		try {
			if (res!= null) {
				response.sendRedirect(res.getUrl());
			}
			if (res == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
   
    @RequestMapping(value = "/", headers="Content-Type=application/json", method = RequestMethod.POST)
    public Urlmap create(@Valid @RequestBody Urlmap urlmap) {
    	return	urlmapService.generateWithAlias(urlmap.getUrl());
    }    
	

	
}