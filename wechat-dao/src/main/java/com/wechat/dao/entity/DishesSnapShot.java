package com.wechat.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class DishesSnapShot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private Dishes dishes;
	private String name;
	@OneToMany
	private List<OptionItem> optionItems = new ArrayList<OptionItem>();// 用户具体选择的选项

	public Dishes getDishes() {
		return dishes;
	}

	public void setDishes(Dishes dishes) {
		this.dishes = dishes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OptionItem> getOptionItems() {
		return optionItems;
	}

	public void setOptionItems(List<OptionItem> optionItems) {
		this.optionItems = optionItems;
	}

}
