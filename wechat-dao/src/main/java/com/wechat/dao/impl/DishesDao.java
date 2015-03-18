package com.wechat.dao.impl;

import org.hibernate.Session;

import com.wechat.dao.Dao;
import com.wechat.dao.entity.Dishes;
import com.wechat.dao.entity.Options;

public class DishesDao extends Dao<Dishes> {

	public DishesDao(Session session) {
		super(session);
	}

	public void addOption(Options option) {
		throw new RuntimeException("Not Implemented");
	}

	public void deleteOption(Options option) {
		throw new RuntimeException("Not Implemented");
	}

	public void updateOptionInfo(Options option) {
		throw new RuntimeException("Not Implemented");
	}

}
