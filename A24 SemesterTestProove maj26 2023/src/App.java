import controller.Controller;
import model.Festival;
import model.Frivillig;
import model.Job;
import model.Vagt;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        initStorage();
    }

    public static void initStorage() {

        Festival festival1 = Controller.createFestival("Northside", LocalDate.of(2020,6,4),LocalDate.of(2020,6,6));

        Frivillig f1 = Controller.createFrivillig("Jane Jensen","12345677",20);
        Frivillig f2 = Controller.createFrivillig("Lone Hansen","78787878",25);
        Frivillig f3 = Controller.createFrivillig("Anders Mikkelsen","55555555",10);

        Controller.createFrivilligForening("Christian Madsen","23232323",100,"Erhvervsakadami Aarhus",40);


        Job j1 = Controller.createJob(festival1,"T1","Rengøring af toilet",LocalDate.of(2020,6,4),100,5);
        Job j2 = Controller.createJob(festival1,"T2","Rengøring af toilet",LocalDate.of(2020,6,4),150,5);
        Job j3 = Controller.createJob(festival1,"T3","Rengøring af toilet",LocalDate.of(2020,6,4),100,5);
        Job j4 = Controller.createJob(festival1,"T4","Rengøring af toilet",LocalDate.of(2020,6,5),100,5);
        Job j5 = Controller.createJob(festival1,"T5","Rengøring af toilet",LocalDate.of(2020,6,5),100,5);
        Job j6 = Controller.createJob(festival1,"T6","Rengøring af toilet",LocalDate.of(2020,6,5),100,5);
        Job j7 = Controller.createJob(festival1,"T7","Rengøring af toilet",LocalDate.of(2020,6,6),100,5);
        Job j8 = Controller.createJob(festival1,"T8","Rengøring af toilet",LocalDate.of(2020,6,6),100,5);
        Job j9 = Controller.createJob(festival1,"T9","Rengøring af toilet",LocalDate.of(2020,6,6),100,5);

        System.out.println("Festivallens totale udgift, hvis alle jobs udført");
        System.out.println(festival1.budgetteretJobUdgift());
        System.out.println();

        System.out.println("Festivallens totale udgift af de jobs der er udført");
        System.out.println(festival1.realiseretJobUdgift());
        System.out.println();

       Vagt v1 = Controller.createVagt(j1,2);
       Vagt v2 = Controller.createVagt(j2,5);

        System.out.println("Festivallens totale udgift af de jobs der er udført efter tilføjet vagter");
        System.out.println(festival1.realiseretJobUdgift());
        System.out.println();

        System.out.println("Tager et job og viser hvor mange timer der mangler at blive udført");
        System.out.println(j1.ikkeBesatteTimer());
        System.out.println();

        System.out.println(f1.ledigeTimer());

        f1.addVagt(v1);
        f1.addVagt(v2);

        System.out.println(f1.ledigeTimer());


    }
}
