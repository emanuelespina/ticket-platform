package org.wdpt6.ticket_platform.ticket_platform.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.wdpt6.ticket_platform.ticket_platform.model.Ticket;
import org.wdpt6.ticket_platform.ticket_platform.repository.CategoryRepository;
import org.wdpt6.ticket_platform.ticket_platform.repository.NoteRepository;
import org.wdpt6.ticket_platform.ticket_platform.repository.TicketRepository;
import org.wdpt6.ticket_platform.ticket_platform.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NoteRepository noteRepository;


    @GetMapping
    public String index(Model model, Authentication authentication, @RequestParam( name = "keyword", required = false) String keyword) {

        List<Ticket> tickets;

        if (keyword != null && !keyword.isEmpty()) {

            tickets = ticketRepository.findByNameContainingIgnoreCase(keyword);
            
        }else {

            tickets = ticketRepository.findAll();

        }

        model.addAttribute("tickets", tickets);

        return "tickets/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id) { 
        
        Ticket ticket = ticketRepository.findById(id).get();
        
        model.addAttribute("ticket", ticket);

        model.addAttribute("notes", noteRepository.findByTicket(ticket));
 
        return "tickets/show";
    }
    

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("ticket", new Ticket());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());

        return "tickets/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ticket") Ticket fromTicket, BindingResult bindingResult, Model model) {
        
          if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            return "tickets/create";
        }

        ticketRepository.save(fromTicket);

        return "redirect:/tickets";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("ticket", ticketRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());

        return "tickets/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("ticket") Ticket formTicket, BindingResult bindingResult, Model model) {
        
          if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            return "/tickets/edit";
        }

        // Salva il libro nel DB
        ticketRepository.save(formTicket);

        return "redirect:/tickets";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){

        Ticket ticketToDelete = ticketRepository.findById(id).get();   
                
        ticketRepository.delete(ticketToDelete);

        return "redirect:/tickets";
    }

    
    
    
    
    
    
}
