package de.nicorosner.customerapi.mitarbeiter.repository;

import de.nicorosner.customerapi.mitarbeiter.entity.Mitarbeiter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long> {
	Optional<Mitarbeiter> findByAnmeldedaten_LoginName(String loginName);
}
