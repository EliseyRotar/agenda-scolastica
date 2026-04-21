package gestione;

import modello.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Agenda {

    private ArrayList<Compito> compiti;

    public Agenda() {
        compiti = new ArrayList<>();
    }

    public void aggiungiCompito(Compito c) {
        if (c == null) {
            throw new IllegalArgumentException("Il compito non può essere nullo.");
        }
        compiti.add(c);
    }

    public boolean rimuoviCompito(int id) {
        for (int i = 0; i < compiti.size(); i++) {
            if (compiti.get(i).getId() == id) {
                compiti.remove(i);
                return true;
            }
        }
        return false;
    }

    public Compito cercaCompitoPerId(int id) {
        for (Compito c : compiti) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Compito> cercaCompitiPerMateria(Materia m) {
        ArrayList<Compito> risultato = new ArrayList<>();
        for (Compito c : compiti) {
            if (c.getMateria() == m) {
                risultato.add(c);
            }
        }
        return risultato;
    }

    public ArrayList<Compito> cercaCompitiPerData(LocalDate data) {
        ArrayList<Compito> risultato = new ArrayList<>();
        for (Compito c : compiti) {
            if (c.getData().equals(data)) {
                risultato.add(c);
            }
        }
        return risultato;
    }

    public void mostraTuttiCompiti() {
        if (compiti.isEmpty()) {
            System.out.println("Nessun compito in agenda.");
            return;
        }
        System.out.println("\n=== TUTTI I COMPITI ===");
        for (Compito c : compiti) {
            System.out.println(c);
        }
    }

    public void mostraCompitiPerMateria() {
        System.out.println("\n=== COMPITI PER MATERIA ===");
        for (Materia materia : Materia.values()) {
            int contatore = 0;
            for (Compito compito : compiti) {
                if (compito.getMateria() == materia) {
                    contatore++;
                }
            }
            if (contatore > 0) {
                System.out.println(materia + ": " + contatore + " compiti");
            }
        }
    }

    public void mostraCompitiImminenti(int giorni) {
        if (giorni < 0) {
            throw new IllegalArgumentException("Il numero di giorni non può essere negativo.");
        }
        LocalDate oggi = LocalDate.now();
        LocalDate limite = oggi.plusDays(giorni);

        System.out.println("\n=== COMPITI NEI PROSSIMI " + giorni + " GIORNI ===");
        boolean trovato = false;
        for (Compito compito : compiti) {
            LocalDate dataCompito = compito.getData();
            if (!dataCompito.isBefore(oggi) && !dataCompito.isAfter(limite) && !compito.isCompletato()) {
                System.out.println(compito);
                trovato = true;
            }
        }
        if (!trovato) {
            System.out.println("Nessun compito in scadenza.");
        }
    }

    public void ordinaPerData() {
        int dimensione = compiti.size();
        for (int i = 0; i < dimensione - 1; i++) {
            for (int j = 0; j < dimensione - 1 - i; j++) {
                if (compiti.get(j).getData().isAfter(compiti.get(j + 1).getData())) {
                    Compito temp = compiti.get(j);
                    compiti.set(j, compiti.get(j + 1));
                    compiti.set(j + 1, temp);
                }
            }
        }
    }

    public double calcolaMediaVoti() {
        double somma = 0;
        int quanti = 0;
        for (Compito c : compiti) {
            Double voto = c.getVoto();
            if (voto != null) {
                somma += voto;
                quanti++;
            }
        }
        if (quanti == 0) return 0;
        return somma / quanti;
    }

    public int getTotaleCompiti() {
        return compiti.size();
    }

    public int getCompitiCompletati() {
        int tot = 0;
        for (Compito c : compiti) {
            if (c.isCompletato()) tot++;
        }
        return tot;
    }
}
