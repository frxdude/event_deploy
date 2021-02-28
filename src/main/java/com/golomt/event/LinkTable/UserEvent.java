package com.golomt.event.LinkTable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name = "UserEventLink")
public class UserEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String socialId;
	private long eventId;

	public UserEvent() {
		super();
	}

	public UserEvent(long id, String socialId, long eventId) {
		super();
		this.id = id;
		this.socialId = socialId;
		this.eventId = eventId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

}
