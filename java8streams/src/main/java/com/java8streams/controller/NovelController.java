package com.java8streams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java8streams.Exception.ApiException;
import com.java8streams.Exception.NovelException;
import com.java8streams.config.response.NovelResponse;
import com.java8streams.service.NovelService;

@RestController
@RequestMapping("/novel")
public class NovelController {

	@Autowired
	private NovelService novelService;

	@GetMapping("/all")
	public ResponseEntity<NovelResponse> getAllCovid() throws NovelException {
		NovelResponse resp = new NovelResponse();
		resp.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
		try {
			resp = novelService.getAllUrl();
		} catch (ApiException exception) {
			throw new NovelException(exception.getErrorBo().getErrorCode(), exception.getErrorBo().getDescription(),
					exception);
		} catch (Exception exception) {
			throw new NovelException(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
					exception);
		}
		return new ResponseEntity<NovelResponse>(resp, resp.getStatus());
	}

}
