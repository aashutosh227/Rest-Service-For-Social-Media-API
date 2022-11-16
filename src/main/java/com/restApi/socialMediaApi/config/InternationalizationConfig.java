package com.restApi.socialMediaApi.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class InternationalizationConfig extends WebMvcConfigurerAdapter{

	@Bean
	public MessageSource getMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		
		messageSource.setBasenames("classpath*:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
