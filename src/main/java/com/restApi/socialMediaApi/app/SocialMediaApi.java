package com.restApi.socialMediaApi.app;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages="com.restApi.socialMediaApi")
@EnableSwagger2
public class SocialMediaApi {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApi.class, args);
	}
	
	@Bean
	public Docket SwaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Initial Version")
				.select()
				.paths(PathSelectors.ant("/users/**"))
				.apis(RequestHandlerSelectors.basePackage("com.restApi.socialMediaApi"))
				.build()
				.apiInfo(getApiInfo());
	}
	
	@Bean
	public Docket SwaggerConfigV1() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V1 API")
				.select()
				.paths(PathSelectors.ant("/v1/**"))
				.apis(RequestHandlerSelectors.basePackage("com.restApi.socialMediaApi"))
				.build()
				.apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Social Media API",
				"Sample Api for REST WS",
				"1.0",
				"Free to use",
				new Contact("Aashutosh Singh","http://innovativeBrains.com",
						"aashutoshsingh126@gmail.com"),
				"API License",
				"http://innovativeBrains.com",
				Collections.emptyList()
				);
	}
}
