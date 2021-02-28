package com.golomt.event.Model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@EnableAutoConfiguration
@Entity
@Table(name = "EventMsg")
public class EventMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long eventId;
    private String text;

    public EventMsg() {
        super();
    }

    public EventMsg(long id, long eventId, String text) {
        this.id = id;
        this.eventId = eventId;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
