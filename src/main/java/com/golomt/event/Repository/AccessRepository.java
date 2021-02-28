package com.golomt.event.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.golomt.event.Model.AccessNumber;

@Repository
public interface AccessRepository extends JpaRepository<AccessNumber, String> {
	public AccessNumber findByAccessNumber(String AccessNumber);

	public boolean existsByAccessNumber(String accessNumber);

	public static final String GET_accessNumberId = "SELECT id FROM access_numbers a WHERE a.access_number = :accessNumber";
	public static final String Toggle_isUsed = "UPDATE access_numbers a SET is_used = true WHERE a.id = :accessNumberId";
	public static final String GET_isUsed = "SELECT is_used FROM access_numbers a WHERE a.access_number = :accessNumber";

	@Query(value = GET_accessNumberId, nativeQuery = true)
	public long getAccessNumberIdByAccessNumber(@Param("accessNumber") String accessNumber);
	
	@Transactional
	@Modifying
	@Query(value = Toggle_isUsed, nativeQuery = true)
	public void changeToUsed(@Param("accessNumberId") long accessNumberId);

	@Query(value = GET_isUsed, nativeQuery = true)
	public boolean isUsedAccessNumber(@Param("accessNumber") String accessNumber);
}
