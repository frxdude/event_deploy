package com.golomt.event.DTO;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface PrizeDTO {
//    private List<String> name;
//    private List<Integer> total;
//    private List<String> pic;

    @Value("#{target.name}")
    String getFirstName();

    @Value("#{target.total}")
    String getLastName();

    @Value("#{target.pic}")
    String getAccessNumber();

}