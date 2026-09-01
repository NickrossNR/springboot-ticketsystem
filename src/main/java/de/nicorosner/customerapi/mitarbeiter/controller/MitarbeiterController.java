package de.nicorosner.customerapi.mitarbeiter.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.nicorosner.customerapi.mitarbeiter.dto.MitarbeiterDTO;
import de.nicorosner.customerapi.mitarbeiter.dto.MitarbeiterDtoOutput;
import de.nicorosner.customerapi.mitarbeiter.entity.Mitarbeiter;
import de.nicorosner.customerapi.mitarbeiter.service.MitarbeiterService;

@RestController
@RequestMapping("/api/mitarbeiter")
public class MitarbeiterController {

    private final MitarbeiterService mitarbeiterService;

    public MitarbeiterController(MitarbeiterService mitarbeiterService) {
        this.mitarbeiterService = mitarbeiterService;
    }

    @PostMapping
    public ResponseEntity<MitarbeiterDtoOutput> createMitarbeiter(@RequestBody MitarbeiterDTO dto) {  
        System.out.println("🔥🔥🔥 METHODE createMitarbeiter() WURDE AUFGERUFEN 🔥🔥🔥");
        System.out.println("➡️ Loginname: " + dto.getLoginname());
        System.out.println("➡️ Passwort: " + dto.getPasswort());
        
        MitarbeiterDtoOutput result = mitarbeiterService.saveMitarbeiter(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mitarbeiter> getMitarbeiterById(@PathVariable Long id) {
        Optional<Mitarbeiter> mitarbeiter = mitarbeiterService.getMitarbeiterById(id);
        return mitarbeiter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Mitarbeiter>> getAllMitarbeiter() {
        return ResponseEntity.ok(mitarbeiterService.getAllMitarbeiter());
    }
}
