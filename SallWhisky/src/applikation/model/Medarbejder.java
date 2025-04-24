package applikation.model;

public class Medarbejder {
    private String navn;
    private int medNr;

    public Medarbejder(String navn, int medNr) {
        this.navn = navn;
        this.medNr = medNr;
    }


    public String getNavn() {
        return navn;
    }

    public int getMedNr() {
        return medNr;
    }

    @Override
    public String toString() {
        return navn + ", " + medNr;
    }
}
