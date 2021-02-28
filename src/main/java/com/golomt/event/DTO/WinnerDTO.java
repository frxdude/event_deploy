package com.golomt.event.DTO;

import org.springframework.beans.factory.annotation.Value;

public interface WinnerDTO {

    @Value("#{target.first_name}")
    String getFirstName();

    @Value("#{target.last_name}")
    String getLastName();

    @Value("#{target.access_number}")
    String getAccessNumber();

}
