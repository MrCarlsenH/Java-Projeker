package application.modules;

import java.time.LocalDate;
import java.util.ArrayList;

public class Salgsannonce {

    private static int antalAnnoncer = 0;
    private int anonnceNummer = 1;
    private boolean aktiv;
    private LocalDate udløbsdato;
    private ArrayList<Vare> varer = new ArrayList<>();
    private Sælger sælger;

    public Salgsannonce() {
        this.anonnceNummer += antalAnnoncer;
        antalAnnoncer++;
    }
    public Salgsannonce( boolean aktiv, LocalDate udløbsdato, Sælger sælger) {
        this();
        this.aktiv = aktiv;
        this.udløbsdato = udløbsdato;
        this.sælger = sælger;
    }

    public void addVare(Vare vare){
        if(!varer.contains(vare)){
            varer.add(vare);
            vare.setSalgsannonce(this);
        }
    }
    public void removeVare(Vare vare){
        if(varer.contains(vare)){
            varer.remove(vare);
            vare.setSalgsannonce(null);
        }
    }

    //TODO S2
    public int samletAnnonceUdbud(){
        int samletUdbud = 0;
        for (int i = 0; i < varer.size(); i++) {
            samletUdbud += varer.get(i).getUdbudspris();
        }
        return samletUdbud;
    }

    //TODO S7
    public void opdaterSalgsannonce(Salgsannonce salgsannonce){
        if(varer.size() == 0){
            salgsannonce.aktiv = false;
        }
        if(salgsannonce.udløbsdato.isAfter(udløbsdato)){
            salgsannonce.aktiv = false;
        }
    }

    public Sælger getSælger() {
        return sælger;
    }

    public ArrayList<Vare> getVarer() {
        return new ArrayList<>(varer);
    }

    public int getAntalAnnoncer() {
        return antalAnnoncer;
    }

    public int getAnonnceNummer() {
        return anonnceNummer;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public LocalDate getUdløbsdato() {
        return udløbsdato;
    }

    @Override
    public String toString() {
        return "anonnceNummer: " + anonnceNummer +
                ", udløbsdato: " + udløbsdato + "\n" +
                "Varer til salg: " + varer + "\n" +
                "Sælger: " + sælger;
    }
}
