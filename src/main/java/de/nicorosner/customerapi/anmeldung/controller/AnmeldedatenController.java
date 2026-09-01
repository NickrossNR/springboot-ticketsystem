package de.nicorosner.customerapi.anmeldung.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.nicorosner.customerapi.anmeldung.entity.Anmeldedaten;
import de.nicorosner.customerapi.anmeldung.service.AnmeldedatenService;

@RestController
@RequestMapping("/api/anmeldedaten")
public class AnmeldedatenController {

    private final AnmeldedatenService anmeldedatenService;

    public AnmeldedatenController(AnmeldedatenService anmeldedatenService) {
        this.anmeldedatenService = anmeldedatenService;
    }

    @GetMapping("/{loginName}")
    public ResponseEntity<Anmeldedaten> getByLoginName(@PathVariable String loginName) {
        Optional<Anmeldedaten> anmeldedaten = anmeldedatenService.getByLoginName(loginName);
        return anmeldedaten.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Anmeldedaten> createAnmeldedaten(@RequestBody Anmeldedaten anmeldedaten) {
        return ResponseEntity.ok(anmeldedatenService.saveAnmeldedaten(anmeldedaten));
    }
}