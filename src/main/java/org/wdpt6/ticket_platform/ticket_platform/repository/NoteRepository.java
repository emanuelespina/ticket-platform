package org.wdpt6.ticket_platform.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wdpt6.ticket_platform.ticket_platform.model.Note;
import org.wdpt6.ticket_platform.ticket_platform.model.Ticket;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Integer> {

    public List<Note> findByTicket(Ticket ticket);
    
}
