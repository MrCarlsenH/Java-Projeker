package storage;


import application.modules.Salg;
import application.modules.Sælger;
import application.modules.Vare;

import java.util.ArrayList;

public class Storage {

    private static ArrayList<Sælger> sælgere = new ArrayList<>();
    private static ArrayList<Vare> varer = new ArrayList<>();
    private static ArrayList<Salg> solgte = new ArrayList<>();


    public static ArrayList<Sælger> getSælgere() {
        return new ArrayList<Sælger>(sælgere);
    }

    public static void addSælger(Sælger sælger) {
        sælgere.add(sælger);
    }

    public static ArrayList<Vare> getVarer() {
        return new ArrayList<Vare>(varer);
    }

    public static void addVare(Vare vare) {
        varer.add(vare);
    }


    public static ArrayList<Salg> getSolgte() {
        return new ArrayList<Salg>(solgte);
    }

    public static void addSalg(Salg salg) {
        solgte.add(salg);
    }
}