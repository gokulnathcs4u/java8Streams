package com.java8streams.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@PropertySource(value = { "novel.properties" })
@ConfigurationProperties(prefix = "novel.api")
public class NovelApiUrl {
	private String hostName;
	private String all;
}
