package com.cc.caloriecounterapi.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cc.caloriecounterapi.model.Item;
import com.cc.caloriecounterapi.model.User;
import com.cc.caloriecounterapi.service.ItemService;
import com.cc.caloriecounterapi.service.UserService;

@RestController
@RequestMapping("/cc")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAndItemController {
	
	private UserService userService;
	private ItemService itemService;
	
	
	public UserAndItemController(UserService userService, ItemService itemService) {
		super();
		this.userService = userService;
		this.itemService = itemService;
	}

	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/getItems")
	public List<Item> getItems(){
		return itemService.getAllItems();
	}

	@GetMapping("/getItem/{id}")
	public Item getItem(@PathVariable("id") int item_id){
		return itemService.getItemById(item_id);
	}

	@GetMapping("/getitemsofuser/{id}")
	public List<Item> getItemsOfUser(@PathVariable("id") int user_id){
		return itemService.getAllItemsOfUser(user_id);
	}

	@GetMapping("/getitemsofuserbydate/{id}/{date}")
	public List<Item> getItemsByDateOfAUser(@PathVariable("id") int user_id,
											@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") String dateString ){
		LocalDate date = LocalDate.parse(dateString);
		return itemService.getItemsByDateOfAUser(date, user_id);
	}

	@GetMapping("/getitemsofdate/{date}")
	public List<Item> getItemsOfADate(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") String dateString){
		LocalDate date = LocalDate.parse(dateString);
		return itemService.itemsOfADate(date);
	}

	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/item/{id}")
	public Item createItemForUser(@PathVariable("id") int user_id, @RequestBody Item item){
		return itemService.createItemForUser(user_id, item);
	}

	@PostMapping("/item")
	public ResponseEntity<Item> saveItem(@RequestBody Item item){
		return new ResponseEntity<Item>(itemService.saveItem(item), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int user_id, @RequestBody User user){
		return new ResponseEntity<User>(userService.updateUser(user, user_id), HttpStatus.OK);
	}

	@PutMapping("/updateItem/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable("id") int item_id, @RequestBody Item item){
		return new ResponseEntity<Item>(itemService.updateItem(item, item_id), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int user_id){
		userService.deleteUser(user_id);
		return new ResponseEntity<String>("Users deleted succesfully" , HttpStatus.OK);
	}

	@DeleteMapping("deleteallusers")
	public ResponseEntity<String> deleteAllUsers(){
		userService.deleteAllUsers();
		return new ResponseEntity<String>("Users deleted succesfully" , HttpStatus.OK);
	}

	@DeleteMapping("/deleteItem/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") int item_id){
		itemService.deleteItem(item_id);
		return ResponseEntity.ok("{\"message\": \"Item Deleted Successfully\"}");
	}

	@GetMapping("/listOfDates")
	public List<Date> allDatesOfItems(){
		return itemService.allDatesOfItems();
	}
}
