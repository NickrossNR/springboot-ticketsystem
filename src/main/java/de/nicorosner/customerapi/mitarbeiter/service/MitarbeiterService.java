package de.nicorosner.customerapi.mitarbeiter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import de.nicorosner.customerapi.anmeldung.entity.Anmeldedaten;
import de.nicorosner.customerapi.anmeldung.repository.AnmeldedatenRepository;
import de.nicorosner.customerapi.mitarbeiter.dto.MitarbeiterDTO;
import de.nicorosner.customerapi.mitarbeiter.dto.MitarbeiterDtoOutput;
import de.nicorosner.customerapi.mitarbeiter.entity.Mitarbeiter;
import de.nicorosner.customerapi.mitarbeiter.repository.MitarbeiterRepository;
import de.nicorosner.customerapi.passwort.PasswortService;
import jakarta.transaction.Transactional;

@Service
public class MitarbeiterService {

    private final MitarbeiterRepository mitarbeiterRepository;
    private final AnmeldedatenRepository anmeldedatenRepository;
    private final PasswortService passwortService;

    public MitarbeiterService(MitarbeiterRepository mitarbeiterRepository, PasswortService passwortService, AnmeldedatenRepository anmeldedatenRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
        this.passwortService = passwortService;
        this.anmeldedatenRepository = anmeldedatenRepository;
    }
    @Transactional
    public MitarbeiterDtoOutput saveMitarbeiter(MitarbeiterDTO dto) {
        System.out.println("🛑 saveMitarbeiter() wird aufgerufen 🛑");

        // Mitarbeiter erstellen
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname(dto.getVorname());
        mitarbeiter.setNachname(dto.getNachname());
        mitarbeiter.setTelefonnummer(dto.getTelefonnummer());
        mitarbeiter.setEmail(dto.getEmail());

        // Anmeldedaten erstellen und miteinander verknüpfen
        Anmeldedaten anmeldedaten = new Anmeldedaten();
        anmeldedaten.setLoginName(dto.getLoginname());
        anmeldedaten.setLoginPasswort(passwortService.hashPasswort(dto.getPasswort()));
        anmeldedaten.setMitarbeiter(mitarbeiter);
        mitarbeiter.setAnmeldedaten(anmeldedaten);

        // Anmeldedaten speichern, dabei wird durch Cascade auch der Mitarbeiter gespeichert
        anmeldedatenRepository.save(anmeldedaten);

        // Ausgabe-Objekt bauen
        MitarbeiterDtoOutput retur = new MitarbeiterDtoOutput();
        retur.setEmail(mitarbeiter.getEmail());
        retur.setNachname(mitarbeiter.getNachname());
        retur.setTelefonnummer(mitarbeiter.getTelefonnummer());
        retur.setVorname(mitarbeiter.getVorname());

        return retur;
    }



    public Optional<Mitarbeiter> getMitarbeiterById(Long id) {
        return mitarbeiterRepository.findById(id);
    }

    public List<Mitarbeiter> getAllMitarbeiter() {
        return mitarbeiterRepository.findAll();
    }

    public Optional<Mitarbeiter> findByLoginName(String loginName) {
        return mitarbeiterRepository.findByAnmeldedaten_LoginName(loginName);
    }
}
