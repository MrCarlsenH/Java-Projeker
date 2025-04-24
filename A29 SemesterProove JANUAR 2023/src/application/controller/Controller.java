package application.controller;

import application.modules.*;
import storage.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static Salgsannonce createSalgsannonce(boolean aktiv, LocalDate udløbsdato, Sælger sælger){
        Salgsannonce salgsannonce = sælger.createSalgsannonce(aktiv, udløbsdato);
        return salgsannonce;
    }

    public static Sælger createSælger(String navn, int studiekortNummer, String mobil){
        Sælger sælger = new Sælger(navn,studiekortNummer,mobil);
        Storage.addSælger(sælger);
        return sælger;
    }

    public static Vare createVare(String navn, int udbudspris, boolean solgt, Varekategori kategori){
        Vare vare = new Vare(navn,udbudspris,solgt,kategori);
        Storage.addVare(vare);
        return vare;
    }

    public static Salg createSalg(String købersNavn, int aftaltSamletPris, ArrayList<Vare> varer){
        Salg salg = new Salg(købersNavn,aftaltSamletPris,varer);
        Storage.addSalg(salg);
        return salg;
    }
}
