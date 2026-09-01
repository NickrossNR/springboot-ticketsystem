package de.nicorosner.customerapi.anmeldung.service;

import de.nicorosner.customerapi.anmeldung.entity.Anmeldedaten;
import de.nicorosner.customerapi.anmeldung.repository.AnmeldedatenRepository;
import de.nicorosner.customerapi.passwort.PasswortService;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AnmeldedatenService {

    private final AnmeldedatenRepository anmeldedatenRepository;
    private final PasswortService passwortService;

    public AnmeldedatenService(AnmeldedatenRepository anmeldedatenRepository, PasswortService passwortService) {
        this.anmeldedatenRepository = anmeldedatenRepository;
        this.passwortService = passwortService;
    }

    public Optional<Anmeldedaten> getByLoginName(String loginName) {
        return anmeldedatenRepository.findByLoginName(loginName);
    }
    

    public Anmeldedaten saveAnmeldedaten(Anmeldedaten anmeldedaten) {
        // Passwort sicher hashen, bevor es gespeichert wird
        anmeldedaten.setLoginPasswort(passwortService.hashPasswort(anmeldedaten.getLoginPasswort()));
        return anmeldedatenRepository.save(anmeldedaten);
    }
}
