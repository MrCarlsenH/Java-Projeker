package guifx;

import application.controller.Controller;
import application.modules.Salgsannonce;
import application.modules.Sælger;
import application.modules.Vare;
import application.modules.Varekategori;
import javafx.application.Application;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        initStorage();
        Application.launch(AppVindue.class);
    }

    public static void initStorage() {

        Sælger s1 = Controller.createSælger("Viktor", 23, "45344247");
        Sælger s2 = Controller.createSælger("Gustav", 35, "56124522");

        Vare v1 = Controller.createVare("Samsung mobil", 1200, false, Varekategori.MOBILTELEFON);
        Vare v2 = Controller.createVare("IPhone", 2000, false, Varekategori.MOBILTELEFON);
        Vare v3 = Controller.createVare("Java", 400, false, Varekategori.STUDIEBOG);
        Vare v4 = Controller.createVare("Android", 300, false, Varekategori.STUDIEBOG);
        Vare v5 = Controller.createVare("Python", 200, false, Varekategori.STUDIEBOG);
        Vare v6 = Controller.createVare("Regnjakke", 100, false, Varekategori.TØJ);
        Vare v7 = Controller.createVare("Regnbukser", 80, false, Varekategori.TØJ);

        Salgsannonce sA1 = Controller.createSalgsannonce(true, LocalDate.of(2023, 5, 6), s1);
        sA1.addVare(v1);

        Salgsannonce sA2 = Controller.createSalgsannonce(true, LocalDate.of(2023, 5, 6), s1);
        sA2.addVare(v3);
        sA2.addVare(v4);
        sA2.addVare(v5);

        Salgsannonce sA3 = Controller.createSalgsannonce(true, LocalDate.of(2023, 5, 6), s2);
        sA3.addVare(v2);

        Salgsannonce sA4 = Controller.createSalgsannonce(true, LocalDate.of(2023, 5, 6), s2);
        sA4.addVare(v6);
        sA4.addVare(v7);

        ArrayList<Vare> StinesVare = new ArrayList<>();
        StinesVare.add(v1);
        StinesVare.add(v5);

        ArrayList<Vare> LaurasVare = new ArrayList<>();
        LaurasVare.add(v6);
        LaurasVare.add(v7);

        Controller.createSalg("Stine",450,StinesVare);
        Controller.createSalg("Laura",120,LaurasVare);


        System.out.println(sA1);
        System.out.println();
        System.out.println(sA2);
        System.out.println();

    }
}
