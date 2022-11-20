package com.academy.model.repository.interfaces;

import com.academy.model.entity.Passengers;
import com.academy.model.entity.Tickets;
import com.academy.model.entity.Trains;

import java.util.Date;
import java.util.List;

/**
 * @author : Volha Salash
 */

public interface TicketRepository {
    Tickets getTicket(int id);

    List<Tickets> getAllTickets();

    void addTicket(Tickets ticket);

    void editTicket(Tickets ticket);

    void deleteTicket(Tickets ticket);

    long getTicketCount();

    List<Tickets> getTicketsByPassenger(Passengers passenger);

    List<Tickets> getTicketsByTrainAndDate(Trains train, Date date);
}