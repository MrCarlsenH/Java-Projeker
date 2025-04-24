package application.modules;

import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Bane {

    private int nummer;
    private  boolean inde;
    private LocalTime førsteTid;
    private LocalTime sidsteTid;
    private ArrayList<Booking> bookinger = new ArrayList<>();
    private Kategori kategori;

    public Bane(int nummer, boolean inde, LocalTime førsteTid, LocalTime sidsteTid, Kategori kategori) {
        this.nummer = nummer;
        this.inde = inde;
        this.førsteTid = førsteTid;
        this.sidsteTid = sidsteTid;
        this.kategori = kategori;
    }



    public void addBooking(Booking booking){
        if (!bookinger.contains(booking)){
            bookinger.add(booking);
            booking.setBane(this);
        }
    }
    public void removeBooking(Booking booking){
        if (bookinger.contains(booking)){
            bookinger.remove(booking);
            booking.setBane(null);
        }
    }

    public ArrayList<Booking> getBookinger() {
        return new ArrayList<>(bookinger);
    }

    public Kategori getKategori() {
        return kategori;
    }

    public int getNummer() {
        return nummer;
    }

    public String isIndeString() {
        if(inde == true)
        return "inde";
        else {
            return "Ude";
        }
    }
    public boolean isInde() {
        return inde;
    }

    public LocalTime getFørsteTid() {
        return førsteTid;
    }

    public LocalTime getSidsteTid() {
        return sidsteTid;
    }

    //TODO Opgave S2
    public int bookedeTimerPåDato(LocalDate dato){
        int sameledeTimer = 0;
        for (int i = 0; i < bookinger.size(); i++) {
            if(bookinger.get(i).getDato().equals(dato)){
                sameledeTimer++;
            }

        }
        return sameledeTimer;
    }

    //TODO Opgave S4

    public int[] antalBookingerPrTime(){
        ArrayList booking = new ArrayList<>(bookinger);
        int[] tidsInterval = new int[sidsteTid.getHour()  - førsteTid.getHour()+1];
        int[] antalBookingerPåTid = new int[sidsteTid.getHour() - førsteTid.getHour()+1];
        int tidLagtTil = førsteTid.getHour();
        for (int i = 0; i < tidsInterval.length; i++) {
            tidsInterval[i] = tidLagtTil;
            tidLagtTil++;
        }
        for (int i = 0; i < tidsInterval.length; i++) {
            for (int j = 0; j < bookinger.size(); j++) {
                if(tidsInterval[i] == bookinger.get(j).getTid().getHour()){
                    antalBookingerPåTid[i]++;
                }
            }
        }

        return antalBookingerPåTid;
    }

    //TODO Opgave S8

    public boolean tidLedig(LocalDate dato, LocalTime tid){
        boolean erLedig = true;
        int i = 0;
        while(i < getBookinger().size() && erLedig){
            if(getBookinger().get(i).getTid().equals(tid) && getBookinger().get(i).getDato().equals(dato)){
                    erLedig = false;
            }
            else {
                i++;
            }
        }
        return erLedig;
    }


    @Override
    public String toString() {
        return "Nr. " + nummer + " " + isIndeString() + " (" + førsteTid + "->" + sidsteTid + ")," + kategori.getNavn();
    }
}
