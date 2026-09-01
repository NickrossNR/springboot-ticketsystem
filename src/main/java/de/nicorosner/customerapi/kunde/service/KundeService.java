package de.nicorosner.customerapi.kunde.service;

import de.nicorosner.customerapi.kunde.entity.Kunde;
import de.nicorosner.customerapi.kunde.repository.KundeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KundeService {

    private final KundeRepository kundeRepository;

    public KundeService(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    public List<Kunde> findAll() {
        return kundeRepository.findAll();
    }

    public Optional<Kunde> findById(Long id) {
        return kundeRepository.findById(id);
    }

    public Kunde save(Kunde kunde) {
        return kundeRepository.save(kunde);
    }

    public void deleteById(Long id) {
        kundeRepository.deleteById(id);
    }
}
