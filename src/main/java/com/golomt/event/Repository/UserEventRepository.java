package com.golomt.event.Repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.golomt.event.LinkTable.UserEvent;

public interface UserEventRepository extends JpaRepository<UserEvent, String> {
	public static final String CHECK_UserAlreadyInvolved = "SELECT EXISTS(SELECT social_id, event_id FROM user_event_link ue WHERE ue.social_id = :socialId AND ue.event_id = :eventId)";

	@Query(value = CHECK_UserAlreadyInvolved, nativeQuery = true)
	public BigInteger checkUserEvent(@Param("socialId") String socialId, @Param("eventId") long eventId);
}
