package com.golomt.event.DTO;

public class WheelPrizeDTO {
    private boolean spin;
    private String prize;
    private int spinTotal;
    private int prizeIndex;

    public WheelPrizeDTO(boolean spin, String prize, int spinTotal, int prizeIndex) {
        this.spin = spin;
        this.prize = prize;
        this.spinTotal = spinTotal;
        this.prizeIndex = prizeIndex;
    }

    public WheelPrizeDTO() {
    }

    public boolean isSpin() {
        return spin;
    }

    public void setSpin(boolean spin) {
        this.spin = spin;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getSpinTotal() {
        return spinTotal;
    }

    public void setSpinTotal(int spinTotal) {
        this.spinTotal = spinTotal;
    }

    public int getPrizeIndex() {
        return prizeIndex;
    }

    public void setPrizeIndex(int prizeIndex) {
        this.prizeIndex = prizeIndex;
    }
}
