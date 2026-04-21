# Agenda Scolastica - Progetto Java 4BI

Studente: Rotar Elisey
Classe: 4BI - Anno Scolastico 2025/2026

---

## Descrizione

Applicazione Java a riga di comando che simula un'agenda scolastica digitale.
L'utente può aggiungere compiti di tre tipi diversi (verifiche, interrogazioni, consegne),
gestirli tramite un menu interattivo e visualizzare statistiche sui dati inseriti.

---

## Compilazione ed Esecuzione

Compilare tutti i file:

```
javac modello/*.java gestione/*.java Main.java
```

Eseguire il programma:

```
java Main
```

---

## Struttura del Progetto

```
agenda-scolastica/
├── modello/
│   ├── Compito.java        (classe astratta)
│   ├── Verifica.java
│   ├── Interrogazione.java
│   ├── Consegna.java
│   └── Materia.java        (enum)
├── gestione/
│   └── Agenda.java
├── Main.java
└── UML.txt
```

---

## Classi Principali

- Compito: classe astratta base con id, descrizione, data, materia, stato completato.
  Dichiara i metodi astratti getTipo(), getVoto() e setVoto() che ogni sottoclasse implementa.

- Verifica: estende Compito, aggiunge argomenti e voto numerico.

- Interrogazione: estende Compito, aggiunge argomento e voto numerico.

- Consegna: estende Compito, aggiunge dettagli. getVoto() restituisce null, setVoto() non fa nulla.

- Materia: enum con le materie scolastiche (ITALIANO, MATEMATICA, INGLESE, ecc.)

- Agenda: gestisce la collezione di compiti con tutti i metodi CRUD, ricerche, statistiche e ordinamento.

- Main: punto di ingresso, menu interattivo e gestione input utente.

---

## Collezioni Utilizzate

ArrayList<Compito> in Agenda.java.
Scelta motivata dalla semplicità d'uso, accesso per indice e supporto completo
alle operazioni di inserimento, ricerca, modifica, rimozione e stampa.

---

## Operazioni Principali

- Inserimento compiti di tre tipi diversi
- Ricerca per ID e per materia
- Modifica descrizione e inserimento voto
- Rimozione compito
- Filtro compiti in scadenza entro N giorni
- Ordinamento per data (bubble sort)
- Statistiche: totale, completati, da fare, media voti, conteggio per materia

---

## Gestione delle Eccezioni

Usati try/catch/throw in più punti del codice:
- NumberFormatException per input numerici non validi
- IllegalArgumentException lanciata con throw quando la descrizione è vuota,
  la data o materia sono nulle, il voto è fuori dall'intervallo 0-10,
  o i giorni passati a mostraCompitiImminenti sono negativi
- Exception generica nel metodo aggiungiCompito per errori di data

---

## Difficoltà e Soluzioni

La gestione del voto per Verifica e Interrogazione richiedeva inizialmente instanceof
per distinguere i tipi a runtime. La soluzione adottata è stata dichiarare getVoto()
e setVoto() come metodi astratti in Compito, permettendo di usare il polimorfismo
in modo corretto senza cast. Consegna implementa i metodi restituendo null e ignorando
il set, dato che non prevede un voto.

---

## Diagramma UML

Il diagramma UML delle classi è disponibile nel file UML.txt.
