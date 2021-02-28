package com.golomt.event.DTO;

public class SMSDTO {
    private String text;
    private String mobileNo;

    public SMSDTO(String text, String mobileNo) {
        this.text = text;
        this.mobileNo = mobileNo;
    }

    public SMSDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
