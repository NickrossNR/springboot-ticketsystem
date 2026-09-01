package de.nicorosner.customerapi.anmeldung.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import de.nicorosner.customerapi.mitarbeiter.entity.Mitarbeiter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anmeldedaten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatische ID-Vergabe
    private Long id;

    @OneToOne(mappedBy = "anmeldedaten", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JsonBackReference("mitarbeiter-anmeldedaten")
    private Mitarbeiter mitarbeiter;

    @Column(unique = true, nullable = false)
    private String loginName;
    
    @JsonIgnore 
    @Column(nullable = false)
    private String loginPasswort;
}
