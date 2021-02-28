package com.golomt.event.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name = "AccessNumbers")
public class AccessNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String accessNumber;
	private boolean isUsed;

	public AccessNumber() {
		super();
	}

	public AccessNumber(long id, String accessNumber, boolean isUsed) {
		super();
		this.id = id;
		this.accessNumber = accessNumber;
		this.isUsed = isUsed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccessNumber() {
		return accessNumber;
	}

	public void setAccessNumber(String accessNumber) {
		this.accessNumber = accessNumber;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
