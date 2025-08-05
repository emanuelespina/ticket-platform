package org.wdpt6.ticket_platform.ticket_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wdpt6.ticket_platform.ticket_platform.model.Note;
import org.wdpt6.ticket_platform.ticket_platform.model.Ticket;
import org.wdpt6.ticket_platform.ticket_platform.model.User;
import org.wdpt6.ticket_platform.ticket_platform.repository.NoteRepository;
import org.wdpt6.ticket_platform.ticket_platform.repository.TicketRepository;
import org.wdpt6.ticket_platform.ticket_platform.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/operators")
public class OperatorController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/{id}")
    public String index(Model model, @PathVariable Integer id, @RequestParam(name = "keyword", required = false) String keyword) {

        List<Ticket> tickets;

        if (keyword != null && !keyword.isEmpty()) {

            tickets = ticketRepository.findByNameContainingIgnoreCase(keyword);

        } else {

            tickets = ticketRepository.findAll();

        }

        User user = userRepository.findById(id).get();

        model.addAttribute("user", user);

        model.addAttribute("tickets", ticketRepository.findByUser(user));

        return "operators/index";
    }

    @GetMapping("/answer/{id}")
    public String answer(Model model, @PathVariable Integer id) {

        Ticket ticket = ticketRepository.findById(id).get();

        Note note = new Note();

        model.addAttribute("ticket", ticket);

        model.addAttribute("notes", noteRepository.findByTicket(ticket));

        model.addAttribute("newNote", note);

        return "operators/answer";
    }

    @PostMapping("/answer/{id}")
    public String addNote(@RequestParam String text, Model model, @PathVariable Integer id) {

        Ticket ticket = ticketRepository.findById(id).get();

        Note note = new Note();

        note.setTicket(ticket);

        note.setUser(ticket.getUser());

        note.setText(text);

        noteRepository.save(note);

        return "redirect:/operators/" + ticket.getUser().getId();
    }

}
