package com.cc.caloriecounterapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cc.caloriecounterapi.repository.ItemRepository;
import org.springframework.stereotype.Service;

import com.cc.caloriecounterapi.exception.ResourceNotFoundException;
import com.cc.caloriecounterapi.model.Item;
import com.cc.caloriecounterapi.model.User;
import com.cc.caloriecounterapi.repository.UserRepository;
import com.cc.caloriecounterapi.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private ItemRepository itemRepository;

	public UserServiceImpl(UserRepository userRepository, ItemRepository itemRepository) {
		super();
		this.userRepository = userRepository;
		this.itemRepository = itemRepository;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user, int id) {
		
		User existingUser = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "ID", id));
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		userRepository.save(existingUser);
		return existingUser;
	}

	@Override
	public void deleteUser(int id) {
		
		userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "ID", id));
		
		userRepository.deleteById(id);
	}
	
	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

	@Override
	public List<Item> getAllItemsOfUser(int user_id) {
		User existingUser = userRepository.findById(user_id).orElseThrow(
				() -> new ResourceNotFoundException("User", "ID", user_id)); 
		return existingUser.getListOfItems();
	}

//	@Override
//	public List<String> listOfDates() {
//		List<String> listOfDatesAsString = new ArrayList<>();
//
//		for (int i = 0; i < itemRepository.count(); i++) {
//			if(listOfDatesAsString.isEmpty()){
//				listOfDatesAsString.add(itemRepository.findAll().get(i).d)
//			}
//		}
//
//		List<LocalDate> listOfDates = new ArrayList<>();
//
//		for (String dateString : listOfDatesAsString) {
//			try {
//				LocalDate date = LocalDate.parse(dateString);
//				listOfDates.add(date);
//			} catch (DateTimeParseException e) {
//				// Handle parsing errors if necessary
//			}
//		}
//	}

}
