package com.golomt.event.LinkTable;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@EnableAutoConfiguration
@Entity
@Table(name = "LotteryPrizeLink")
public class WLotteryPrize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long lotteryId;
    private long prizeId;
    private int total;

    public WLotteryPrize(){
    }

    public WLotteryPrize(long id, long lotteryId, long prizeId, int total) {
        this.id = id;
        this.lotteryId = lotteryId;
        this.prizeId = prizeId;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(long lotteryId) {
        this.lotteryId = lotteryId;
    }

    public long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(long prizeId) {
        this.prizeId = prizeId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
