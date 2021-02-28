package com.golomt.event.LinkTable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name = "EventuserRoleLink")
public class EventUserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long roleId;
	private long eventUserId;

	public EventUserRole() {
		super();
	}

	public EventUserRole(long id, long roleId, long eventUserId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.eventUserId = eventUserId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getEventUserId() {
		return eventUserId;
	}

	public void setEventUserId(long eventUserId) {
		this.eventUserId = eventUserId;
	}

}
