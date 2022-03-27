package com.java8streams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidStatus {
	private Long confirmed;
	private Long deaths;
	private Long recovered;
}
