package com.wechat.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.wechat.dao.Dao;
import com.wechat.dao.entity.Admin;

public class AdminDao extends Dao<Admin> {

	public AdminDao(Session session) {
		super(session);
	}

	@SuppressWarnings("rawtypes")
	public Admin get(String name) {
		List list = session.createQuery("from Admin where name=:name").setString("name", name).list();
		if (list.size() == 0) {
			return null;
		} else {
			return (Admin) list.get(0);
		}
	}

}
