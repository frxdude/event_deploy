package com.golomt.event.LinkTable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name = "EventuserEventLink")
public class EventUserEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long eventId;
	private long eventUserId; 

	public EventUserEvent() {
		super();
	}

	public EventUserEvent(long id, long eventId, long eventUserId) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.eventUserId = eventUserId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getEventUserId() {
		return eventUserId;
	}

	public void setEventUserId(long eventUserId) {
		this.eventUserId = eventUserId;
	}

}
