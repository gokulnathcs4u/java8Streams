package com.java8streams.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@PropertySource(value = { "classpath:/novel.properties" })
@ConfigurationProperties(prefix = "novel.api")
public class NovelApiUrl {
	private String hostName;
	private String all;
	private String states;
	private String state;
	private String yesterday;
	private String allowNull;
	private String continents;
	private String continent;
	private String countries;
	private String country;
	private String history;
}
