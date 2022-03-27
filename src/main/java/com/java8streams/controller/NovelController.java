package com.java8streams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java8streams.Exception.ApiException;
import com.java8streams.Exception.NovelException;
import com.java8streams.config.response.NovelResponse;
import com.java8streams.service.NovelService;

/**
 * 
 * @author GoCool
 *
 */
@RestController
@RequestMapping("/novel")
public class NovelController {

	@Autowired
	private NovelService novelService;

	/**
	 * 
	 * @param yesterday
	 * @param twoDaysAgo
	 * @param allowNull
	 * @return
	 * @throws NovelException
	 */
	@GetMapping("/all")
	public ResponseEntity<NovelResponse> getAllCovid(@RequestParam(required = false) Boolean yesterday,
			@RequestParam(required = false) Boolean twoDaysAgo, @RequestParam(required = false) Boolean allowNull)
			throws NovelException {
		NovelResponse resp = new NovelResponse();
		try {
			resp = novelService.getAllUrl(yesterday, twoDaysAgo, allowNull);
		} catch (ApiException exception) {
			throw new NovelException(exception.getErrorBo().getErrorCode(), exception.getErrorBo().getDescription(),
					exception);
		} catch (Exception exception) {
			throw new NovelException(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
					exception);
		}
		return new ResponseEntity<NovelResponse>(resp, resp.getStatus());
	}

	/**
	 * 
	 * @param yesterday
	 * @param sort
	 * @param allowNull
	 * @return
	 * @throws NovelException
	 */
	@GetMapping("/states")
	public ResponseEntity<NovelResponse> getStates(@RequestParam(required = false) Boolean yesterday,
			@RequestParam(required = false) String sort, @RequestParam(required = false) Boolean allowNull)
			throws NovelException {
		NovelResponse resp = new NovelResponse();
		try {
			resp = novelService.getStates(yesterday, sort, allowNull);
		} catch (ApiException exception) {
			throw new NovelException(exception.getErrorBo().getErrorCode(), exception.getErrorBo().getDescription(),
					exception);
		} catch (Exception exception) {
			throw new NovelException(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
					exception);
		}
		return new ResponseEntity<NovelResponse>(resp, resp.getStatus());
	}

	/**
	 * 
	 * @param yesterday
	 * @param state
	 * @param allowNull
	 * @return
	 * @throws NovelException
	 */
	@GetMapping("/states/{state}")
	public ResponseEntity<NovelResponse> getState(@RequestParam(required = false) Boolean yesterday,
			@PathVariable(required = true) String state, @RequestParam(required = false) Boolean allowNull)
			throws NovelException {
		NovelResponse resp = new NovelResponse();
		try {
			resp = novelService.getState(yesterday, state, allowNull);
		} catch (ApiException exception) {
			throw new NovelException(exception.getErrorBo().getErrorCode(), exception.getErrorBo().getDescription(),
					exception);
		} catch (Exception exception) {
			if (exception.getMessage().startsWith("40")) {
				throw new NovelException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), exception);
			}
			throw new NovelException(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
					exception);
		}
		return new ResponseEntity<NovelResponse>(resp, resp.getStatus());
	}

	/**
	 * 
	 * @param yesterday
	 * @param twoDaysAgo
	 * @param sort
	 * @param allowNull
	 * @return
	 * @throws NovelException
	 */
	@GetMapping("/continents")
	public ResponseEntity<NovelResponse> getContinents(@RequestParam(required = false) Boolean yesterday,
			@RequestParam(required = false) Boolean twoDaysAgo, @RequestParam(required = false) String sort,
			@RequestParam(required = false) Boolean allowNull) throws NovelException {
		NovelResponse resp = new NovelResponse();
		try {
			resp = novelService.getContinents(yesterday, twoDaysAgo, sort, allowNull);
		} catch (ApiException exception) {
			throw new NovelException(exception.getErrorBo().getErrorCode(), exception.getErrorBo().getDescription(),
					exception);
		} catch (Exception exception) {
			if (exception.getMessage().startsWith("40")) {
				throw new NovelException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), exception);
			}
			throw new NovelException(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
					exception);
		}
		return new ResponseEntity<NovelResponse>(resp, resp.getStatus());
	}

	@GetMapping("/continents/{continent}")
	public ResponseEntity<NovelResponse> getContinent(@PathVariable(required = true) String continent,
			@RequestParam(required = false) Boolean yesterday, @RequestParam(required = false) Boolean twoDaysAgo,
			@RequestParam(required = false) Boolean strict, @RequestParam(required = false) Boolean allowNull)
			throws NovelException {
		NovelResponse resp = new NovelResponse();
		try {
			resp = novelService.getContinent(continent, yesterday, twoDaysAgo, strict, allowNull);
		} catch (ApiException exception) {
			throw new NovelException(exception.getErrorBo().getErrorCode(), exception.getErrorBo().getDescription(),
					exception);
		} catch (Exception exception) {
			if (exception.getMessage().startsWith("40")) {
				throw new NovelException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), exception);
			}
			throw new NovelException(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
					exception);
		}
		return new ResponseEntity<NovelResponse>(resp, resp.getStatus());
	}

}
