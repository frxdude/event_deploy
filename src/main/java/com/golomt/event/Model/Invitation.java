package com.golomt.event.Model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@EnableAutoConfiguration
@Entity
@Table(name = "Invitations")
public class Invitation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String branch;
	private int usedCount;
	private long eventId;

	public Invitation() {
		super();
	}

	public Invitation(long id, String branch, int usedCount, long eventId) {
		super();
		this.id = id;
		this.branch = branch;
		this.usedCount = usedCount;
		this.eventId = eventId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(int usedCount) {
		this.usedCount = usedCount;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
}
