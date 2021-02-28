package com.golomt.event.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name = "EventUsers")
public class EventUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String companyName;
	private String telNumber;
	private boolean active;
	private boolean delFlg;
	private boolean jackAccess;
	private boolean qrAccess;
	private boolean uploadAccess;

	public EventUser(long id, String userName, String firstName, String lastName, String companyName, String telNumber, boolean jackAccess, boolean qrAccess, boolean uploadAccess, boolean active, boolean delFlg) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.telNumber = telNumber;
		this.jackAccess = jackAccess;
		this.qrAccess = qrAccess;
		this.uploadAccess = uploadAccess;
		this.active = active;
		this.delFlg = delFlg;
	}

	public EventUser() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public boolean isJackAccess() {
		return jackAccess;
	}

	public void setJackAccess(boolean jackAccess) {
		this.jackAccess = jackAccess;
	}

	public boolean isQrAccess() {
		return qrAccess;
	}

	public void setQrAccess(boolean qrAccess) {
		this.qrAccess = qrAccess;
	}

	public boolean isUploadAccess() {
		return uploadAccess;
	}

	public void setUploadAccess(boolean uploadAccess) {
		this.uploadAccess = uploadAccess;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDelFlg() {
		return delFlg;
	}

	public void setDelFlg(boolean delFlg) {
		this.delFlg = delFlg;
	}
}
