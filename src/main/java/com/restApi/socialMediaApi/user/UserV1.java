package com.restApi.socialMediaApi.user;

import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Details about user")
public class UserV1 {
	
	@ApiModelProperty(notes="Unique User Id")
	private int id;
	@ApiModelProperty(notes="Name of the user")
	@Size(min=2, message="Name should have at least 2 characters")
	private Name name;
	@ApiModelProperty(notes="Date of birth of the user")
	@Past(message="Birth date should be a past date")
	private LocalDate birthDate;
	
	public UserV1(@Size(min = 2, message = "Name should have at least 2 characters") Name name) {
		super();
		this.name = name;
		this.id=1;
		this.birthDate = LocalDate.now().minusYears(52);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	 
}
