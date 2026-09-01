package de.nicorosner.customerapi.kunde.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.nicorosner.customerapi.ticket.entity.Ticket;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Kunde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference("kunde-ticket")
    @OneToMany(mappedBy = "kunde", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    private String vorname;
    private String nachname;
    private String email;
    private String telefonnummer;
    private String firmenname;
    private String adresse;

    public Kunde(String name, String nachname) {
        this.vorname = name;
        this.nachname = nachname;
    }
}