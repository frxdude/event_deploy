package com.golomt.event.Repository;

import java.util.List;

import com.golomt.event.DTO.EventUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.golomt.event.Model.EventUser;

public interface EventUserRepository extends JpaRepository<EventUser, Long> {

	public static final String GET_info = "SELECT * FROM event_users eu WHERE eu.id IN (SELECT eel.event_id FROM eventuser_event_link eel"
			+ " WHERE eel.event_id = :eventId)";
	public static final String GET_userId = "SELECT id FROM event_users eu WHERE eu.user_name = :userName";
//	public static final String GET_eventUsers = "SELECT eu.*, erl.role_id from event_users eu INNER JOIN "
//										+ "eventuser_role_link erl ON eu.id = erl.event_user_id";
	public static final String GET_eventUsers = "SELECT * from event_users";

	@Query(value = GET_info, nativeQuery = true)
	public EventUser getEventUsersByEventId(@Param("eventId") long eventId);

	@Query(value = GET_userId, nativeQuery = true)
	public long getUserIdByUserName(@Param("userName") String userName);

	public boolean existsByUserName(String userName);

	@Query(value = GET_eventUsers, nativeQuery = true)
	public List<EventUser> getEventUsers();
}
