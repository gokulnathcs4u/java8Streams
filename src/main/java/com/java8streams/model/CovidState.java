package com.java8streams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidState {

	private String state;
	private Long updated;
	private Long cases;
	private Long todayCases;
	private Long deaths;
	private Long todayDeaths;
	private Long active;
	private Long casesPerOneMillion;
	private Long deathsPerOneMillion;
	private Long tests;
	private Long testsPerOneMillion;
	private Long population;
}
