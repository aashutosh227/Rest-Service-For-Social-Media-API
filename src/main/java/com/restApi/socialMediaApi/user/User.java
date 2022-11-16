package com.restApi.socialMediaApi.user;

import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Details about user")
//@JsonIgnoreProperties({"id"})
@JsonFilter("userBean")
public class User {

	@ApiModelProperty(notes="Unique User Id")
	//@JsonIgnore
	private int id;
	@ApiModelProperty(notes="Name of the user")
	@Size(min=2, message="Name should have at least 2 characters")
	private String name;
	@ApiModelProperty(notes="Date of birth of the user")
	@Past(message="Birth date should be a past date")
	private LocalDate birthDate;
	
	public User(int id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}
