package com.golomt.event.DTO;
import org.springframework.beans.factory.annotation.Value;

public interface AvailUsersDTO1 {

    @Value("#{target.first_name}")
    String getFirstName();

    @Value("#{target.tel_number}")
    String getTelNumber();
}
