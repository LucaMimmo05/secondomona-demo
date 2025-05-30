# SecondoMona Demo

Questo progetto è un'applicazione Java basata su Quarkus per la gestione di visitatori, badge e timbrature.

## Requisiti

- JDK 11 o superiore
- Maven 3.8 o superiore
- Microsoft SQL Server
- Git

## Configurazione del Database

L'applicazione utilizza Microsoft SQL Server come database. È necessario configurare le seguenti variabili d'ambiente prima di avviare l'applicazione:

- `DB_USERNAME`: nome utente per l'accesso al database
- `DB_PASSWORD`: password per l'accesso al database
- `DB_URL`: URL di connessione JDBC, ad esempio `jdbc:sqlserver://localhost:1433;databaseName=secondomona;encrypt=false`


## Avvio dell'applicazione

### Modalità sviluppo

Per avviare l'applicazione in modalità sviluppo con hot reload:

```shell
# Impostare le variabili d'ambiente (Windows)
set DB_USERNAME=utente_database
set DB_PASSWORD=password_database
set DB_URL=jdbc:sqlserver://localhost:1433;databaseName=secondomona;encrypt=false

# Impostare le variabili d'ambiente (Linux/Mac)
export DB_USERNAME=utente_database
export DB_PASSWORD=password_database
export DB_URL=jdbc:sqlserver://localhost:1433;databaseName=secondomona;encrypt=false

# Avvio in modalità sviluppo
./mvnw quarkus:dev
```

L'applicazione sarà disponibile all'indirizzo: http://localhost:8080
L'interfaccia di sviluppo Quarkus Dev UI è disponibile all'indirizzo: http://localhost:8080/q/dev/

### Packaging e distribuzione

Per creare un pacchetto eseguibile:

```shell
./mvnw package
```

Questo comando genera il file `quarkus-run.jar` nella directory `target/quarkus-app/`.
Per eseguire l'applicazione pacchettizzata:

```shell
# Impostare le variabili d'ambiente necessarie
java -jar target/quarkus-app/quarkus-run.jar
```

Per creare un file JAR autonomo (über-jar):

```shell
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

Il file JAR risultante sarà eseguibile con:

```shell
java -jar target/*-runner.jar
```

## Configurazione CORS

L'applicazione è configurata per consentire richieste CORS da `http://localhost:5173`, che è l'URL predefinito del frontend. Se il frontend è in esecuzione su un URL diverso, modificare la proprietà `quarkus.http.cors.origins` nel file `application.properties`.

## API e Documentazione

Dopo aver avviato l'applicazione, è possibile accedere alla documentazione Swagger delle API all'indirizzo:
http://localhost:8080/q/swagger-ui/

### Elenco degli Endpoint

#### Autenticazione
- `POST /api/auth/login` - Effettua il login e ottiene i token di autenticazione
- `POST /api/auth/refresh` - Rinnova il token di accesso utilizzando un refresh token

#### Gestione Dipendenti e Visitatori
- `POST /api/dipendenti` - Registra un nuovo dipendente
- `GET /api/dipendenti` - Ottiene tutti i dipendenti
- `GET /api/dipendenti/{id}` - Ottiene un dipendente specifico
- `GET /api/visitatori` - Ottiene tutti i visitatori
- `POST /api/visitatori` - Crea un nuovo visitatore

#### Gestione Badge
- `GET /api/badge` - Ottiene tutte le assegnazioni di badge
- `GET /api/badge/{id}` - Ottiene un'assegnazione specifica
- `POST /api/badge/assegna` - Assegna un badge a una persona
- `PUT /api/badge/termina/{id}` - Termina un'assegnazione di badge
- `GET /api/badge/persona/{idPersona}` - Ottiene le assegnazioni di badge di una persona

#### Gestione Timbrature
- `GET /api/timbrature` - Ottiene tutte le timbrature
- `GET /api/timbrature/{id}` - Ottiene una timbratura specifica
- `POST /api/timbrature` - Registra una nuova timbratura
- `PUT /api/timbrature/{id}/valida` - Valida una timbratura (richiede il parametro `validatorId`)
- `GET /api/timbrature/persona/{idPersona}` - Ottiene le timbrature di una persona
- `GET /api/timbrature/data/{data}` - Ottiene le timbrature di una data specifica (formato YYYY-MM-DD)
- `GET /api/timbrature/non-validate` - Ottiene le timbrature non ancora validate
- `DELETE /api/timbrature/{id}` - Elimina una timbratura
- `GET /api/timbrature/oggi/{idPersona}` - Ottiene le timbrature odierne di una persona

#### Gestione Visite
- `GET /api/visite` - Ottiene tutte le richieste di visita
- `POST /api/visite` - Crea una nuova richiesta di visita
- `GET /api/visite/attive` - Ottiene le visite attualmente attive
- `GET /api/visite/in-attesa` - Ottiene le visite non ancora iniziate di quel giorno

## Risoluzione dei problemi

### Errori di connessione al database
- Verificare che il server SQL Server sia in esecuzione e accessibile
- Controllare che le credenziali nelle variabili d'ambiente siano corrette
- Verificare che il database esista o abbia i permessi per essere creato automaticamente

### Errori di avvio dell'applicazione
- Verificare i log di avvio per identificare eventuali errori
- Assicurarsi che tutte le dipendenze siano state scaricate correttamente

## Struttura del progetto

- `src/main/java/com/secondomona/dto`: Data Transfer Objects
- `src/main/java/com/secondomona/persistence`: Repository e modelli del database
- `src/main/java/com/secondomona/service`: Logica dei servizi
- `src/main/java/com/secondomona/web`: Controller REST e gestione delle eccezioni
- `src/main/resources`: File di configurazione e script SQL

## Tecnologie utilizzate

- Quarkus: framework Java
- Hibernate ORM con Panache: ORM per la persistenza dei dati
- REST con Jackson: API RESTful con serializzazione JSON
- SmallRye JWT: autenticazione e autorizzazione con JWT
- Microsoft SQL Server: database relazionale
