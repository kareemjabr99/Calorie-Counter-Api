package com.cc.caloriecounterapi.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer item_id;
	
	@Column
	private String name;
	
	@Column
	private Double calories;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfItem;

	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonBackReference
    private User user;

	
	public Item() {
		// TODO Auto-generated constructor stub
	}


	public Item(String name, Double calories, Date dateOfItem) {
		super();
		this.name = name;
		this.calories = calories;
		this.dateOfItem = dateOfItem;
//		this.user = user;
	}

	public Integer getItem_id() {
		return item_id;
	}

	
	public String getName() {
		return name;
	}


	public Double getCalories() {
		return calories;
	}


	public User getUser() {
		return user;
	}


	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setCalories(Double calories) {
		this.calories = calories;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Date getDateOfItem() {
		return dateOfItem;
	}

	
	public void setDateOfItem(Date dateOfItem) {
		this.dateOfItem = dateOfItem;
	}
	

}
