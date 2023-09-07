 package com.cc.caloriecounterapi.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cc.caloriecounterapi.exception.ResourceNotFoundException;
import com.cc.caloriecounterapi.model.Item;
import com.cc.caloriecounterapi.model.User;
import com.cc.caloriecounterapi.repository.ItemRepository;
import com.cc.caloriecounterapi.repository.UserRepository;
import com.cc.caloriecounterapi.service.ItemService;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class ItemServiceImpl implements ItemService {
	
	private ItemRepository itemRepository;
	private UserRepository userRepository;

	public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
		super();
		this.itemRepository = itemRepository;
		this.userRepository = userRepository;
	}

	public Item saveItem(Item item){
		return itemRepository.save(item);
	}

	@Override
	public Item getItemById(int item_id) {
		Item existingItem = itemRepository.findById(item_id).orElseThrow(
				() -> new ResourceNotFoundException("Item", "ID", item_id)
		);
		return existingItem;
	}

	@Override
	public Item createItemForUser(int user_id, Item item) {
		
		User existingUser = userRepository.findById(user_id).orElseThrow(
				() -> new ResourceNotFoundException("User", "ID", user_id));
		
        item.setUser(existingUser);
        existingUser.getListOfItems().add(item);
        userRepository.save(existingUser);
        return itemRepository.save(item);
	}

	@Override
	public Item updateItem(Item item, int item_id) {
		Item existingItem = itemRepository.findById(item_id).orElseThrow(
				() -> new ResourceNotFoundException("Item", "Id", item_id));
		existingItem.setName(item.getName());
		existingItem.setCalories(item.getCalories());
		existingItem.setDateOfItem(item.getDateOfItem());
		existingItem.setUser(item.getUser());
		return itemRepository.save(existingItem);
	}

	@Override
	public void deleteItem(int item_id) {
		itemRepository.findById(item_id).orElseThrow(
				() -> new ResourceNotFoundException("Item", "ID", item_id));

		itemRepository.deleteById(item_id);
	}

	public List<Item> getAllItems(){
		return itemRepository.findAll();
	}
	@Override
	public List<Item> getAllItemsOfUser(int user_id) {
		
		User existingUser = userRepository.findById(user_id).orElseThrow(
				() -> new ResourceNotFoundException("User", "ID", user_id));
		
		return existingUser.getListOfItems();
	}

	@Override
	public List<Item> getItemsByDateOfAUser(LocalDate date, int user_id) {
		User existingUser = userRepository.findById(user_id).orElseThrow(
				() -> new ResourceNotFoundException("User", "ID", user_id));
		List<Item> items = new ArrayList<>();
		for (Item item : existingUser.getListOfItems()) {
	        LocalDate itemDate = item.getDateOfItem().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        if (itemDate.isEqual(date)) {
	            items.add(item);
	        }
	    }
		return items;
	}
	@Override
	public List<Date> allDatesOfItems() {
		List<Item> allItems = itemRepository.findAll();
		List<Date> listOfDates = allItems.stream().map(Item::getDateOfItem).distinct().collect(Collectors.toList());
		return listOfDates;
	}

	@Override
	public List<Item> itemsOfADate(LocalDate date) {
		List<Item> resultItems = new ArrayList<>();
		if(itemRepository.count() != 0) {
			for (int i = 0; i < itemRepository.count(); i++) {
				Item item = itemRepository.findAll().get(i);
				LocalDate itemDate = item.getDateOfItem().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if (itemDate.isEqual(date)) {
					resultItems.add(item);
				}
			}
		}
		else{ return resultItems; }

		return resultItems;
	}
}
