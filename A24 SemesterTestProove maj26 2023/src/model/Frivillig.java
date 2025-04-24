package model;

import java.util.ArrayList;

public class Frivillig{

    private String navn;
    private String mobil;
    private int maksAntaltimer;
    private ArrayList<Vagt> vagter = new ArrayList<>();

    public Frivillig(String navn, String mobil, int maksAntalTimer) {
        this.navn = navn;
        this.mobil = mobil;
        this.maksAntaltimer = maksAntalTimer;
    }

    public String getNavn() {
        return navn;
    }

    public String getMobil() {
        return mobil;
    }

    public int getMaksAntaltimer() {
        return maksAntaltimer;
    }

    public void addVagt(Vagt vagt) {
        if (!vagter.contains(vagt)) {
            vagter.add(vagt);
            vagt.setFrivillig(this);
        }
    }
    public void removeVagt(Vagt vagt) {
        if (vagter.contains(vagt)) {
            vagter.remove(vagt);
            vagt.setFrivillig(null);
        }
    }

    public ArrayList<Vagt> getVagter() {
        return new ArrayList<>(vagter);
    }
    public int ledigeTimer(){
        int timerTilbage = this.maksAntaltimer;
        for (int i = 0; i < vagter.size(); i++) {
            timerTilbage -= vagter.get(i).getTimer();
        }
        return timerTilbage;
    }
}
