package com.golomt.event.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.golomt.event.Model.InvolvedEvent;

@Repository
public interface InvolvedEventRepository extends JpaRepository<InvolvedEvent, String> {
	public static final String DELETE_UserInInvolvedEvent = "DELETE FROM involved_events WHERE (event_id = :eventId AND user_encoded_data = :userEncodedData)";
	public static final String GET_countOfEventUsers = "SELECT COUNT(*) FROM involved_events WHERE involved_events.event_id = :eventId";

	@Modifying
	@Transactional
	@Query(value = DELETE_UserInInvolvedEvent, nativeQuery = true)
	public void deleteUserInInvolvedEvent(@Param("eventId") long eventId,
			@Param("userEncodedData") String userEncodedData);

	@Query(value = GET_countOfEventUsers, nativeQuery = true)
	public int getCountOfEventUsers(@Param("eventId") long eventId);
	
	int countByEventId(long eventId);
}
