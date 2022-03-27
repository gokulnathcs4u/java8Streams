package com.java8streams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidCountry {

	private Long updated;
	private String country;
	private GeoInfo countryInfo;
	private Long cases;
	private Long todayCases;
	private Long deaths;
	private Long todayDeaths;
	private Long recovered;
	private Long todayRecovered;
	private Long active;
	private Long critical;
	private Long casesPerOneMillion;
	private Long deathsPerOneMillion;
	private Long tests;
	private Long testsPerOneMillion;
	private Long population;
	private String continent;
	private Long oneCasePerPeople;
	private Long oneDeathPerPeople;
	private Long oneTestPerPeople;
	private Long activePerOneMillion;
	private Long recoveredPerOneMillion;
	private Long criticalPerOneMillion;

}
