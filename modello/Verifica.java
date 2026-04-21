package modello;

import java.time.LocalDate;

public class Verifica extends Compito {

    private String argomenti;
    private Double voto;

    public Verifica(String descrizione, LocalDate data, Materia materia, String argomenti) {
        super(descrizione, data, materia);
        this.argomenti = argomenti;
        this.voto = null;
    }

    public Verifica(String descrizione, LocalDate data, Materia materia) {
        this(descrizione, data, materia, "");
    }

    public String getArgomenti() {
        return argomenti;
    }

    public void setArgomenti(String argomenti) {
        this.argomenti = argomenti;
    }

    @Override
    public Double getVoto() {
        return voto;
    }

    @Override
    public void setVoto(Double voto) {
        if (voto != null && (voto < 0 || voto > 10)) {
            throw new IllegalArgumentException("Il voto deve essere tra 0 e 10.");
        }
        this.voto = voto;
    }

    @Override
    public String getTipo() {
        return "VERIFICA";
    }

    @Override
    public String toString() {
        String s = super.toString() + " | Argomenti: " + argomenti;
        if (voto != null) {
            s = s + " | Voto: " + voto;
        }
        return s;
    }
}
