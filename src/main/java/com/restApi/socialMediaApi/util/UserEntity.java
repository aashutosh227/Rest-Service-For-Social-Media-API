package com.restApi.socialMediaApi.util;

import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.restApi.socialMediaApi.user.User;

@JsonFilter("entityFilterBean")
public class UserEntity extends EntityModel<User>{

}
