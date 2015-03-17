package com.wechat.dao.entity;

import java.util.List;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
=======
import javax.persistence.Entity;
>>>>>>> dao base class and entity
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
	private long id = 0;
	@Column(unique = true)
	private String name;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_id")
	private List<Dishes> dishes;
	@OneToMany
	@JoinColumn(name = "seller_id")
	private List<Orders> orders;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
=======
	private long id;

	private String name;
	@OneToMany
	@JoinColumn(name = "seller_id")
	private List<Dishes> dishes;

	public List<Dishes> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dishes> dishes) {
		this.dishes = dishes;
>>>>>>> dao base class and entity
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

<<<<<<< HEAD
	public List<Dishes> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dishes> dishes) {
		this.dishes = dishes;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
=======
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
>>>>>>> dao base class and entity
	}
}
