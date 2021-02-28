package com.golomt.event.Model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@EnableAutoConfiguration
@Entity
@Table(name = "WJackpots")
public class WJackpot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int count;
    private int digit;

    public WJackpot(long id, int count, int digit) {
        this.id = id;
        this.count = count;
        this.digit = digit;
    }

    public WJackpot() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }
}
