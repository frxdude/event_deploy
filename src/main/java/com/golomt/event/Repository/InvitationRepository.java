package com.golomt.event.Repository;

import com.golomt.event.Model.AccessNumber;
import com.golomt.event.Model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InvitationRepository extends JpaRepository<Invitation, String> {
    public Invitation findByBranch(String branch);

    public boolean existsByBranch(String branch);

    public static final String GET_branchEvId = "SELECT id FROM invitations a WHERE a.branch = :branch and a.event_id = :eventId";
    public static final String Increment_usedCount = "UPDATE invitations a SET used_count = used_count + 1 WHERE a.id = :invitationId";
    public static final String GET_usedCount = "SELECT used_count FROM invitations a WHERE a.id = :invitationId";

    @Query(value = GET_branchEvId, nativeQuery = true)
    public long getInvitationIdByBranch(@Param("branch") String branch, @Param("eventId") long eventId);

    @Transactional
    @Modifying
    @Query(value = Increment_usedCount, nativeQuery = true)
    public void incrementUsedCount(@Param("invitationId") long invitationId);

    @Query(value = GET_usedCount, nativeQuery = true)
    public int getUsedCountBranch(@Param("invitationId") long invitationId);
}
