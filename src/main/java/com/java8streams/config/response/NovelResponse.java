package com.java8streams.config.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class NovelResponse {
	
	private HttpStatus status;
	private List<Object> values;
	private int count;
	private LocalDateTime timestamp = LocalDateTime.now();

}
