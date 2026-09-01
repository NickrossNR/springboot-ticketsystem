package de.nicorosner.customerapi.mitarbeiter.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MitarbeiterDtoOutput {
	
    private Long id;
    private String vorname;
    private String nachname;
    private String email;
    private String telefonnummer;

}
