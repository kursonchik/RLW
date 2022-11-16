package com.academy.service.impl;

import com.academy.service.interfaces.MessagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author : Volha Salash
 */

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessagingServiceImpl implements MessagingService {

    private final JmsTemplate jmsTemplate;

    @Override
    public void sendMessage() {
        jmsTemplate.send(session -> session.createTextMessage("Update timetable"));
        log.info("Message sent to queue");
    }
}
