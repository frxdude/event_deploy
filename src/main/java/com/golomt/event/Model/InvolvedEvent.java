package com.golomt.event.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.sun.istack.NotNull;

@EnableAutoConfiguration
@Entity
@Table(name = "InvolvedEvents")
public class InvolvedEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private long eventId;
	@NotNull
	private String userEncodedData;
	@NotNull
	private String involvedTime;
	@NotNull
	private long eventUserId;

	public InvolvedEvent() {
		super();
	}

	public InvolvedEvent(long id, long eventId, String userEncodedData, long eventUserId) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.userEncodedData = userEncodedData;
		this.eventUserId = eventUserId;
	}
	
	public long getEventUserId() {
		return eventUserId;
	}

	public void setEventUserId(long eventUserId) {
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

	public String getUserEncodedData() {
		return userEncodedData;
	}

	public void setUserEncodedData(String userEncodedData) {
		this.userEncodedData = userEncodedData;
	}

	public String getInvolvedTime() {
		return involvedTime;
	}

	public void setInvolvedTime(String involvedTime) {
		this.involvedTime = involvedTime;
	}

	@PrePersist
	public void prePersist() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		this.involvedTime = dtf.format(now);
	}
}
