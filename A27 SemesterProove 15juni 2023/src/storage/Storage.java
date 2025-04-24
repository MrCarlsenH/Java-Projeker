package storage;

import application.modules.Bane;
import application.modules.Kategori;
import application.modules.Spiller;

import java.util.ArrayList;

public class Storage {

    private static ArrayList<Spiller> spillere = new ArrayList<>();
    private static ArrayList<Bane> baner = new ArrayList<>();
    private static ArrayList<Kategori> kategorier = new ArrayList<>();

    public static ArrayList<Spiller> getSpillere() {
        return new ArrayList<Spiller>(spillere);
    }

    public static void addSpiller(Spiller spiller) {
        spillere.add(spiller);
    }

    public static ArrayList<Bane> getBaner() {
        return new ArrayList<Bane>(baner);
    }

    public static void addBane(Bane bane) {
        baner.add(bane);
    }


    public static ArrayList<Kategori> getKategorier() {
        return new ArrayList<Kategori>(kategorier);
    }

    public static void addKategori(Kategori kategori) {
        kategorier.add(kategori);
    }

}