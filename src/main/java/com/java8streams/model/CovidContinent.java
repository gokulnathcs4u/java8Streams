package com.java8streams.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidContinent {
	
	private Long updated;
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
	private Long activePerOneMillion;	
	private Long recoveredPerOneMillion;	
	private Long criticalPerOneMillion;
	private GeoInfo continentInfo;
	private List<String> countries;
	

}
