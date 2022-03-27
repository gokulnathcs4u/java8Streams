package com.java8streams.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoInfo {
	
	@JsonProperty(value = "lat")
	private Double latitude;
	@JsonProperty(value = "long")
	private Double longitude;

}
