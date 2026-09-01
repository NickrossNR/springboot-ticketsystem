package de.nicorosner.customerapi.ticket.mapper;

import de.nicorosner.customerapi.ticket.dto.TicketDTO;
import de.nicorosner.customerapi.ticket.entity.Ticket;

public class TicketMapper {

    public static TicketDTO toDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setBeschreibung(ticket.getBeschreibung());
        dto.setNotiz(ticket.getNotiz());

        // Datum
        dto.setErstelldatum(ticket.getErstelldatum() != null
                ? ticket.getErstelldatum().toString()
                : "unbekannt");

        // Kunde
        dto.setKundeName(ticket.getKunde() != null
                ? ticket.getKunde().getVorname() + " " + ticket.getKunde().getNachname()
                : "Unbekannter Kunde");

        // Ersteller
        dto.setErstellerName(ticket.getErsteller() != null
                ? ticket.getErsteller().getVorname() + " " + ticket.getErsteller().getNachname()
                : "Unbekannter Ersteller");

        // Status
        dto.setStatus(ticket.getStatus() != null
                ? ticket.getStatus().name()
                : null);
        // Bearbeiter
        dto.setBearbeiterId(ticket.getBearbeiter() != null
                ? ticket.getBearbeiter().getId()
                : null);

        return dto;
    }
}
