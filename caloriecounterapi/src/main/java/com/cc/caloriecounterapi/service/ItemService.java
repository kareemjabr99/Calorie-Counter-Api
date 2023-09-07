package com.cc.caloriecounterapi.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.cc.caloriecounterapi.model.Item;

public interface ItemService {

	Item saveItem(Item item);
	
	Item createItemForUser(int user_id, Item item);

	Item updateItem(Item item, int item_id);
	
	void deleteItem(int item_id);
	
	List<Item> getAllItemsOfUser(int user_id);

	List<Item> getAllItems();
	
	List<Item> getItemsByDateOfAUser(LocalDate date, int user_id);

	Item getItemById(int item_id);

	List<Date> allDatesOfItems();

	List<Item> itemsOfADate(LocalDate date);

}
