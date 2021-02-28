package com.golomt.event.Model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@EnableAutoConfiguration
@Entity
@Table(name = "EventFields")
public class EventFields {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long eventId;
    private String fieldTitle;
    private String fieldName;
    private String fieldType;
    private String valueType;
    private int minValue;
    private int maxValue;
    private String options;
    private boolean required;

    public EventFields() {
    }

    public EventFields(long id, long eventId, String fieldName, String fieldTitle, String fieldType, String valueType, int minValue, int maxValue, String options, boolean required) {
        this.id = id;
        this.eventId = eventId;
        this.fieldTitle = fieldTitle;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.valueType = valueType;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.options = options;
        this.required = required;
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

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldTitle() {
        return fieldTitle;
    }

    public void setFieldTitle(String fieldTitle) {
        this.fieldTitle = fieldTitle;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
