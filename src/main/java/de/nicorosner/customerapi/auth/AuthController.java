package de.nicorosner.customerapi.auth;

import de.nicorosner.customerapi.anmeldung.entity.Anmeldedaten;
import de.nicorosner.customerapi.anmeldung.service.AnmeldedatenService;
import de.nicorosner.customerapi.passwort.PasswortService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AnmeldedatenService anmeldedatenService;
    private final PasswortService passwortService;
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes());


    public AuthController(AnmeldedatenService anmeldedatenService, PasswortService passwortService) {
        this.anmeldedatenService = anmeldedatenService;
        this.passwortService = passwortService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        Optional<Anmeldedaten> anmeldedatenOpt = anmeldedatenService.getByLoginName(request.getLoginName());

        if (anmeldedatenOpt.isPresent()) {         	 	
            Anmeldedaten anmeldedaten = anmeldedatenOpt.get();
            if (passwortService.verifizierePasswort(request.getLoginPasswort(), anmeldedaten.getLoginPasswort())) {
                String token = generateToken(anmeldedatenOpt.get().getId());
                return ResponseEntity.ok(new LoginResponse(token));
            }
        }
        return ResponseEntity.status(401).body("Ungültige Anmeldedaten");
    }

    private String generateToken(Long Id) { 
        return Jwts.builder()
                .claim("id", Id)
                .claim("roles", List.of("ROLE_ADMIN")) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 Stunde gültig
                .signWith(SECRET_KEY)
                .compact();
    }
}