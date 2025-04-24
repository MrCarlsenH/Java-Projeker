package application.modules;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Spiller {

    private String navn;
    private String uddannelse;
    private ArrayList<Booking> bookinger = new ArrayList<>();

    public Spiller(String navn, String uddannelse) {
        this.navn = navn;
        this.uddannelse = uddannelse;
    }

    public String getNavn() {
        return navn;
    }

    public String getUddannelse() {
        return uddannelse;
    }

    public Booking createBooking(LocalDate dato, LocalTime tid, boolean single,Bane bane) {
        Booking booking = null;
        try {
            if (bane.tidLedig(dato, tid)) {
                booking = new Booking(dato, tid, single, bane, this);
                if (!bookinger.contains(booking)) {
                    bookinger.add(booking);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return booking;
    }

    public ArrayList<Booking> getBookinger() {
        return new ArrayList<>(bookinger);
    }

    //TODO Opgave S3
    public int samletPris(Kategori kategori){
        int samletPris = 0;
        for (int i = 0; i < bookinger.size(); i++) {
            if(bookinger.get(i).getBane().getKategori() == kategori){
                if(bookinger.get(i).isSingle()){
                   samletPris += kategori.getPrisKrSingle();
                }
                else {
                    samletPris += kategori.getPrisKrDouble();
                }
            }
        }
        return samletPris;
    }

    @Override
    public String toString() {
        return navn + " (" + uddannelse + ")";
    }
}
