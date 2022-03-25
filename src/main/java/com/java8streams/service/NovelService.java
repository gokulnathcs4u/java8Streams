package com.java8streams.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.java8streams.Exception.ApiException;
import com.java8streams.Exception.ErrorBo;
import com.java8streams.config.response.NovelResponse;
import com.java8streams.model.CovidAll;

@Service
public class NovelService {

	@Autowired
	private NovelApiUrl novelApiUrl;

	@Autowired
	private RestTemplate restTemplate;

	public NovelResponse getAllUrl() throws ApiException {
		ResponseEntity<CovidAll> responseEntity = restTemplate
				.getForEntity(novelApiUrl.getHostName() + novelApiUrl.getAll(), CovidAll.class);
		if (Objects.nonNull(responseEntity)) {
			NovelResponse resp = new NovelResponse();
			resp.setRespTime(new Date());
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				resp.setStatus(responseEntity.getStatusCode());
				resp.setValues(Arrays.asList(responseEntity.getBody()));
				resp.setCount(resp.getValues().size());
			} else {
				throw new ApiException(new ErrorBo(responseEntity.getStatusCode(),responseEntity.getStatusCode().getReasonPhrase()));
			}
			return resp;
		} else {
			throw new ApiException(new ErrorBo(HttpStatus.SERVICE_UNAVAILABLE,HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()));
		}
	}

}
