package com.wechat.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @author yml
 *
 *         不可变对象,dao不提供修改接口
 */
@Entity
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "option_id")
	private List<OptionItem> items = new ArrayList<OptionItem>();

	public List<OptionItem> getItems() {
		return items;
	}

	public void setItems(List<OptionItem> items) {
		this.items = items;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
