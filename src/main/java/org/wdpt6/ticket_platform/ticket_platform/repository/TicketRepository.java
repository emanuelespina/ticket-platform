package org.wdpt6.ticket_platform.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wdpt6.ticket_platform.ticket_platform.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

    public List<Ticket> findByNameContainingIgnoreCase(String name);
        
}
