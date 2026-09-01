package de.nicorosner.customerapi.ticket.controller;

import de.nicorosner.customerapi.ticket.dto.TicketDTO;
import de.nicorosner.customerapi.ticket.entity.Ticket;
import de.nicorosner.customerapi.ticket.mapper.TicketMapper;
import de.nicorosner.customerapi.ticket.service.TicketService;
import de.nicorosner.customerapi.ticket.status.TicketStatus;
import de.nicorosner.customerapi.auth.CustomPrincipal;
import de.nicorosner.customerapi.mitarbeiter.entity.Mitarbeiter;
import de.nicorosner.customerapi.mitarbeiter.service.MitarbeiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // erlaubt Get-, Post-, Put-,
@RequestMapping("/api/tickets")
@CrossOrigin // erlaubt Frontend-Zugriff
public class TicketController {

	private final TicketService ticketService;
	private final MitarbeiterService mitarbeiterService;

	public TicketController(TicketService ticketService, MitarbeiterService mitarbeiterService) {
		this.ticketService = ticketService;
		this.mitarbeiterService = mitarbeiterService;
	}

	// 🔍 Optionaler Test-Endpunkt
	@GetMapping("/debug")
	public ResponseEntity<Ticket> debugTicket() {
		return ticketService.findById(1L).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// 📋 Alle Tickets als DTOs
	@GetMapping
	public ResponseEntity<List<TicketDTO>> getAllTickets() {
		List<Ticket> tickets = ticketService.findAll();

		List<TicketDTO> dtos = tickets.stream().map(TicketMapper::toDTO).toList();

		return ResponseEntity.ok(dtos);
	}

	// Einzelnes Ticket abrufen
	@GetMapping("/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
		return ticketService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Neues Ticket speichern
	@PostMapping
	public TicketDTO createTicket(@RequestBody Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
		Long id = principal.getId();
		Mitarbeiter ersteller = mitarbeiterService.getMitarbeiterById(id)
				.orElseThrow(() -> new RuntimeException("Mitarbeiter nich gefunden: " + id));

		ticket.setErsteller(ersteller);
		ticket.setStatus(TicketStatus.OFFEN);

		return TicketMapper.toDTO(ticketService.save(ticket));
	}

	// Ticket löschen
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
		ticketService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	// Ticket bearbeiten
	@PutMapping("/{id}")
	public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody TicketDTO dto) {
		return ticketService.findById(id).map(existing -> {
			existing.setBeschreibung(dto.getBeschreibung());
			existing.setNotiz(dto.getNotiz());
			if (dto.getBearbeiterId() != null) {
				mitarbeiterService.getMitarbeiterById(dto.getBearbeiterId()).ifPresent(existing::setBearbeiter);
			}
			if (dto.getStatus() != null) {
				existing.setStatus(TicketStatus.valueOf(dto.getStatus().toUpperCase()));
			}
			Ticket updated = ticketService.save(existing);
			return ResponseEntity.ok(TicketMapper.toDTO(updated));
		}).orElse(ResponseEntity.notFound().build());
	}

}
