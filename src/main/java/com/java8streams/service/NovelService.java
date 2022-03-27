package com.java8streams.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.java8streams.Exception.ApiException;
import com.java8streams.Exception.ErrorBo;
import com.java8streams.config.response.NovelResponse;
import com.java8streams.model.CovidAll;
import com.java8streams.model.CovidContinent;
import com.java8streams.model.CovidState;

/**
 * 
 * @author GoCool
 *
 */
@Service
public class NovelService {

	@Autowired
	private NovelApiUrl novelApiUrl;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 
	 * @param yesterday
	 * @param twoDaysAgo
	 * @param allowNull
	 * @return
	 * @throws ApiException
	 */
	public NovelResponse getAllUrl(Boolean yesterday, Boolean twoDaysAgo, Boolean allowNull) throws ApiException {
		ResponseEntity<CovidAll> responseEntity = restTemplate.getForEntity(
				novelApiUrl.getHostName() + novelApiUrl.getAll(), CovidAll.class, yesterday, twoDaysAgo, allowNull);
		if (Objects.nonNull(responseEntity)) {
			NovelResponse resp = new NovelResponse();
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				resp.setStatus(responseEntity.getStatusCode());
				resp.setValues(responseEntity.getBody());
				resp.setCount(1);
			} else {
				throw new ApiException(
						new ErrorBo(responseEntity.getStatusCode(), responseEntity.getStatusCode().getReasonPhrase()));
			}
			return resp;
		} else {
			throw new ApiException(
					new ErrorBo(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()));
		}
	}

	/**
	 * 
	 * @param yesterday
	 * @param sort
	 * @param allowNull
	 * @return
	 * @throws ApiException
	 */
	public NovelResponse getStates(Boolean yesterday, String sort, Boolean allowNull) throws ApiException {
		ResponseEntity<List<CovidState>> responseEntity = restTemplate.exchange(
				novelApiUrl.getHostName() + novelApiUrl.getStates(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CovidState>>() {
				}, sort, yesterday, allowNull);
		if (Objects.nonNull(responseEntity)) {
			NovelResponse resp = new NovelResponse();
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				resp.setStatus(responseEntity.getStatusCode());
				List<CovidState> covidStateList = responseEntity.getBody();
				if (Objects.nonNull(sort)) {
					Comparator<CovidState> comparator = sortCovidState(sort);
					covidStateList.stream().sorted(comparator).collect(Collectors.toList());
				}
				resp.setValues(covidStateList);
				resp.setCount(covidStateList.size());
			} else {
				throw new ApiException(
						new ErrorBo(responseEntity.getStatusCode(), responseEntity.getStatusCode().getReasonPhrase()));
			}
			return resp;
		} else {
			throw new ApiException(
					new ErrorBo(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()));
		}
	}

	/**
	 * cases, todayCases, deaths, todayDeaths, active
	 * 
	 * @param covidStateList
	 * @param sort
	 * @param comparator2
	 * @return
	 */
	private Comparator<CovidState> sortCovidState(String sort) {
		Comparator<CovidState> comparator = null;
		List<String> sortLst = sort.contains(",") ? Arrays.asList(sort.split(",")) : Arrays.asList(sort);
		for (String sortVal : sortLst) {
			if (sortVal.equalsIgnoreCase("cases")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidState::getCases).reversed();
				}
			}
			if (sortVal.equalsIgnoreCase("todayCases")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidState::getTodayCases).reversed();
				} else {
					comparator.thenComparing(Comparator.comparing(CovidState::getTodayCases).reversed());
				}
			}
			if (sortVal.equalsIgnoreCase("deaths")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidState::getDeaths).reversed();
				} else {
					comparator.thenComparing(Comparator.comparing(CovidState::getDeaths).reversed());
				}
			}
			if (sortVal.equalsIgnoreCase("todayDeaths")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidState::getTodayDeaths).reversed();
				} else {
					comparator.thenComparing(Comparator.comparing(CovidState::getTodayDeaths).reversed());
				}
			}
			if (sortVal.equalsIgnoreCase("active")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidState::getActive).reversed();
				} else {
					comparator.thenComparing(Comparator.comparing(CovidState::getActive).reversed());
				}
			}

		}

		return comparator;

	}

	/**
	 * 
	 * @param yesterday @param state @param allowNull @return @throws
	 *                  ApiException @throws
	 */
	public NovelResponse getState(Boolean yesterday, String state, Boolean allowNull) throws ApiException {

		StringBuilder sb = new StringBuilder(novelApiUrl.getHostName()).append(novelApiUrl.getState()).append(state)
				.append("?");
		if (Objects.nonNull(yesterday)) {
			sb.append(yesterday);
		}
		if (Objects.nonNull(allowNull)) {
			sb.append(allowNull);
		}
		ResponseEntity<CovidState> responseEntity = restTemplate.exchange(sb.toString(), HttpMethod.GET, null,
				CovidState.class);
		if (Objects.nonNull(responseEntity)) {
			NovelResponse resp = new NovelResponse();
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				resp.setStatus(responseEntity.getStatusCode());
				CovidState covidState = responseEntity.getBody();
				resp.setValues(covidState);
				resp.setCount(1);
			} else if (responseEntity.getStatusCode().is4xxClientError()) {
				throw new ApiException(
						new ErrorBo(responseEntity.getStatusCode(), responseEntity.getBody().toString()));
			} else {
				throw new ApiException(
						new ErrorBo(responseEntity.getStatusCode(), responseEntity.getStatusCode().getReasonPhrase()));
			}
			return resp;
		} else {
			throw new ApiException(
					new ErrorBo(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()));
		}
	}

	/**
	 * 
	 * @param yesterday
	 * @param twoDaysAgo
	 * @param sort
	 * @param allowNull
	 * @return
	 * @throws ApiException
	 */
	public NovelResponse getContinents(Boolean yesterday, Boolean twoDaysAgo, String sort, Boolean allowNull) throws ApiException {
		ResponseEntity<List<CovidContinent>> responseEntity = restTemplate.exchange(
				novelApiUrl.getHostName() + novelApiUrl.getContinents(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CovidContinent>>() {
				}, sort, yesterday, twoDaysAgo, allowNull);
		if (Objects.nonNull(responseEntity)) {
			NovelResponse resp = new NovelResponse();
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				resp.setStatus(responseEntity.getStatusCode());
				List<CovidContinent> covidContinentList = responseEntity.getBody();
				if (Objects.nonNull(sort)) {
					Comparator<CovidContinent> comparator = sortCovidContinent(sort);
					covidContinentList.stream().sorted(comparator).collect(Collectors.toList());
				}
				resp.setValues(covidContinentList);
				resp.setCount(covidContinentList.size());
			} else {
				throw new ApiException(
						new ErrorBo(responseEntity.getStatusCode(), responseEntity.getStatusCode().getReasonPhrase()));
			}
			return resp;
		} else {
			throw new ApiException(
					new ErrorBo(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()));
		}
	}

	/**
	 * 
	 * @param sort
	 * @return
	 */
	private Comparator<CovidContinent> sortCovidContinent(String sort) {
		Comparator<CovidContinent> comparator = null;
		List<String> sortLst = sort.contains(",") ? Arrays.asList(sort.split(",")) : Arrays.asList(sort);
		for (String sortVal : sortLst) {
			if (sortVal.equalsIgnoreCase("cases")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidContinent::getCases).reversed();
				}
			}
			if (sortVal.equalsIgnoreCase("todayCases")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidContinent::getTodayCases).reversed();
				} else {
					comparator.thenComparing(Comparator.comparing(CovidContinent::getTodayCases).reversed());
				}
			}
			if (sortVal.equalsIgnoreCase("deaths")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidContinent::getDeaths).reversed();
				} else {
					comparator.thenComparing(Comparator.comparing(CovidContinent::getDeaths).reversed());
				}
			}
			if (sortVal.equalsIgnoreCase("todayDeaths")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidContinent::getTodayDeaths).reversed();
				} else {
					comparator.thenComparing(Comparator.comparing(CovidContinent::getTodayDeaths).reversed());
				}
			}
			if (sortVal.equalsIgnoreCase("active")) {
				if (Objects.isNull(comparator)) {
					comparator = Comparator.comparing(CovidContinent::getActive).reversed();
				} else {
					comparator.thenComparing(Comparator.comparing(CovidContinent::getActive).reversed());
				}
			}

		}

		return comparator;

	}

	/**
	 * 
	 * @param continent
	 * @param yesterday
	 * @param twoDaysAgo
	 * @param strict
	 * @param allowNull
	 * @return
	 * @throws ApiException 
	 */
	public NovelResponse getContinent(String continent, Boolean yesterday, Boolean twoDaysAgo, Boolean strict,
			Boolean allowNull) throws ApiException {

		StringBuilder sb = new StringBuilder(novelApiUrl.getHostName()).append(novelApiUrl.getContinent()).append(continent)
				.append("?");
		if (Objects.nonNull(yesterday)) {
			sb.append(yesterday);
		}
		if (Objects.nonNull(twoDaysAgo)) {
			sb.append(twoDaysAgo);
		}
		if (Objects.nonNull(strict)) {
			sb.append(strict);
		}
		if (Objects.nonNull(allowNull)) {
			sb.append(allowNull);
		}
		ResponseEntity<CovidContinent> responseEntity = restTemplate.exchange(sb.toString(), HttpMethod.GET, null,
				CovidContinent.class);
		if (Objects.nonNull(responseEntity)) {
			NovelResponse resp = new NovelResponse();
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				resp.setStatus(responseEntity.getStatusCode());
				CovidContinent covidContinent = responseEntity.getBody();
				resp.setValues(covidContinent);
				resp.setCount(1);
			} else if (responseEntity.getStatusCode().is4xxClientError()) {
				throw new ApiException(
						new ErrorBo(responseEntity.getStatusCode(), responseEntity.getBody().toString()));
			} else {
				throw new ApiException(
						new ErrorBo(responseEntity.getStatusCode(), responseEntity.getStatusCode().getReasonPhrase()));
			}
			return resp;
		} else {
			throw new ApiException(
					new ErrorBo(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()));
		}
	
	}

}
