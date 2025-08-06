package org.wdpt6.ticket_platform.ticket_platform.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wdpt6.ticket_platform.ticket_platform.model.Ticket;
import org.wdpt6.ticket_platform.ticket_platform.model.Ticket.TicketStatus;
import org.wdpt6.ticket_platform.ticket_platform.repository.TicketRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping()
    public ResponseEntity<List<Ticket>> index() {

        List<Ticket> tickets = ticketRepository.findAll();

          if (tickets.size() == 0) {
            return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")    
    public List<Ticket> getTicketsByCategoria(@PathVariable Integer id) {

        return ticketRepository.findByCategories(id);

    }

    @GetMapping("/status/{status}")
    public List<Ticket> getMethodName(@RequestParam String status) {

        return ticketRepository.findByStatus(TicketStatus.valueOf(status.toUpperCase()));
    }
    
    
    
}
