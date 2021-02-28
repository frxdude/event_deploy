package com.golomt.event.DTO;

public class EventUserDTO {
	private long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String companyName;
	private String telNumber;
	private String password;
	private long roleId;
	private boolean active;
	private boolean delFlg;
	private boolean uploadAccess;
	private boolean qrAccess;
	private boolean jackAccess;

	public EventUserDTO(long id, String userName, String firstName, String lastName, String companyName, String telNumber, String password, long roleId, boolean active, boolean delFlg, boolean uploadAccess, boolean qrAccess, boolean jackAccess) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.telNumber = telNumber;
		this.password = password;
		this.roleId = roleId;
		this.active = active;
		this.delFlg = delFlg;
		this.uploadAccess = uploadAccess;
		this.qrAccess = qrAccess;
		this.jackAccess = jackAccess;
	}

	public EventUserDTO() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
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

	public boolean isUploadAccess() {
		return uploadAccess;
	}

	public void setUploadAccess(boolean uploadAccess) {
		this.uploadAccess = uploadAccess;
	}

	public boolean isQrAccess() {
		return qrAccess;
	}

	public void setQrAccess(boolean qrAccess) {
		this.qrAccess = qrAccess;
	}

	public boolean isJackAccess() {
		return jackAccess;
	}

	public void setJackAccess(boolean jackAccess) {
		this.jackAccess = jackAccess;
	}
}
