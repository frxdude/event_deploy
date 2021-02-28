package com.golomt.event.Repository;

import com.golomt.event.Model.WUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WUserRepository extends JpaRepository<WUser, Long> {
}
