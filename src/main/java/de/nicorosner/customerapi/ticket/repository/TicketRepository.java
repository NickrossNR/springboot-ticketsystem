package de.nicorosner.customerapi.ticket.repository;


import de.nicorosner.customerapi.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Optional: custom Abfragen z. B. findByErstellerId(), findByKundeId()
}
