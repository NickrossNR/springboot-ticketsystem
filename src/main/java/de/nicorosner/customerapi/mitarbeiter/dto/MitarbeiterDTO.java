package de.nicorosner.customerapi.mitarbeiter.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MitarbeiterDTO {
    private Long id;
    private String vorname;
    private String nachname;
    private String email;
    private String telefonnummer;
    private String passwort;
    private String loginname;
    
}
