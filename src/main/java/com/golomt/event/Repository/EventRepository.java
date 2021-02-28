package com.golomt.event.Repository;
import com.golomt.event.Model.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.golomt.event.Model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    public static final String GET_lottoByEventId = "SELECT lotto FROM events e WHERE id = :eventId";

    @Query(value = GET_lottoByEventId, nativeQuery = true)
    public String getLottoByEventId(@Param("eventId") long eventId);
}
