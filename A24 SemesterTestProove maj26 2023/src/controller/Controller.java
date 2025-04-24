package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;

public class Controller {

    public static Frivillig createFrivillig(String navn, String mobil, int maksAntalTimer){
        Frivillig frivillig = new Frivillig(navn, mobil, maksAntalTimer);
        Storage.addFrivillig(frivillig);
        return frivillig;
    }

    public static Festival createFestival(String navn, LocalDate fradato, LocalDate tildato){
        Festival festival = new Festival(navn, fradato, tildato);
        Storage.addFestival(festival);
        return festival;
    }

    public static FrivilligForening createFrivilligForening(String navn, String mobil, int maksAntalTimer, String foreningsNavn, int antalPersoner){
        FrivilligForening ff = new FrivilligForening(navn, mobil, maksAntalTimer, foreningsNavn, antalPersoner);
        return ff;
    }

    public static Job createJob(Festival festival, String kode, String beskrivelse, LocalDate dato, int timeHonorar, int antalTimer){
        Job job = festival.createJob(kode, beskrivelse, dato, timeHonorar, antalTimer);
        return job;
    }

    public static Vagt createVagt(Job job,int timer){
        Vagt vagt = job.createVagt(timer);
        return vagt;
    }
}
