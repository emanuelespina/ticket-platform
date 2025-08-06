package org.wdpt6.ticket_platform.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wdpt6.ticket_platform.ticket_platform.model.Ticket;
import org.wdpt6.ticket_platform.ticket_platform.model.Ticket.TicketStatus;
import org.wdpt6.ticket_platform.ticket_platform.model.User;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

    public List<Ticket> findByNameContainingIgnoreCase(String name);

    public List<Ticket> findByUser(User user);

    public List<Ticket> findByCategories(Integer id);

    public List<Ticket> findByStatus (TicketStatus status);
        
}
