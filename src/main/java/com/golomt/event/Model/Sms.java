package com.golomt.event.Model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;

@EnableAutoConfiguration
@Entity
@Table(name = "SmsHistory")
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long eventId;
    private String telNo;
    private String text;
    private Date dateTime;

    public Sms() {
        super();
    }

    public Sms(long id, long eventId, String telNo, String text, Date dateTime) {
        this.id = id;
        this.eventId = eventId;
        this.telNo = telNo;
        this.text = text;
        this.dateTime = dateTime;
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

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
