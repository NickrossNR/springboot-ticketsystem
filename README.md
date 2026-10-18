# Ticketsystem – Spring Boot + MySQL

Ein simples Ticketsystem mit Spring Boot, Spring Security, JWT-Authentifizierung und MySQL. Das Projekt umfasst ein Backend für Ticketverwaltung sowie ein kleines Frontend in HTML/JavaScript.

## Überblick

Das System ermöglicht:

- Benutzeranmeldung mit Login und Passwort
- JWT-basierte Authentifizierung
- Verwaltung von Tickets
- Zuweisung von Tickets an Mitarbeiter
- Statusverwaltung (
  offen, in Bearbeitung, gelöst usw. je nach Implementierung)
- Testdaten für lokale Entwicklung
- Zugriff über eine einfache Weboberfläche im Frontend

## Tech Stack

- Java 17
- Spring Boot 3.4.3
- Maven
- Spring Data JPA
- Spring Security
- MySQL
- JWT (jjwt)
- HTML / JavaScript / CSS

## Projektstruktur

```text
springboot-ticketsystem/
├── frontend/
│   ├── index.html
│   ├── mainSystem.html
│   └── Firmenlogo.png
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── de/nicorosner/customerapi/
│   │   │       ├── anmeldung/
│   │   │       ├── auth/
│   │   │       ├── config/
│   │   │       ├── kunde/
│   │   │       ├── mitarbeiter/
│   │   │       ├── passwort/
│   │   │       ├── ticket/
│   │   │       └── CustomerapiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Voraussetzungen

Vor dem Start benötigst du:

- Java 17+
- Maven
- MySQL
- Git

## Lokale Einrichtung

### 1. MySQL vorbereiten

Stelle eine lokale MySQL-Instanz bereit und erstelle die Datenbank `customerapi_tutorial` (falls sie noch nicht existiert).

Die Standardkonfiguration im Projekt ist:

```properties
spring.datasource.url=jdbc:mysql://localhost:3308/customerapi_tutorial?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=p@ssw0rd
```

Wenn deine MySQL-Instanz anders konfiguriert ist, passe dies in `src/main/resources/application.properties` an.

### 2. Projekt klonen

```bash
git clone https://github.com/NickrossNR/springboot-ticketsystem.git
cd springboot-ticketsystem
```

### 3. Backend starten

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Bei erfolgreichem Start läuft die API auf:

```text
http://localhost:9090
```

### 4. Frontend öffnen

Öffne im Browser:

```text
frontend/index.html
```

Das Login-Formular sendet Anfragen an den Backend-Endpunkt:

```text
http://localhost:9090/api/auth/login
```

## Standardzugang

Das Projekt initialisiert bei Start automatisch Testdaten. Der Login kann je nach vorhandenen Testdaten funktionieren.

Ein Beispiel für einen Test-Mitarbeiter wird in `TestDataInitializer` angelegt.

## Wichtige Endpunkte

### Authentifizierung

```http
POST /api/auth/login
```

Request:

```json
{
  "loginName": "maxmuster",
  "loginPasswort": "passwort123"
}
```

### Tickets

```http
GET    /api/tickets
GET    /api/tickets/{id}
POST   /api/tickets
PUT    /api/tickets/{id}
DELETE /api/tickets/{id}
```

## Sicherheitskonzept

Das Projekt nutzt Spring Security in Kombination mit JWT:

- Login liefert einen Token
- Token wird im Frontend lokal gespeichert
- Der Server prüft den Token über einen Filter (`JwtFilter`)
- Zugriff auf geschützte Endpunkte wird dadurch kontrolliert

## Testdaten

Das Projekt enthält einen Initializer (`TestDataInitializer`), der beim Start automatisch Beispiel-Daten erzeugt, sofern noch keine vorhanden sind.

Das dient vor allem der lokalen Entwicklung und Demo-Zwecken.

## Hinweise

- Das Projekt ist als lokales Entwicklungsprojekt konzipiert.
- Datenbankzugang und Passwörter sind derzeit direkt im `application.properties` hinterlegt.
- Für Produktion würde man eine sichere Konfiguration mit Umgebungsvariablen oder Secret-Management empfehlen.

## Lizenz

Diese Datei beschreibt das Projekt; die tatsächliche Lizenz kann je nach Bedarf ergänzt werden.

## Nächste mögliche Erweiterungen

- Benutzerrollen sauber modellieren
- Admin-Panel
- Ticket-Kommentare / Historie
- E-Mail-Benachrichtigungen
- Docker-Setup für MySQL + Backend
- CI/CD-Konfiguration
- Production-ready Security und Secrets-Management

## Autor

NickrossNR

## Status

Projektstatus: Entwicklungs-/Demo-Projekt mit funktionaler Backend- und Frontend-Struktur.

## Ausschnitte
<img width="1906" height="912" alt="Unbenannt" src="https://github.com/user-attachments/assets/f6a147a6-4d5d-410a-af9e-a3942be00e6f" />

<img width="1906" height="912" alt="image" src="https://github.com/user-attachments/assets/359dd43c-55c3-4334-90a1-007878338a2d" />

