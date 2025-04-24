package applikation.model;

import java.time.LocalDate;

public class Whisky {
    private String navn;
    private boolean tilsatVand;
    private LocalDate tapningsdato;
    private int antalLiter;
    private DestillatComponent destillat;

    public Whisky(String navn, boolean tilsatVand, LocalDate tapningsdato, int antalLiter, DestillatComponent destillat) {
        this.navn = navn;
        this.tilsatVand = tilsatVand;
        this.tapningsdato = tapningsdato;
        this.antalLiter = antalLiter;
        this.destillat = (Destillat) destillat;
    }

    public String getNavn() {
        return navn;
    }

    public boolean isTilsatVand() {
        return tilsatVand;
    }

    public boolean isSingleCask() {
        boolean isSingleCask = true;
        Destillat destillatTemp = (Destillat) destillat;
        if (destillatTemp.getDestillatComponents().size() > 1) {
            isSingleCask = false;
        }
        return isSingleCask;
    }

    public LocalDate getTapningsdato() {
        return tapningsdato;
    }

    public int getAntalLiter() {
        return antalLiter;
    }

    public DestillatComponent getDestillat() {
        return destillat;
    }

    @Override
    public String toString() {
        String caskStrength = "";
        if (!tilsatVand) {
            caskStrength = "Cask strength, ";
        }
        String singleCask = "Blended";
        if (isSingleCask()) {
            singleCask = ", Single cask";
        }

        return "Whisky: " + "\n" + "Batches: " + getNavn() + "\n" + "Betegnelser: " + caskStrength + singleCask + "\n" + "Antal liter: " + antalLiter;
    }
}
