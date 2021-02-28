package com.golomt.event.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.Transient;

import com.golomt.event.Repository.UserRepository;

@EnableAutoConfiguration
@Entity
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String eventName;
    private String eventDescription;
    private String eventStartTime;
    private String eventEndTime;
    private String eventWhere;
    private String webLink;
    private String coverPic;
    private String pic1;
    private String pic2;
    private String pic3;
    private String videoLink;
    //	private String fields;
    private String lottoType;
    private boolean active;
    private boolean delFlg;

    private String prizeName;
    private String prizePic;
    private String prizeTotal;
    private String changedTotal;
    private String mainColor;
    private String selectedLottery;

//    private String fieldName;
//    private String fieldType;
//    private String valueType;
//    private int minValue;
//    private int maxValue;
//    private String options;
//    private boolean required;

    //	private boolean lottoFlg;
    @Transient
    private int eventAllUserNumber, eventInvolvedUserNumber;

    public Event() {
        super();
    }

    public Event(long id, String eventName, String eventDescription, String eventStartTime, String eventEndTime, String eventWhere, String webLink, String coverPic, String pic1, String pic2, String pic3, String videoLink, String lottoType, boolean active, boolean delFlg, String prizeName, String prizePic, String prizeTotal, String changedTotal, String mainColor, int eventAllUserNumber, int eventInvolvedUserNumber, String selectedLottery) {
        this.id = id;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventWhere = eventWhere;
        this.webLink = webLink;
        this.coverPic = coverPic;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.videoLink = videoLink;
        this.lottoType = lottoType;
        this.active = active;
        this.delFlg = delFlg;
        this.prizeName = prizeName;
        this.prizePic = prizePic;
        this.prizeTotal = prizeTotal;
        this.changedTotal = changedTotal;
        this.mainColor = mainColor;
        this.selectedLottery = selectedLottery;
        this.eventAllUserNumber = eventAllUserNumber;
        this.eventInvolvedUserNumber = eventInvolvedUserNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

//	public String getFields() {
//		return fields;
//	}
//
//	public void setFields(String fields) {
//		this.fields = fields;
//	}

    public String getLottoType() {
        return lottoType;
    }

    public void setLottoType(String lotto) {
        this.lottoType = lottoType;
    }

    public int getEventAllUserNumber() {
        return eventAllUserNumber;
    }

    public void setEventAllUserNumber(int eventAllUserNumber) {
        this.eventAllUserNumber = eventAllUserNumber;
    }

    public int getEventInvolvedUserNumber() {
        return eventInvolvedUserNumber;
    }

    public String getEventWhere() {
        return eventWhere;
    }

    public void setEventWhere(String eventWhere) {
        this.eventWhere = eventWhere;
    }

    public void setEventInvolvedUserNumber(int eventInvolvedUserNumber) {
        this.eventInvolvedUserNumber = eventInvolvedUserNumber;
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

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizePic() {
        return prizePic;
    }

    public void setPrizePic(String prizePic) {
        this.prizePic = prizePic;
    }

    public String getPrizeTotal() {
        return prizeTotal;
    }

    public void setPrizeTotal(String prizeTotal) {
        this.prizeTotal = prizeTotal;
    }

    public String getChangedTotal() {
        return changedTotal;
    }

    public void setChangedTotal(String changedTotal) {
        this.changedTotal = changedTotal;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public String getSelectedLottery() {
        return selectedLottery;
    }

    public void setSelectedLottery(String selectedLottery) {
        this.selectedLottery = selectedLottery;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", eventName=" + eventName + ", eventDescription=" + eventDescription
                + ", eventStartTime=" + eventStartTime + ", eventEndTime=" + eventEndTime + ", webLink=" + webLink
                + ", active=" + active + ", delFlg=" + delFlg + ", eventAllUserNumber="
                + eventAllUserNumber + ", eventInvolvedUserNumber=" + eventInvolvedUserNumber + "]";
    }

}
