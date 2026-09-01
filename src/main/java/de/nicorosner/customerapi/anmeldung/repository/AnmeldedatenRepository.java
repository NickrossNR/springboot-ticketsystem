package de.nicorosner.customerapi.anmeldung.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import de.nicorosner.customerapi.anmeldung.entity.Anmeldedaten;

public interface AnmeldedatenRepository extends JpaRepository<Anmeldedaten, Long> {
    Optional<Anmeldedaten> findByLoginName(String loginName); // Falls nach Login-Namen suchen 
}