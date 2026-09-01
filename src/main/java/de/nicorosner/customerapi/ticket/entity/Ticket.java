package de.nicorosner.customerapi.ticket.entity;

import com.fasterxml.jackson.annotation.*;
import de.nicorosner.customerapi.kunde.entity.Kunde;
import de.nicorosner.customerapi.mitarbeiter.entity.Mitarbeiter;
import de.nicorosner.customerapi.ticket.status.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("ersteller-ticket")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ersteller_id", nullable = false)
    private Mitarbeiter ersteller;

    @JsonBackReference("bearbeiter-ticket")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bearbeiter_id")
    private Mitarbeiter bearbeiter;

    @JsonBackReference("kunde-ticket")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kunde_id", nullable = false)
    private Kunde kunde;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;
    
    private String notiz;
    private String beschreibung;
    
    @CreationTimestamp
    @Column(updatable= false)
    private LocalDateTime erstelldatum;
}
