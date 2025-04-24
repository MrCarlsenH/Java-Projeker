package application.controller;

import application.modules.Bane;
import application.modules.Booking;
import application.modules.Kategori;
import application.modules.Spiller;
import storage.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static Spiller createSpiller(String navn, String uddannelse) {
        Spiller spiller = new Spiller(navn, uddannelse);
        Storage.addSpiller(spiller);
        return spiller;
    }

    public static Bane createBane(int nummer, boolean inde, LocalTime førsteTid, LocalTime sidsteTid, Kategori kategori) {
        Bane bane = new Bane(nummer, inde, førsteTid, sidsteTid, kategori);
        Storage.addBane(bane);
        return bane;
    }

    public static Kategori createKategori(String navn, int prisKrSingle, int prisKrDouble) {
        Kategori kategori = new Kategori(navn, prisKrSingle, prisKrDouble);
        Storage.addKategori(kategori);
        return kategori;
    }

    public static Booking createBooking(LocalDate dato, LocalTime tid, boolean single, Bane bane, Spiller spiller) {
        Booking booking = spiller.createBooking(dato, tid, single, bane);
        bane.addBooking(booking);
        return booking;
    }

    //TODO Opgave S7
    public static int samletBooketDoubleTid(String uddannelse, Kategori kategori) {
        int samletAntalTimer = 0;

        for (int i = 0; i < Storage.getSpillere().size(); i++) {
            if (Storage.getSpillere().get(i).getUddannelse().equals(uddannelse)) {
                for (int j = 0; j < Storage.getSpillere().get(i).getBookinger().size(); j++) {
                    if (Storage.getSpillere().get(i).getBookinger().get(j).getBane().getKategori().equals(kategori) && !Storage.getSpillere().get(i).getBookinger().get(j).isSingle()) {
                        samletAntalTimer++;
                    }
                }

            }
        }
        return samletAntalTimer;
    }

    //TODO Opgave S9

    public static Bane findLedigBane(LocalDate dato, LocalTime tid, Kategori kategori) {
        boolean found = false;
        Bane bane = null;
        int i = 0;
        while (!found && i < Storage.getBaner().size()) {
            if (Storage.getBaner().get(i).tidLedig(dato, tid) == true && Storage.getBaner().get(i).getKategori().equals(kategori)) {
                found = true;
                bane = Storage.getBaner().get(i);
            } else {
                i++;
            }
        }
        return bane;
    }

    //TODO Opgave S10
    public static void udskrivInitStorage(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
