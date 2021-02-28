package com.golomt.event.Model;

import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String socialId;
    private String companyName;
    private String position;
    private String lastName;
    private String firstName;
    private String email;
    private String telNumber;
    private String accessNumber;
    private String involvedTime;
    private String regNum;
    private long eventId;
    private int paid;
    private String status;
    private boolean del_flg;
    private int spin;

    private String qrBase64;
    private String encodedData;


    public User(long id, String socialId, String companyName, String position, String lastName, String firstName, String email, String telNumber, String accessNumber, String involvedTime, String regNum, long eventId, int paid, int spin, String status, boolean del_flg, String qrBase64, String encodedData) {
        this.id = id;
        this.socialId = socialId;
        this.companyName = companyName;
        this.position = position;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.telNumber = telNumber;
        this.accessNumber = accessNumber;
        this.involvedTime = involvedTime;
        this.regNum = regNum;
        this.eventId = eventId;
        this.paid = paid;
        this.spin = spin;
        this.status = status;
        this.del_flg = del_flg;
        this.qrBase64 = qrBase64;
        this.encodedData = encodedData;
    }

    public User() {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
    }

    public String getInvolvedTime() {
        return involvedTime;
    }

    public void setInvolvedTime(String involvedTime) {
        this.involvedTime = involvedTime;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getSpin() {
        return spin;
    }

    public void setSpin(int spin) {
        this.spin = spin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDel_flg() {
        return del_flg;
    }

    public void setDel_flg(boolean del_flg) {
        this.del_flg = del_flg;
    }

    public String getQrBase64() {
        return qrBase64;
    }

    public void setQrBase64(String qrBase64) {
        this.qrBase64 = qrBase64;
    }

    public String getEncodedData() {
        return encodedData;
    }

    public void setEncodedData(String encodedData) {
        this.encodedData = encodedData;
    }
    //	@PrePersist
//	public void prePersist() {
//		String[] parts = { this.accessNumber.substring(0, this.accessNumber.length() / 2),
//				this.accessNumber.substring(this.accessNumber.length() / 2) };
//		String temp = parts[0] + this.id + parts[1];
//		this.encodedData = Base64.getEncoder().encodeToString(temp.getBytes());
//	}

}
