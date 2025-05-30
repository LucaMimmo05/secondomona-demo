# SecondoMona Demo

## Indice
1. [Panoramica](#panoramica)
2. [Architettura](#architettura)
3. [Funzionalità Principali](#funzionalità-principali)
4. [API REST](#api-rest)
5. [Modelli di Dati](#modelli-di-dati)
6. [Autenticazione e Autorizzazione](#autenticazione-e-autorizzazione)
7. [Gestione dei Badge](#gestione-dei-badge)
8. [Gestione Visitatori](#gestione-visitatori)
9. [Gestione Visite](#gestione-visite)
10. [Configurazione e Avvio](#configurazione-e-avvio)

## Panoramica

SecondoMona Demo è un'applicazione per la gestione di visitatori, badge e controllo accessi. Il sistema permette di:

- Gestire i dipendenti e i visitatori
- Assegnare e terminare l'uso di badge
- Registrare e monitorare le visite
- Gestire le timbrature
- Generare report e documentazione

L'applicazione è sviluppata utilizzando Quarkus, un framework Java progettato per applicazioni cloud-native, e sfrutta tecnologie moderne come:

- RESTEasy per le API REST
- Hibernate ORM con Panache per la persistenza
- Qute per i template HTML
- JWT per l'autenticazione

## Architettura

Il progetto segue un'architettura a strati:

```
┌───────────────────┐
│     Web Layer     │  <- Risorse REST, Controller
├───────────────────┤
│   Service Layer   │  <- Logica di business
├───────────────────┤
│ Persistence Layer │  <- Repository, Entità JPA
└───────────────────┘
```

### Web Layer
Contiene le risorse REST che espongono le API. Implementate nelle classi:
- `PersonaResource`: Gestione delle persone (dipendenti e visitatori)
- `BadgeResource`: Gestione dei badge e delle assegnazioni
- `VisitaResource`: Gestione delle visite
- `AuthenticationResource`: Autenticazione e gestione token
- `SicurezzaResource`: Funzionalità legate alla sicurezza

### Service Layer
Contiene la logica di business. Implementato nelle classi:
- `PersonaService`: Creazione e gestione delle persone
- `AssegnazioneBadgeService`: Assegnazione e terminazione badge
- `VisitaService`: Creazione e gestione delle visite
- `PdfService`: Generazione di documenti PDF
- `SicurezzaService`: Gestione della sicurezza
- `TimbraturaDipendenteService`: Gestione delle timbrature

### Persistence Layer
Gestisce l'accesso ai dati. Implementato tramite:
- Repository Panache: `PersonaRepository`, `AssegnazioneBadgeRepository`, `TesseraRepository`, ecc.
- Modelli JPA: `Persona`, `Tessera`, `AssegnazioneBadge`, `RichiestaVisita`, ecc.

## Funzionalità Principali

### Gestione Persone
- Registrazione di dipendenti e visitatori
- Gestione delle informazioni personali
- Autenticazione e controllo dei ruoli

### Gestione Badge
- Creazione automatica tessere al momento della registrazione di un visitatore
- Assegnazione e terminazione di badge
- Monitoraggio dello stato dei badge (attivi/terminati)

### Gestione Visite
- Registrazione di richieste di visita
- Monitoraggio delle visite attive
- Conclusione delle visite

### Timbrature
- Registrazione di timbrature in entrata e uscita
- Report sulle presenze

## API REST

Il sistema espone diverse API REST per interagire con le sue funzionalità.

### API Persone
- `GET /api/dipendenti`: Recupera tutti i dipendenti
- `POST /api/dipendenti`: Crea un nuovo dipendente
- `POST /api/visitatori`: Crea un nuovo visitatore
- `GET /api/visitatori`: Recupera tutti i visitatori

### API Badge
- `GET /api/badge`: Recupera tutte le assegnazioni di badge
- `GET /api/badge/{id}`: Recupera un'assegnazione specifica
- `POST /api/badge/assegna`: Assegna un badge a una persona
- `PUT /api/badge/termina/{id}`: Termina un'assegnazione di badge
- `GET /api/badge/persona/{idPersona}`: Recupera le assegnazioni per una persona

### API Visite
- `GET /api/visite`: Recupera tutte le richieste di visita
- `GET /api/visite/attive`: Recupera le visite attive
- `GET /api/visite/in-attesa`: Recupera le visite in attesa
- `POST /api/visite`: Crea una nuova richiesta di visita
- `PUT /api/visite/{idRichiesta}/conclusione`: Conclude una visita
- `PUT /api/visite/{idRichiesta}/concludi-visita`: Conclude una visita e termina il badge associato

## Modelli di Dati

### Persona
Rappresenta sia i dipendenti che i visitatori. Attributi principali:
- `idPersona`: Identificatore univoco
- `nome`, `cognome`: Anagrafica
- `mail`: Email per contatti e autenticazione
- `ruolo`: Ruolo della persona (Dipendente, Visitatore, Admin, Portineria)
- `numeroDocumento`: Numero del documento di identità
- Altre informazioni personali e di contatto

### Tessera
Rappresenta un badge fisico. Attributi principali:
- `idTessera`: Identificatore univoco
- `codiceTessera`: Codice univoco della tessera (formato "tess-AAAAMMGG-HHMMSS" per i visitatori)
- `persona`: Riferimento alla persona associata
- `categorie`: Categoria della tessera (es. "Visitatore", "Dipendente")
- `abilitata`, `attivata`: Stato della tessera
- `dataInizio`, `dataFine`: Periodo di validità

### AssegnazioneBadge
Rappresenta l'assegnazione di un badge a una persona. Attributi principali:
- `idAssegnazione`: Identificatore univoco
- `persona`: Persona a cui è assegnato il badge
- `tessera`: Badge assegnato
- `dataInizio`: Data e ora di inizio dell'assegnazione
- `dataFine`: Data e ora di fine dell'assegnazione (null se ancora attiva)

### RichiestaVisita
Rappresenta una richiesta di visita. Attributi principali:
- `idRichiesta`: Identificatore univoco
- `visitatore`: Persona che effettua la visita
- `dataInizio`, `dataFine`: Periodo della visita
- `motivo`: Motivazione della visita
- `stato`: Stato della richiesta (in attesa, approvata, conclusa)

## Autenticazione e Autorizzazione

Il sistema utilizza JWT (JSON Web Token) per l'autenticazione e l'autorizzazione. I ruoli principali sono:

- `Admin`: Accesso completo a tutte le funzionalità
- `Portineria`: Gestione visitatori, badge e visite
- `Dipendente`: Accesso limitato alle proprie informazioni
- `Visitatore`: Nessun Accesso alle API, solo visualizzazione tramite frontend

Gli endpoint API sono protetti con annotazioni `@RolesAllowed` che specificano quali ruoli possono accedere a ciascuna funzionalità.

## Gestione dei Badge

### Creazione automatica tessere per i visitatori
Quando viene creato un nuovo visitatore, il sistema crea automaticamente una nuova tessera con un codice nel formato "tess-AAAAMMGG-HHMMSS".

Il codice responsabile di questa funzionalità si trova nel metodo `createVisitatore` del `PersonaService`:

```java
// Generiamo il codice nel formato tess-datacreazione (es. tess-20250530-123045)
String dataOraFormattata = LocalDateTime.now()
    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
String codiceTessera = "tess-" + dataOraFormattata;
```

### Assegnazione badge
L'assegnazione di un badge avviene tramite l'API `POST /api/badge/assegna` che crea un record `AssegnazioneBadge` associando una tessera disponibile a una persona.

### Terminazione badge
Quando una visita termina, il badge può essere disattivato tramite l'API `PUT /api/badge/termina/{id}` o automaticamente tramite `PUT /api/visite/{idRichiesta}/concludi-visita`.

## Gestione Visitatori

### Creazione visitatore
La creazione di un visitatore avviene tramite l'API `POST /api/persone/visitatori`. Al momento della creazione:

1. Viene verificato che non esista già una persona con lo stesso numero di documento
2. Viene creato un nuovo record `Persona` con ruolo "Visitatore"
3. Viene generata automaticamente una nuova tessera associata al visitatore

### Monitoraggio visitatori
Le API permettono di monitorare:
- Tutti i visitatori registrati
- Visitatori attualmente presenti
- Storico delle visite di un visitatore specifico

## Gestione Visite

### Creazione di una visita
Le visite vengono create tramite l'API `POST /api/visite`. L'endpoint accetta un oggetto `RichiestaVisitaDTO` che contiene:
- Dati del visitatore
- Data e ora di inizio e fine previste
- Motivo della visita
- Informazioni aggiuntive

### Conclusione di una visita
Le visite possono essere concluse tramite:
- `PUT /api/visite/{idRichiesta}/conclusione`: Conclude solo la visita
- `PUT /api/visite/{idRichiesta}/concludi-visita`: Conclude la visita e termina anche l'eventuale badge associato

## Configurazione e Avvio

### Prerequisiti
- JDK 11+
- Maven 3.8+
- Database (configurato in application.properties)

### Avvio in modalità sviluppo
```bash
./mvnw compile quarkus:dev
```

### Compilazione e packaging
```bash
./mvnw clean package
```

### Esecuzione del jar prodotto
```bash
java -jar target/secondomona-demo-dev.jar
```

