package com.golomt.event.DTO;

import com.golomt.event.Model.Event;
import com.golomt.event.Model.EventFields;
import org.springframework.data.annotation.Transient;

import java.util.List;

public class EventDTO {
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
    private String lottoType;
    private boolean active;
    private boolean delFlg;

    private String prizeName;
    private String prizePic;
    private String prizeTotal;
    private String changedTotal;
    private String mainColor;
    private String selectedLottery;

    @Transient
    private int eventAllUserNumber, eventInvolvedUserNumber;
    private List<EventFields> eveFields;

    public EventDTO() {
    }

    public EventDTO(long id, String eventName, String eventDescription, String eventStartTime, String eventEndTime, String eventWhere, String webLink, String coverPic, String pic1, String pic2, String pic3, String videoLink, String lottoType, boolean active, boolean delFlg, String prizeName, String prizePic, String prizeTotal, String changedTotal, String mainColor, int eventAllUserNumber, int eventInvolvedUserNumber, List<EventFields> eveFields, String selectedLottery) {
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
        this.eventAllUserNumber = eventAllUserNumber;
        this.eventInvolvedUserNumber = eventInvolvedUserNumber;
        this.eveFields = eveFields;
        this.selectedLottery = selectedLottery;
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

    public String getEventWhere() {
        return eventWhere;
    }

    public void setEventWhere(String eventWhere) {
        this.eventWhere = eventWhere;
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

    public String getLottoType() {
        return lottoType;
    }

    public void setLottoType(String lottoType) {
        this.lottoType = lottoType;
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

    public int getEventAllUserNumber() {
        return eventAllUserNumber;
    }

    public void setEventAllUserNumber(int eventAllUserNumber) {
        this.eventAllUserNumber = eventAllUserNumber;
    }

    public int getEventInvolvedUserNumber() {
        return eventInvolvedUserNumber;
    }

    public void setEventInvolvedUserNumber(int eventInvolvedUserNumber) {
        this.eventInvolvedUserNumber = eventInvolvedUserNumber;
    }

    public List<EventFields> getEveFields() {
        return eveFields;
    }

    public void setEveFields(List<EventFields> eveFields) {
        this.eveFields = eveFields;
    }

    public String getSelectedLottery() {
        return selectedLottery;
    }

    public void setSelectedLottery(String selectedLottery) {
        this.selectedLottery = selectedLottery;
    }
    //        private long id;
//    private String eventName;
//    private String eventDescription;
//    private String eventStartTime;
//    private String eventEndTime;
//    private String eventWhere;
//    private String webLink;
//    private String coverPic;
//    private String pic1;
//    private String pic2;
//    private String pic3;
//    private String videoLink;
//    private String lottoType;
//    private boolean active;
//    private boolean delFlg;
//    private int eventAllUserNumber, eventInvolvedUserNumber;
//
//    private String prizeName;
//    private String prizePic;
//    private String prizeTotal;
//    private String changedTotal;
//    private String mainColor;
//    private String fieldName;
//    private String fieldType;
//    private String valueType;
//    private int minValue;
//    private int maxValue;
//    private String options;
//    private boolean required;
//
//    public EventDTO() {
//    }
//
//    public EventDTO(long id, String eventName, String eventDescription, String eventStartTime, String eventEndTime, String eventWhere, String webLink, String coverPic, String pic1, String pic2, String pic3, String videoLink, String lottoType, boolean active, boolean delFlg, int eventAllUserNumber, int eventInvolvedUserNumber, String prizeName, String prizePic, String prizeTotal, String changedTotal, String mainColor, String fieldName, String fieldType, String valueType, int minValue, int maxValue, String options, boolean required) {
//        this.id = id;
//        this.eventName = eventName;
//        this.eventDescription = eventDescription;
//        this.eventStartTime = eventStartTime;
//        this.eventEndTime = eventEndTime;
//        this.eventWhere = eventWhere;
//        this.webLink = webLink;
//        this.coverPic = coverPic;
//        this.pic1 = pic1;
//        this.pic2 = pic2;
//        this.pic3 = pic3;
//        this.videoLink = videoLink;
//        this.lottoType = lottoType;
//        this.active = active;
//        this.delFlg = delFlg;
//        this.eventAllUserNumber = eventAllUserNumber;
//        this.eventInvolvedUserNumber = eventInvolvedUserNumber;
//        this.prizeName = prizeName;
//        this.prizePic = prizePic;
//        this.prizeTotal = prizeTotal;
//        this.changedTotal = changedTotal;
//        this.mainColor = mainColor;
//        this.fieldName = fieldName;
//        this.fieldType = fieldType;
//        this.valueType = valueType;
//        this.minValue = minValue;
//        this.maxValue = maxValue;
//        this.options = options;
//        this.required = required;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getEventName() {
//        return eventName;
//    }
//
//    public void setEventName(String eventName) {
//        this.eventName = eventName;
//    }
//
//    public String getEventDescription() {
//        return eventDescription;
//    }
//
//    public void setEventDescription(String eventDescription) {
//        this.eventDescription = eventDescription;
//    }
//
//    public String getEventStartTime() {
//        return eventStartTime;
//    }
//
//    public void setEventStartTime(String eventStartTime) {
//        this.eventStartTime = eventStartTime;
//    }
//
//    public String getEventEndTime() {
//        return eventEndTime;
//    }
//
//    public void setEventEndTime(String eventEndTime) {
//        this.eventEndTime = eventEndTime;
//    }
//
//    public String getEventWhere() {
//        return eventWhere;
//    }
//
//    public void setEventWhere(String eventWhere) {
//        this.eventWhere = eventWhere;
//    }
//
//    public String getWebLink() {
//        return webLink;
//    }
//
//    public void setWebLink(String webLink) {
//        this.webLink = webLink;
//    }
//
//    public String getCoverPic() {
//        return coverPic;
//    }
//
//    public void setCoverPic(String coverPic) {
//        this.coverPic = coverPic;
//    }
//
//    public String getPic1() {
//        return pic1;
//    }
//
//    public void setPic1(String pic1) {
//        this.pic1 = pic1;
//    }
//
//    public String getPic2() {
//        return pic2;
//    }
//
//    public void setPic2(String pic2) {
//        this.pic2 = pic2;
//    }
//
//    public String getPic3() {
//        return pic3;
//    }
//
//    public void setPic3(String pic3) {
//        this.pic3 = pic3;
//    }
//
//    public String getVideoLink() {
//        return videoLink;
//    }
//
//    public void setVideoLink(String videoLink) {
//        this.videoLink = videoLink;
//    }
//
//    public String getLottoType() {
//        return lottoType;
//    }
//
//    public void setLottoType(String lottoType) {
//        this.lottoType = lottoType;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//
//    public boolean isDelFlg() {
//        return delFlg;
//    }
//
//    public void setDelFlg(boolean delFlg) {
//        this.delFlg = delFlg;
//    }
//
//    public int getEventAllUserNumber() {
//        return eventAllUserNumber;
//    }
//
//    public void setEventAllUserNumber(int eventAllUserNumber) {
//        this.eventAllUserNumber = eventAllUserNumber;
//    }
//
//    public int getEventInvolvedUserNumber() {
//        return eventInvolvedUserNumber;
//    }
//
//    public void setEventInvolvedUserNumber(int eventInvolvedUserNumber) {
//        this.eventInvolvedUserNumber = eventInvolvedUserNumber;
//    }
//
//    public String getPrizeName() {
//        return prizeName;
//    }
//
//    public void setPrizeName(String prizeName) {
//        this.prizeName = prizeName;
//    }
//
//    public String getPrizePic() {
//        return prizePic;
//    }
//
//    public void setPrizePic(String prizePic) {
//        this.prizePic = prizePic;
//    }
//
//    public String getPrizeTotal() {
//        return prizeTotal;
//    }
//
//    public void setPrizeTotal(String prizeTotal) {
//        this.prizeTotal = prizeTotal;
//    }
//
//    public String getChangedTotal() {
//        return changedTotal;
//    }
//
//    public void setChangedTotal(String changedTotal) {
//        this.changedTotal = changedTotal;
//    }
//
//    public String getMainColor() {
//        return mainColor;
//    }
//
//    public void setMainColor(String mainColor) {
//        this.mainColor = mainColor;
//    }
//
//    public String getFieldName() {
//        return fieldName;
//    }
//
//    public void setFieldName(String fieldName) {
//        this.fieldName = fieldName;
//    }
//
//    public String getFieldType() {
//        return fieldType;
//    }
//
//    public void setFieldType(String fieldType) {
//        this.fieldType = fieldType;
//    }
//
//    public String getValueType() {
//        return valueType;
//    }
//
//    public void setValueType(String valueType) {
//        this.valueType = valueType;
//    }
//
//    public int getMinValue() {
//        return minValue;
//    }
//
//    public void setMinValue(int minValue) {
//        this.minValue = minValue;
//    }
//
//    public int getMaxValue() {
//        return maxValue;
//    }
//
//    public void setMaxValue(int maxValue) {
//        this.maxValue = maxValue;
//    }
//
//    public String getOptions() {
//        return options;
//    }
//
//    public void setOptions(String options) {
//        this.options = options;
//    }
//
//    public boolean isRequired() {
//        return required;
//    }
//
//    public void setRequired(boolean required) {
//        this.required = required;
//    }
}
