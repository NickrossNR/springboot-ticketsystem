package de.nicorosner.customerapi.passwort;

//Erstelle eine Utility-Klasse, die Passwörter sicher verschlüsselt und verifiziert:


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswortService {

    private final PasswordEncoder passwordEncoder;

    public PasswortService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String hashPasswort(String passwort) {
        return passwordEncoder.encode(passwort);
    }

    public boolean verifizierePasswort(String passwort, String hashedPasswort) {
        return passwordEncoder.matches(passwort, hashedPasswort);
    }
}