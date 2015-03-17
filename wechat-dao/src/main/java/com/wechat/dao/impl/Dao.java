package com.wechat.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;

public class Dao<T> {

	protected Session session = null;
	protected Class<?> entityClass;

	private boolean isActive = false;

	public Dao(Session session) {
		this.session = session;
		this.isActive = session.getTransaction().isActive();

		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<?>) params[0];
	}

	public void beginTransaction() {
		if (!isActive) {
			session.beginTransaction();
		}
	}

	public void commit() {
		if (!isActive) {
			session.getTransaction().commit();
		}
	}

	public void rollback() {
		if (!isActive) {
			session.getTransaction().rollback();
		}
	}

	public void save(T entity) {
		beginTransaction();
		try {
			session.save(entity);
			commit();
		} catch (RuntimeException e) {
			rollback();
			session.clear();
			throw e;
		}
	}

	public void update(T entity) {
		beginTransaction();
		try {
			session.update(entity);
			commit();
		} catch (RuntimeException e) {
			rollback();
			session.clear();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public T get(long id) {
		try {
			T obj = (T) session.get(entityClass, id);
			return obj;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> list() {
		try {
			String query = String.format("from " + entityClass.getSimpleName());
			List<T> list = (List<T>) session.createQuery(query).list();
			return list;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void delete(long id) {
		beginTransaction();
		try {
			String hql = String.format("delete %s where id = %d", entityClass.getSimpleName(), id);
			System.out.println(hql);
			session.createQuery(hql).executeUpdate();
			commit();
		} catch (RuntimeException e) {
			rollback();
			throw e;
		} finally {
			session.clear();
		}
	}
}
