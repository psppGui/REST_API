package com.apirestspringboot.apirest.configurations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.module.SimpleModule;

@Configuration
public class DateConfig {
	
	public static final String DATETIME_FORMAT = "yyy-MM-dd'T'HH:mm:ss'Z'";
	public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
	
	@Bean
	public ObjectMapper objectMapper() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(LocalDateTime.class, LOCAL_DATETIME_SERIALIZER);
		
		return new ObjectMapper().rebuild().addModule(module).build();
	}
}