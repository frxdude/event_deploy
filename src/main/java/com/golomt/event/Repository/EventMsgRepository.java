package com.golomt.event.Repository;

import com.golomt.event.Model.EventMsg;
import com.golomt.event.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventMsgRepository extends JpaRepository<EventMsg, String> {
    public Optional<EventMsg> findByEventId(long eventId);
}
