package com.golomt.event.Repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.golomt.event.Model.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, String> {

//	public static final String IS_UserExists = "SELECT EXISTS(SELECT * from event_users eu, user_auth ua WHERE eu.id=:userId)";
//
//	@Query(value = IS_UserExists, nativeQuery = true)
//	public long isEventUserExists(@Param("userId") long userId);
//	
	public boolean existsByUserId(long userId);
	public UserAuth findByUserId(long userId);
	
	public static final String TO_Login = "SELECT EXISTS(SELECT user_id, password FROM user_auth ua WHERE ua.user_id = :userId AND ua.password = :password)";

	@Query(value = TO_Login, nativeQuery = true)
	public BigInteger toLogin(@Param("userId") long userId, @Param("password") String password);
//	public long findUserId
	
}
