package application.modules;

import java.util.ArrayList;

public class Salg {

    private String købersNavn;
    private int aftaltSamletPris;
    private ArrayList<Vare> varer = new ArrayList<>();

    public Salg(String købersNavn, int aftaltSamletPris, ArrayList<Vare> varer) {
        this.købersNavn = købersNavn;
        this.aftaltSamletPris = aftaltSamletPris;
        this.varer = new ArrayList<>(varer);
    }

    public void addVare(Vare vare){
        if(!varer.contains(vare)) {
            varer.add(vare);
        }
    }
    public void removeVare(Vare vare){
        if(varer.contains(vare)){
            varer.remove(vare);
        }
    }

    public ArrayList<Vare> getVarer() {
        return new ArrayList<>(varer);
    }

    public String getKøbersNavn() {
        return købersNavn;
    }
    public int getAftaltSamletPris() {
        return aftaltSamletPris;
    }
}
