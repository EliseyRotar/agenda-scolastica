import gestione.Agenda;
import modello.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Agenda agenda = new Agenda();

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("   AGENDA SCOLASTICA");
        System.out.println("=================================");

        boolean continua = true;
        while (continua) {
            mostraMenu();
            try {
                int scelta = Integer.parseInt(scanner.nextLine());
                continua = gestisciScelta(scelta);
            } catch (NumberFormatException e) {
                System.out.println("Errore: devi inserire un numero!");
            }
        }

        System.out.println("Arrivederci!");
        scanner.close();
    }

    static void mostraMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Aggiungi compito");
        System.out.println("2. Visualizza tutti i compiti");
        System.out.println("3. Cerca per ID");
        System.out.println("4. Cerca per materia");
        System.out.println("5. Segna come completato");
        System.out.println("6. Rimuovi compito");
        System.out.println("7. Compiti in scadenza");
        System.out.println("8. Statistiche");
        System.out.println("9. Inserisci voto");
        System.out.println("10. Modifica descrizione");
        System.out.println("11. Ordina per data");
        System.out.println("0. Esci");
        System.out.print("Scelta: ");
    }

    static boolean gestisciScelta(int scelta) {
        System.out.println();
        switch (scelta) {
            case 1:
                aggiungiCompito();
                break;
            case 2:
                agenda.mostraTuttiCompiti();
                break;
            case 3:
                cercaPerId();
                break;
            case 4:
                cercaPerMateria();
                break;
            case 5:
                segnaCompletato();
                break;
            case 6:
                rimuoviCompito();
                break;
            case 7:
                mostraImminenti();
                break;
            case 8:
                mostraStatistiche();
                break;
            case 9:
                inserisciVoto();
                break;
            case 10:
                modificaDescrizione();
                break;
            case 11:
                agenda.ordinaPerData();
                System.out.println("Compiti ordinati per data.");
                break;
            case 0:
                return false;
            default:
                System.out.println("Opzione non valida.");
        }
        return true;
    }

    static void aggiungiCompito() {
        try {
            System.out.println("Che tipo di compito?");
            System.out.println("1. Verifica");
            System.out.println("2. Interrogazione");
            System.out.println("3. Consegna");
            System.out.print("Scelta: ");
            int tipo = Integer.parseInt(scanner.nextLine());

            System.out.print("Descrizione: ");
            String descrizione = scanner.nextLine();

            System.out.print("Data (gg/mm/aaaa): ");
            String dataStr = scanner.nextLine();
            String[] parti = dataStr.split("/");
            if (parti.length != 3) {
                System.out.println("Formato data sbagliato.");
                return;
            }
            int giorno = Integer.parseInt(parti[0]);
            int mese = Integer.parseInt(parti[1]);
            int anno = Integer.parseInt(parti[2]);
            LocalDate data = LocalDate.of(anno, mese, giorno);

            System.out.println("Materie:");
            Materia[] materie = Materia.values();
            for (int i = 0; i < materie.length; i++) {
                System.out.println((i + 1) + ". " + materie[i]);
            }
            System.out.print("Scegli: ");
            int sceltaMateria = Integer.parseInt(scanner.nextLine()) - 1;
            if (sceltaMateria < 0 || sceltaMateria >= materie.length) {
                System.out.println("Materia non valida.");
                return;
            }
            Materia materia = materie[sceltaMateria];

            Compito compito = null;
            if (tipo == 1) {
                System.out.print("Argomenti della verifica: ");
                String argomenti = scanner.nextLine();
                compito = new Verifica(descrizione, data, materia, argomenti);
            } else if (tipo == 2) {
                System.out.print("Argomento interrogazione: ");
                String argomento = scanner.nextLine();
                compito = new Interrogazione(descrizione, data, materia, argomento);
            } else if (tipo == 3) {
                System.out.print("Dettagli: ");
                String dettagli = scanner.nextLine();
                compito = new Consegna(descrizione, data, materia, dettagli);
            } else {
                System.out.println("Tipo non valido.");
                return;
            }

            agenda.aggiungiCompito(compito);
            System.out.println("Aggiunto! ID: " + compito.getId());

        } catch (NumberFormatException e) {
            System.out.println("Errore: numero non valido.");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    static void cercaPerId() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            Compito c = agenda.cercaCompitoPerId(id);
            if (c != null) {
                System.out.println(c);
            } else {
                System.out.println("Nessun compito con questo ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
        }
    }

    static void cercaPerMateria() {
        Materia[] materie = Materia.values();
        for (int i = 0; i < materie.length; i++) {
            System.out.println((i + 1) + ". " + materie[i]);
        }
        try {
            System.out.print("Scegli materia: ");
            int scelta = Integer.parseInt(scanner.nextLine()) - 1;
            if (scelta < 0 || scelta >= materie.length) {
                System.out.println("Scelta non valida.");
                return;
            }
            ArrayList<Compito> lista = agenda.cercaCompitiPerMateria(materie[scelta]);
            if (lista.isEmpty()) {
                System.out.println("Nessun compito per questa materia.");
            } else {
                for (Compito c : lista) {
                    System.out.println(c);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore input.");
        }
    }

    static void segnaCompletato() {
        try {
            System.out.print("ID compito: ");
            int id = Integer.parseInt(scanner.nextLine());
            Compito c = agenda.cercaCompitoPerId(id);
            if (c != null) {
                c.setCompletato(true);
                System.out.println("Segnato come completato.");
            } else {
                System.out.println("Compito non trovato.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
        }
    }

    static void rimuoviCompito() {
        try {
            System.out.print("ID compito da rimuovere: ");
            int id = Integer.parseInt(scanner.nextLine());
            boolean rimosso = agenda.rimuoviCompito(id);
            if (rimosso) {
                System.out.println("Rimosso.");
            } else {
                System.out.println("Compito non trovato.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
        }
    }

    static void mostraImminenti() {
        try {
            System.out.print("Quanti giorni vuoi controllare? ");
            int giorni = Integer.parseInt(scanner.nextLine());
            agenda.mostraCompitiImminenti(giorni);
        } catch (NumberFormatException e) {
            System.out.println("Numero non valido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    static void mostraStatistiche() {
        System.out.println("\n=== STATISTICHE ===");
        int totale = agenda.getTotaleCompiti();
        int completati = agenda.getCompitiCompletati();
        System.out.println("Totale: " + totale);
        System.out.println("Completati: " + completati);
        System.out.println("Da fare: " + (totale - completati));

        double media = agenda.calcolaMediaVoti();
        if (media > 0) {
            System.out.printf("Media voti: %.2f%n", media);
        } else {
            System.out.println("Nessun voto ancora.");
        }

        agenda.mostraCompitiPerMateria();
    }

    static void inserisciVoto() {
        try {
            System.out.print("ID compito: ");
            int id = Integer.parseInt(scanner.nextLine());
            Compito c = agenda.cercaCompitoPerId(id);
            if (c == null) {
                System.out.println("Compito non trovato.");
                return;
            }
            if (c.getVoto() == null && c.getTipo().equals("CONSEGNA")) {
                System.out.println("Questo compito non ha un voto.");
                return;
            }
            System.out.print("Voto: ");
            double voto = Double.parseDouble(scanner.nextLine());
            c.setVoto(voto);
            System.out.println("Voto salvato.");
        } catch (NumberFormatException e) {
            System.out.println("Valore non valido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    static void modificaDescrizione() {
        try {
            System.out.print("ID compito: ");
            int id = Integer.parseInt(scanner.nextLine());
            Compito c = agenda.cercaCompitoPerId(id);
            if (c == null) {
                System.out.println("Compito non trovato.");
                return;
            }
            System.out.println("Descrizione attuale: " + c.getDescrizione());
            System.out.print("Nuova descrizione: ");
            String nuova = scanner.nextLine();
            c.setDescrizione(nuova);
            System.out.println("Modificato.");
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
