package org.wdpt6.ticket_platform.ticket_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

       @GetMapping
    public String homepage() {

        return "pages/home";
    }
    
}
