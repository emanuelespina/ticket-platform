package org.wdpt6.ticket_platform.ticket_platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wdpt6.ticket_platform.ticket_platform.model.User;
import org.wdpt6.ticket_platform.ticket_platform.model.Ticket;


public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByTickets(Ticket ticket);

    Optional<User> findByUsername(String username);
    
}
