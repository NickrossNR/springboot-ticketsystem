package de.nicorosner.customerapi.kunde.controller;

import de.nicorosner.customerapi.kunde.entity.Kunde;
import de.nicorosner.customerapi.kunde.service.KundeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kunden")
@CrossOrigin
public class KundeController {

    private final KundeService kundeService;

    public KundeController(KundeService kundeService) {
        this.kundeService = kundeService;
    }

    @GetMapping
    public List<Kunde> getAllKunden() {
        return kundeService.findAll();
    }

    @GetMapping("/{id}")
    public Kunde getKundeById(@PathVariable Long id) {
        return kundeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Kunde nicht gefunden mit ID: " + id));
    }

    @PostMapping
    public Kunde createKunde(@RequestBody Kunde kunde) {
        return kundeService.save(kunde);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Kunde> updateKunde(@PathVariable Long id, @RequestBody Kunde neuerKunde) {
        return kundeService.findById(id)
                .map(vorhandenerKunde -> {
                    vorhandenerKunde.setVorname(neuerKunde.getVorname());
                    vorhandenerKunde.setNachname(neuerKunde.getNachname());
                    vorhandenerKunde.setFirmenname(neuerKunde.getFirmenname());
                    vorhandenerKunde.setEmail(neuerKunde.getEmail());
                    vorhandenerKunde.setTelefonnummer(neuerKunde.getTelefonnummer());
                    
                    Kunde aktualisierterKunde = kundeService.save(vorhandenerKunde);
                    return ResponseEntity.ok(aktualisierterKunde);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public void deleteKunde(@PathVariable Long id) {
        kundeService.deleteById(id);
    }
}

