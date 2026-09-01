package de.nicorosner.customerapi.kunde.repository;

import de.nicorosner.customerapi.kunde.entity.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundeRepository extends JpaRepository<Kunde, Long> {
    // Optional: findByEmail(), findByFirmenname(), ...
}
