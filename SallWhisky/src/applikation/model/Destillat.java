package applikation.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillat extends DestillatComponent {
    private LocalDate påfyldningsDato;
    private int alkoholProcent;
    private int antalLiter;
    private ArrayList<DestillatComponent> destillatComponents;
    private Fad fad;
    private boolean isWhiskyReady;


    public Destillat(Fad fad, LocalDate påfyldningsDato, int alkoholProcent,
                     ArrayList<DestillatComponent> destillatComponents, int antalLiter) {
        this.påfyldningsDato = påfyldningsDato;
        this.alkoholProcent = alkoholProcent;
        this.destillatComponents = destillatComponents;
        this.setFad(fad);
        this.antalLiter = antalLiter;
        setIsWhiskyReady(destillatComponents);
    }
    public Destillat(Fad fad, LocalDate påfyldningsDato,
                     int alkoholProcent, ArrayList<DestillatComponent> destillatComponents) {
        this.påfyldningsDato = påfyldningsDato;
        this.alkoholProcent = alkoholProcent;
        this.destillatComponents = destillatComponents;
        this.setFad(fad);
        this.antalLiter = startAntalLiter();
        setIsWhiskyReady(destillatComponents);
    }

    private int startAntalLiter() {
        for (DestillatComponent p : destillatComponents) {
            if (p.getClass() == Påfyldning.class) {
                this.antalLiter += p.getAntalLiter();
            }
        }
        return this.antalLiter;
    }

    public Fad getFad() {
        return fad;
    }

    public int[] getFadPlads(){
        return fad.getLokation(this.fad);
    }
    public void setFad(Fad fad) {
        if (this.fad != fad && !fad.isPensioneret()) {
            this.fad = fad;
            this.fad.setLedig(false);
            fad.inkrementerAntalGangeBrugt();
        } else throw new RuntimeException("Fadet er pensioneret!");

    }


    @Override
    public int getAntalLiter() {
        return antalLiter;
    }

    public void setAntalLiter(int antalLiter) {
        this.antalLiter = antalLiter;
    }

    public LocalDate getPåfyldningsDato() {
        return påfyldningsDato;
    }

    public int getAlkoholProcent() {
        return alkoholProcent;
    }

    public ArrayList<DestillatComponent> getDestillatComponents() {
        return destillatComponents;
    }

    private void setIsWhiskyReady(ArrayList<DestillatComponent> destillatComponents) {
        boolean isWhiskyReady = true;
        int i = 0;
        while (isWhiskyReady && i < destillatComponents.size()) {
            int antalAarsLagring = this.påfyldningsDato.getYear() - destillatComponents.get(i).getPåfyldningsDato().getYear();
            if (destillatComponents.get(i) instanceof Påfyldning) {
                isWhiskyReady = false;
            } else if (antalAarsLagring < 3) {
                isWhiskyReady = false;
            }
            i++;
        }
        this.isWhiskyReady = isWhiskyReady;
    }

    public boolean isWhiskyReady() {
        return isWhiskyReady;
    }

    @Override
    public String getName() {
        String dato = påfyldningsDato.toString();
        String fadet = fad.toString();
        return fadet + ", Dato: " + dato + ". Batches: " + getBatches() + "\n" + "Antal liter: " + antalLiter;
    }

    public String getBatches() {
        String string = "";
        for (DestillatComponent dc : destillatComponents) {
            if (dc instanceof Påfyldning) {
                string += ((Påfyldning) dc).getBatch().getNewSpiritBatchNr() + ", ";
            } else if (dc instanceof Destillat) {
                string += ((Destillat) dc).getBatches();
            }
        }
        return string;
    }

    public String historik() {
        String string = ("Påfyldnings dato: " + påfyldningsDato + "\n" + "Har været på fadID: " + fad.getFadNr() +
                ", Alkoholprocent: " + alkoholProcent + ", Antal liter: " + antalLiter +
                "\n" + "\n");
        for (DestillatComponent påfyldning : destillatComponents) {
            if(påfyldning instanceof Påfyldning) {
                string += påfyldning.historik() + "\n";
            } else {
                string +="\n" + "Destillat: " + påfyldning.historik() + "\n";
            }
        }
        return string;

    }

    @Override
    public String toString() {
        String string = this.getName();
        return string;
    }






}
