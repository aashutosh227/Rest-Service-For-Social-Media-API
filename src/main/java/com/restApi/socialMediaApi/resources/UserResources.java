package com.restApi.socialMediaApi.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.restApi.socialMediaApi.dao.UserDaoService;
import com.restApi.socialMediaApi.exceptions.UserNotFoundException;
import com.restApi.socialMediaApi.user.User;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
//@RequestMapping("/api")
public class UserResources {
	
	private Logger logger = LoggerFactory.getLogger(UserResources.class);

	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return new ArrayList<User>(userDaoService.findAll().values());
	}
	
	@GetMapping("/users/{id}")
	@ApiOperation(value="Find user by id", 
	notes="Provides user details using the ID",
	response=User.class)
	public EntityModel<User> getUser(@ApiParam(value="Id value needed to retrieve user.", required=true)
	@PathVariable("id") int id) {
		User user= userDaoService.findUserById(id);
				
		if(user==null) {
			throw new UserNotFoundException("User with ID "+id+" not found.");
		}
		
		//Dynamic Filtering to remove user ID from Response
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id");
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("userBean", filter);
		mappingJacksonValue.setFilters(filters);
		
		EntityModel<User> model =  EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		model.add(link.withRel("all-users"));
		
		return model;
	}
	
	@GetMapping("/users/jsonMapping/{id}")
	public MappingJacksonValue getUserJackson(@PathVariable("id") int id) {
		User user= userDaoService.findUserById(id);
		
		if(user==null) {
			throw new UserNotFoundException("User with ID "+id+" not found.");
		}
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name","birthDate");
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("userBean", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		user = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
				
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		
		if(userDaoService.delete(id)) {
			return ResponseEntity.ok().body("User with ID "+id+" deleted successfully");
		}
		else {
			return ResponseEntity.badRequest()
					.body("User with ID "+id+" not found. Hence delete can't be performed.");
		}
		
	}
	
	@GetMapping("/hello")
	public String helloInternationalization() {
		Locale locale =  LocaleContextHolder.getLocale();
		
		try {
			logger.info("Locale: "+locale.getDisplayName());
			String msg = messageSource.getMessage("good.morning.message", null, locale);
		}
		catch(Exception e) {
			logger.info("Message Source error: "+e.toString());
		}
		
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
