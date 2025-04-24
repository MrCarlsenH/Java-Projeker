package guifx;

import application.controller.Controller;
import application.modules.Bane;
import application.modules.Booking;
import application.modules.Kategori;
import application.modules.Spiller;
import javafx.application.Application;
import storage.Storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        initStorage();

        File file = new File("tekstfil.txt");
        PrintWriter printWriter = new PrintWriter("tekstfil.txt");

        for (int i = 0; i < Storage.getSpillere().size(); i++) {
            for (int j = 0; j < Storage.getSpillere().get(i).getBookinger().size(); j++) {
                printWriter.println(Storage.getSpillere().get(i).getBookinger().get(j));
            }
        }
        printWriter.close();
        Controller.udskrivInitStorage(file);
        Application.launch(AppVindue.class);

    }

    public static void initStorage() throws FileNotFoundException {

        Kategori k1 = Controller.createKategori("Luksus",100,200);
        Kategori k2 = Controller.createKategori("Mellem",50,100);
        Kategori k3 = Controller.createKategori("Begynder",25,50);

        Bane b1 = Controller.createBane(1,true, LocalTime.of(9,0),LocalTime.of(17,0),k1);
        Bane b2 = Controller.createBane(2,true, LocalTime.of(9,0),LocalTime.of(17,0),k1);
        Bane b3 = Controller.createBane(3,true, LocalTime.of(9,0),LocalTime.of(17,0),k2);
        Bane b4 = Controller.createBane(4,false, LocalTime.of(9,0),LocalTime.of(17,0),k2);
        Bane b5 = Controller.createBane(5,false, LocalTime.of(9,0),LocalTime.of(17,0),k3);
        Bane b6 = Controller.createBane(6,false, LocalTime.of(9,0),LocalTime.of(17,0),k3);

        Spiller s1 = Controller.createSpiller("Andreas","DMU");
        Spiller s2 = Controller.createSpiller("Petra","DMU");
        Spiller s3 = Controller.createSpiller("Henrik","ITA");
        Spiller s4 = Controller.createSpiller("Ulla","ITA");

        Controller.createBooking(LocalDate.of(2023,6,20),LocalTime.of(10,0),true,b3,s1);
        Controller.createBooking(LocalDate.of(2023,6,20),LocalTime.of(10,0),false,b2,s1);
        Controller.createBooking(LocalDate.of(2023,6,20),LocalTime.of(11,0),false,b3,s3);
        Controller.createBooking(LocalDate.of(2023,6,20),LocalTime.of(16,0),false,b3,s4);
        Controller.createBooking(LocalDate.of(2023,6,20),LocalTime.of(17,0),true,b5,s4);

        Controller.createBooking(LocalDate.of(2023,7,20),LocalTime.of(10,0),true,b3,s4);

        System.out.println("Viser antal bookede timer på dato: ");
        System.out.println(b2.bookedeTimerPåDato(LocalDate.of(2023,6,20)));
        System.out.println();

        System.out.println("Viser den samlede pris for en spiller i en bestemt kategori: ");
        System.out.println(s1.samletPris(k2));
        System.out.println();

        System.out.println("Viser et array over antal bookinger pr. time på en bane: ");
        System.out.println(Arrays.toString(b3.antalBookingerPrTime()));
        System.out.println();

        System.out.println("Viser den samlet bookede doubletid på en uddannelse med en bestemt kategori:");
        System.out.println(Controller.samletBooketDoubleTid("ITA",k2));
        System.out.println();

        System.out.println("Viser om en tid på en dato er ledig på en bestemt bane: ");
        System.out.println(b3.tidLedig(LocalDate.of(2023,6,20),LocalTime.of(12,0)));
        System.out.println();

        System.out.println("Finder den første ledige bane på dato med bestemt tidspunkt og kategori:");
        System.out.println(Controller.findLedigBane(LocalDate.of(2023,6,20),LocalTime.of(11,0),k2));
        System.out.println();
    }
}
