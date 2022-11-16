package com.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Volha Salash
 */

@Controller
@RequestMapping("/active")
public class MainController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping(value = "send/{message}", produces = "text/hml")
    public String sendMessage(@PathVariable("message") String message) {
        jmsTemplate.convertAndSend("superqueue", message);
        return "done";
    }

}

