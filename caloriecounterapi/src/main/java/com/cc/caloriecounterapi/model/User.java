package com.cc.caloriecounterapi.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Integer user_id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "list_of_items")
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Item> listOfItems;

//	@ElementCollection
//	@CollectionTable(name = "dates_table", joinColumns = @JoinColumn(name = "entity_id"))
//	@Column(name = "date")
//	private List<String> listOfDates;
	

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String email, List<Item> listOfItems) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.listOfItems = listOfItems;
	}

	public Integer getId() {
		return user_id;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setId(Integer id) {
		this.user_id = id;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public List<Item> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(List<Item> listOfItems) {
		this.listOfItems = listOfItems;
	}

//	public List<String> getListOfDates() {
//		return listOfDates;
//	}
}
