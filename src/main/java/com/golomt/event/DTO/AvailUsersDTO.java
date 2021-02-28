package com.golomt.event.DTO;

import org.springframework.beans.factory.annotation.Value;

public interface AvailUsersDTO {

    @Value("#{target.first_name}")
    String getFirstName();

    @Value("#{target.access_number}")
    String getAccessNumber();
}
