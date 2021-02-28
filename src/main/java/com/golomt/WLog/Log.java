package com.golomt.WLog;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.Table;

@EnableAutoConfiguration
@Entity
@Table(name = "Logs")
public class Log {
    private long id;
    private String createdTime;
    private long createdUserId;
    private String modifiedTime;
    private long modifiedUserId;

    public Log() {
    }

    public Log(long id, String createdTime, long createdUserId, String modifiedTime, long modifiedUserId) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdUserId = createdUserId;
        this.modifiedTime = modifiedTime;
        this.modifiedUserId = modifiedUserId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public long getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(long createdUserId) {
        this.createdUserId = createdUserId;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getModifiedUserId() {
        return modifiedUserId;
    }

    public void setModifiedUserId(long modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }
}
