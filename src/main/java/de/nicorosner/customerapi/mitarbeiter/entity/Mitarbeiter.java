package de.nicorosner.customerapi.mitarbeiter.entity;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import de.nicorosner.customerapi.anmeldung.entity.Anmeldedaten;
import de.nicorosner.customerapi.ticket.entity.Ticket;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mitarbeiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vorname; 
    private String nachname;
    private String email;
    private String telefonnummer;

    @JsonManagedReference("ersteller-ticket")
    @OneToMany(mappedBy = "ersteller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> erstellteTickets = new ArrayList<>();

    @JsonManagedReference("bearbeiter-ticket")
    @OneToMany(mappedBy = "bearbeiter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> bearbeiteteTickets = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "anmeldedaten_id")
    @JsonManagedReference("mitarbeiter-anmeldedaten")
    private Anmeldedaten anmeldedaten;

    public Mitarbeiter(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = null;
        this.telefonnummer = null;
    }
}
