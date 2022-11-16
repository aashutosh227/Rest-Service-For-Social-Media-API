package com.restApi.socialMediaApi.resources;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restApi.socialMediaApi.user.Name;
import com.restApi.socialMediaApi.user.User;
import com.restApi.socialMediaApi.user.UserV1;

@RestController
public class UserResourcesV1 {

	@GetMapping("/v1/users")
	public List<UserV1> getAllUsers(){
		return Collections.singletonList(new UserV1(new Name("Jim", "Hopper")));
	}
	
	//Versioning using request parameters
	@GetMapping(path="/users", params="version=v1")
	public List<UserV1> getUsers(){
		return Collections.singletonList(new UserV1(new Name("Nancy", "Wheeler")));
	}
	
	@GetMapping(path="/users", params="version=v0")
	public List<User> getUsersByParamsInitVersion(){
		return Collections.singletonList(new User(1,"Will Byers",LocalDate.now().minusYears(15)));
	}
	
	//Versioning using request Headers
	@GetMapping(path="/users", headers="X-API-VERSION=1")
	public List<UserV1> getUsersByHeader(){
		return Collections.singletonList(new UserV1(new Name("Will", "Byers")));
	}
	
	@GetMapping(path="/users", headers="X-API-VERSION=0")
	public List<User> getUsersByHeaderInitVersion(){
		return Collections.singletonList(new User(1,"Will Byers",LocalDate.now().minusYears(15)));
	}
	
	//Versioning using Content-Negotiation [Media Type]
	@GetMapping(path="/users", produces="application/socialMediaApi.app-v1+json")
	public List<UserV1> getUsersByMediaType(){
		return Collections.singletonList(new UserV1(new Name("Max", "Mayfield")));
	}
	
}
