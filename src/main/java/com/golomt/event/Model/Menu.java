package com.golomt.event.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name = "Menus")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String menuName;

	public Menu() {
		super();
	}

	public Menu(long id, String menuName) {
		super();
		this.id = id;
		this.menuName = menuName;
	}
	public long getId() {

		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
