package applikation.model;

import java.time.LocalDate;

public class Påfyldning extends DestillatComponent {
    private int antalLiter;
    private LocalDate påfyldningsDato;
    private Batch batch;

    public Påfyldning(int antalLiter, Batch batch) {
        this.antalLiter = antalLiter;
        setBatch(batch);
        this.påfyldningsDato = batch.getSlutDato();
    }

    public Batch getBatch() {
        return batch;
    }

    public int getAntalLiter() {
        return antalLiter;
    }

    @Override
    public LocalDate getPåfyldningsDato() {
        return påfyldningsDato;
    }


    public String historik(){
        String string = "";
        string +=
                "    BatchNr: " + batch.getNewSpiritBatchNr() + "\n" +
                "    Kornsort: " +batch.getKornsort() + "\n"  +
                "    AlkoholProcent: " + batch.getAlkoholProcent() + "\n" +
                "    Startet med at indeholde:  " + batch.getAntalLiter()  + "\n" +
                "    Antal Liter tilbage " + batch.beregnResterendeMængde() + "\n"+
                "    StartDato: " + batch.getStartDato() + "\n" +
                "    SlutDato:  " + batch.getSlutDato() + "\n" +
                "    Malbatch: " + batch.getMalBatch() + "\n" +
                "    Medarbejder: " + batch.getMedarbejder().getNavn() + "\n" +
                "    Kommentar: " + batch.getKommentar() + "\n";

        return string;

    }

    private void setBatch(Batch batch) {
            this.batch = batch;
            if(batch != null){
                batch.addPåfyldning(this);
            }
        }

}
