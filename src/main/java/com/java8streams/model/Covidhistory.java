package com.java8streams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Covidhistory {

	private String country;
	private String county;
	private String updatedAt;
	private String province;
	private CovidStatus stats;
	private Coordinates coordinates;

}
