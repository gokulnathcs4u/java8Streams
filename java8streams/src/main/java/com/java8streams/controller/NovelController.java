package com.java8streams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.java8streams.service.NovelService;

@RestController
public class NovelController {
	
	@Autowired
	private NovelService novelService;
	
	public <T> ResponseEntity<T> getAllCovid(){
		
		return null;
	}

}
