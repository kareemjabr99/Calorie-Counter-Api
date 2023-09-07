package com.cc.caloriecounterapi.service;

import java.util.List;

import com.cc.caloriecounterapi.model.Item;
import com.cc.caloriecounterapi.model.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User updateUser(User user, int user_id);
	
	void deleteUser(int user_id);
	
	void deleteAllUsers();
	
	List<Item> getAllItemsOfUser(int user_id);

//	List<String> listOfDates();

}
