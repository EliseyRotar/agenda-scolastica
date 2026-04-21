package modello;

import java.time.LocalDate;

public class Interrogazione extends Compito {

    private String argomento;
    private Double voto;

    public Interrogazione(String descrizione, LocalDate data, Materia materia, String argomento) {
        super(descrizione, data, materia);
        this.argomento = argomento;
        this.voto = null;
    }

    public String getArgomento() {
        return argomento;
    }

    public void setArgomento(String argomento) {
        this.argomento = argomento;
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
        return "INTERROGAZIONE";
    }

    @Override
    public String toString() {
        String base = super.toString();
        if (voto != null) {
            return base + " | Argomento: " + argomento + " | Voto: " + voto;
        }
        return base + " | Argomento: " + argomento;
    }
}
