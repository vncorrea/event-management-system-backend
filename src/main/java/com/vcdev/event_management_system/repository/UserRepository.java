package com.vcdev.event_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vcdev.event_management_system.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
