package modello;

import java.time.LocalDate;

public class Consegna extends Compito {

    private String dettagli;

    public Consegna(String descrizione, LocalDate data, Materia materia, String dettagli) {
        super(descrizione, data, materia);
        this.dettagli = dettagli;
    }

    public String getDettagli() {
        return dettagli;
    }

    public void setDettagli(String dettagli) {
        this.dettagli = dettagli;
    }

    @Override
    public String getTipo() {
        return "CONSEGNA";
    }

    @Override
    public Double getVoto() {
        return null;
    }

    @Override
    public void setVoto(Double voto) {
    }

    @Override
    public String toString() {
        return super.toString() + " | Dettagli: " + dettagli;
    }
}
