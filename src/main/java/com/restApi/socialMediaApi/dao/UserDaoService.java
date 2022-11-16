package com.restApi.socialMediaApi.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.restApi.socialMediaApi.user.User;

@Component
public class UserDaoService {
	
	private static Map<Integer, User> users = new HashMap<Integer, User>();
	
	static {
		users.put(1, new User(1, "Mike", LocalDate.now().minusYears(30)));
		users.put(2, new User(2, "Dustin", LocalDate.now().minusYears(40)));
		users.put(3, new User(3, "Hopper", LocalDate.now().minusYears(60)));
	}
	
	public Map<Integer,User> findAll(){
		return users;
	}

	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return users.get(id);
	}
	
	public User save(User user) {
		user.setId(users.size()+1);
		users.put(users.size()+1, user);
		return users.get(users.size());
	}
	
	public boolean delete(int id) {
		if(users.get(id)==null) {
			return false;
		}
		else {
			users.remove(id);
			return true;
		}
	}
}
