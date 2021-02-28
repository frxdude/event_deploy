package com.golomt.event.Repository;

import com.golomt.event.DTO.LoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.golomt.event.LinkTable.EventUserRole;

public interface EventUserRoleRepository extends JpaRepository<EventUserRole, String> {

	public static final String FIND_roleId = "SELECT erl.role_id, eu.qr_access, eu.upload_access, eu.jack_access FROM eventuser_role_link erl INNER JOIN event_users eu ON eu.id = erl.event_user_id WHERE erl.event_user_id = :eventUserId";

	@Query(value = FIND_roleId, nativeQuery = true)
	public LoginDTO getRoleIdByEventUserId(@Param(value = "eventUserId") long eventUserId);
}
