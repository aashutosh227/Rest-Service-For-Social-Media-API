package com.restApi.socialMediaApi.util;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("jsonFilterBean")
public class JsonMappingUtility extends MappingJacksonValue {

	public JsonMappingUtility(Object value) {
		super(value);
	}

}
