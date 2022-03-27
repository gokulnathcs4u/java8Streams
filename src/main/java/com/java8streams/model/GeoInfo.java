package com.java8streams.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoInfo {
	
	private Long _id;
	private String iso2;
	private String iso3;
	@JsonProperty(value = "lat")
	private Double latitude;
	@JsonProperty(value = "long")
	private Double longitude;
	private String flag;

}
