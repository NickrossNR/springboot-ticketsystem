package de.nicorosner.customerapi.kunde.dto;

import lombok.Data;

@Data
public class KundeDTO {
    private Long id;
    private String vorname;
    private String nachname;
    private String firmenname;
}
