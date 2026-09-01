package de.nicorosner.customerapi.config;

import de.nicorosner.customerapi.kunde.entity.Kunde;
import de.nicorosner.customerapi.kunde.repository.KundeRepository;
import de.nicorosner.customerapi.mitarbeiter.dto.MitarbeiterDTO;
import de.nicorosner.customerapi.mitarbeiter.entity.Mitarbeiter;
import de.nicorosner.customerapi.mitarbeiter.service.MitarbeiterService;
import de.nicorosner.customerapi.ticket.entity.Ticket;
import de.nicorosner.customerapi.ticket.repository.TicketRepository;
import de.nicorosner.customerapi.ticket.status.TicketStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class TestDataInitializer implements CommandLineRunner {

    private final MitarbeiterService mitarbeiterService;
    private final KundeRepository kundeRepository;
    private final TicketRepository ticketRepository;

    public TestDataInitializer(MitarbeiterService mitarbeiterService,
                                KundeRepository kundeRepository,
                                TicketRepository ticketRepository) {
        this.mitarbeiterService = mitarbeiterService;
        this.kundeRepository = kundeRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(String... args) {
        if (kundeRepository.count() == 0 && ticketRepository.count() == 0) {
            // 👤 Mitarbeiter anlegen über SERVICE
            MitarbeiterDTO dto = new MitarbeiterDTO();
            dto.setVorname("Max");
            dto.setNachname("Mustermann");
            dto.setEmail("max@firma.de");
            dto.setTelefonnummer("01234 56789");
            dto.setLoginname("maxmuster");
            dto.setPasswort("passwort123");

            mitarbeiterService.saveMitarbeiter(dto);
            
            var optionalMitarbeiter = mitarbeiterService.findByLoginName(dto.getLoginname());
            Mitarbeiter mitarbeiter = optionalMitarbeiter.orElseThrow(() -> new RuntimeException("Mitarbeiter nicht gefunden"));
            // Danach Kunde + Tickets
            Kunde kunde1 = new Kunde();
            kunde1.setVorname("Erika");
            kunde1.setNachname("Musterkunde");
            kunde1.setEmail("erika@musterfirma.de");
            kunde1.setTelefonnummer("0123456789");
            kunde1.setFirmenname("Musterfirma GmbH");
            kunde1.setAdresse("Musterstraße 1, 12345 Musterstadt");
            kunde1 = kundeRepository.save(kunde1);

            Ticket ticket1 = new Ticket();
            ticket1.setBeschreibung("Test Beschreibung 1");
            ticket1.setNotiz("Ticket wurde telefonisch eröffnet.");
            ticket1.setKunde(kunde1);
            ticket1.setStatus(TicketStatus.OFFEN);
            ticket1.setErstelldatum(LocalDateTime.now());
            ticket1.setErsteller(mitarbeiter);


            ticketRepository.save(ticket1);

            System.out.println("✅ Testdaten erfolgreich erstellt.");
        }
    }
}
