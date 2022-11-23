package com.academy.model.repository.impl;

import com.academy.model.entity.Passengers;
import com.academy.model.entity.Tickets;
import com.academy.model.entity.Trains;
import com.academy.model.repository.interfaces.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * @author : Volha Salash
 */
@Repository
public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Tickets getTicket(int id) {
        return entityManager.find(Tickets.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tickets> getAllTickets() {
        Query query = entityManager.createQuery("SELECT t FROM Tickets t");
        return query.getResultList();
    }

    @Override
    public void addTicket(Tickets ticket) {
        entityManager.persist(entityManager.merge(ticket));
    }

    @Override
    public void editTicket(Tickets ticket) {
        entityManager.merge(ticket);
    }

    @Override
    public void deleteTicket(Tickets ticket) {
        entityManager.remove(entityManager.merge(ticket));
    }

    @Override
    public long getTicketCount() {
        Query query = entityManager.createQuery("SELECT count(t) FROM Tickets t");
        return (Long) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tickets> getTicketsByPassenger(Passengers passenger) {
        Query query = entityManager.createQuery("SELECT t FROM Tickets t " +
                "WHERE t.passenger = :passenger ORDER BY t.date, t.departureTime");
        query.setParameter("passenger", passenger);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tickets> getTicketsByTrainAndDate(Trains train, Date date) {
        Query query = entityManager.createQuery("SELECT t FROM Tickets t WHERE :train member of t.trains AND t.date = :date");
        query.setParameter("train", train);
        query.setParameter("date", date);
        return query.getResultList();
    }
}