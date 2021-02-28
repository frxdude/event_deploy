package com.golomt.event.Repository;

import com.golomt.event.Model.EventFields;
import com.golomt.event.Model.EventMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventFieldsRepository extends JpaRepository<EventFields, String> {
    public static final String GET_fieldsByEventId = "SELECT * FROM event_fields e WHERE event_id = :eventId";

    @Query(value = GET_fieldsByEventId, nativeQuery = true)
    public List<EventFields> getFieldsByEventId(@Param("eventId") long eventId);
}
