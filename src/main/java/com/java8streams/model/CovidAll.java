package com.java8streams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidAll {
	
	private long updated;
	private long cases;	
	private long todayCases;	
	private long deaths;	
	private long recovered;	
	private long todayRecovered;	
	private long active;	
	private long critical;	
	private long casesPerOneMillion;	
	private long deathsPerOneMillion;	
	private long tests;	
	private long testsPerOneMillion;	
	private long population;	
	private long oneCasePerPeople;	
	private long oneDeathPerPeople;
	private long oneTestPerPeople;	
	private long activePerOneMillion;	
	private long recoveredPerOneMillion;	
	private long criticalPerOneMillion;	
	private long affectedCountries;	
	

}
