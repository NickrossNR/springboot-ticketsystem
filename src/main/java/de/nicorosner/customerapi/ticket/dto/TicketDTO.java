// TicketDTO.java
package de.nicorosner.customerapi.ticket.dto;

import lombok.Data;

@Data
public class TicketDTO {
	private String status;
	private Long id;
	private String beschreibung;
	private String notiz;
	private String erstelldatum;
	private String kundeName;
	private String erstellerName;
	private Long bearbeiterId;

}
