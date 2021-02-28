package com.golomt.event.Repository;

import com.golomt.event.DTO.WinnerDTO;
import com.golomt.event.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface WJackpotRepository extends Repository<User, Long> {

}
