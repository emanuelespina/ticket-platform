package org.wdpt6.ticket_platform.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wdpt6.ticket_platform.ticket_platform.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
