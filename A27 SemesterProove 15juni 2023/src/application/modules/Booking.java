package application.modules;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {

    private LocalDate dato;
    private LocalTime tid;
    private boolean single;
    private Spiller spiller;
    private Bane bane;

    public Booking(LocalDate dato, LocalTime tid, boolean single,Bane bane ,Spiller spiller) {
        this.dato = dato;
        this.tid = tid;
        this.single = single;
        this.spiller = spiller;
        this.bane = bane;
    }

    public void setBane(Bane bane) {
        if (this.bane != bane) {
            Bane oldBane = this.bane;
            if (oldBane != null) {
                bane.removeBooking(this);
            }
            this.bane = bane;
            if (bane != null) {
                bane.addBooking(this);
            }
        }
    }

    public Spiller getSpiller() {
        return spiller;
    }

    public Bane getBane() {
        return bane;
    }

    public LocalDate getDato() {
        return dato;
    }

    public LocalTime getTid() {
        return tid;
    }

    public boolean isSingle() {
       return single;
    }
    public String isSingleString() {
        if(single == true){
            return "single";
        }
        else{
            return "double";
        }
    }


    @Override
    public String toString() {
        return "Bane nr: " + bane.getNummer() + ", " + dato + " kl. " + tid + ", spil: " + isSingleString() + ", spiller: " + spiller.getNavn();
    }
}
