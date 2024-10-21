package com.vcdev.event_management_system.repository;

import com.vcdev.event_management_system.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);

    Object findByDate(Date date);
    Object findByLocation(String location);
    Object findByName(String name);
}

