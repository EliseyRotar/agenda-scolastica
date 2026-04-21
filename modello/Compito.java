package modello;

import java.time.LocalDate;

public abstract class Compito {

    private int id;
    private String descrizione;
    private LocalDate data;
    private Materia materia;
    private boolean completato;
    private static int prossimoId = 1;

    public Compito(String descrizione, LocalDate data, Materia materia) {
        if (descrizione == null || descrizione.trim().isEmpty()) {
            throw new IllegalArgumentException("La descrizione non può essere vuota.");
        }
        if (data == null) {
            throw new IllegalArgumentException("La data non può essere nulla.");
        }
        if (materia == null) {
            throw new IllegalArgumentException("La materia non può essere nulla.");
        }
        this.id = prossimoId;
        prossimoId++;
        this.descrizione = descrizione;
        this.data = data;
        this.materia = materia;
        this.completato = false;
    }

    public int getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        if (descrizione == null || descrizione.trim().isEmpty()) {
            throw new IllegalArgumentException("La descrizione non può essere vuota.");
        }
        this.descrizione = descrizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public boolean isCompletato() {
        return completato;
    }

    public void setCompletato(boolean completato) {
        this.completato = completato;
    }

    public abstract String getTipo();

    public abstract Double getVoto();

    public abstract void setVoto(Double voto);

    @Override
    public String toString() {
        String stato = completato ? "[X]" : "[ ]";
        return stato + " ID:" + id + " - " + getTipo() + " | " + materia + " | " + data + " | " + descrizione;
    }
}
