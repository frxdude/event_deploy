package com.golomt.event.LinkTable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name = "MenuRoleLink")
public class MenuRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long menuId;
	private long roleId;

	public MenuRole() {
		super(); 
	}

	public MenuRole(long id, long menuId, long roleId) {
		super();
		this.id = id;
		this.menuId = menuId;
		this.roleId = roleId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
