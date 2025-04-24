package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Job {
    private String kode;
    private String beskrivelse;
    private LocalDate dato;
    private int timeHonorar;
    private int antalTimer;
    private ArrayList<Vagt> vagter = new ArrayList<>();

    public Job(String kode, String beskrivelse, LocalDate dato, int timeHonorar, int antalTimer) {
        this.kode = kode;
        this.beskrivelse = beskrivelse;
        this.dato = dato;
        this.timeHonorar = timeHonorar;
        this.antalTimer = antalTimer;
    }

    public Vagt createVagt(int timer){
        Vagt vagt = new Vagt(timer, this);
        vagter.add(vagt);
        return vagt;
    }

    public ArrayList<Vagt> getVagter() {
        return new ArrayList<>(vagter);
    }

    public String getKode() {
        return kode;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public LocalDate getDato() {
        return dato;
    }

    public int getTimeHonorar() {
        return timeHonorar;
    }

    public int getAntalTimer() {
        return antalTimer;
    }

    public int ikkeBesatteTimer(){
        int timerTilbage = this.antalTimer;
        for (int i = 0; i <vagter.size() ; i++) {
            timerTilbage -= vagter.get(i).getTimer();
        }
        return timerTilbage;
    }
}
