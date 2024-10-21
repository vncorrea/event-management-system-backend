package com.vcdev.event_management_system.repository;

import com.vcdev.event_management_system.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
    @Query("SELECT CASE WHEN COUNT(ue) > 0 THEN true ELSE false END FROM UserEvent ue WHERE ue.eventId = :eventId AND ue.userId = :userId")
    Boolean isUserInscribed(@Param("eventId") Long eventId, @Param("userId") Long userId);

    void deleteByEventIdAndUserId(Long eventId, Long userId);
}