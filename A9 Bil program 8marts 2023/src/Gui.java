import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Scanner;


public class Gui extends Application {

    @Override
    public void start(Stage stage) {
        Scanner input = new Scanner(System.in);

        //Størrelse, Farve og Mærke skal ændres i koden.
        //Mulige størrelser er "Stor" eller "Lille"
        //Mulige mærker er "Mercedes" eller "Volkswagen"
        Car testBil = new Car("Stor", 4, 200, 4, 4, Color.BLUE, "Volkswagen", true);

//        System.out.println("Indtast om den er Stor eller Lille:");
//        testBil.setStørrelse((input.nextLine());
        System.out.println("Du indtaster nu værdier for en "+ testBil.getStørrelse() + " bil med mærket " + testBil.getMærke());
        System.out.println("Hvis du ønsker at ændre størrelse, farve og mærke skal det gøres i koden");
        System.out.println("Indtast antal døre (Max = 4):");
        testBil.setDoors(input.nextInt());
        System.out.println("Indtast antal hjul (Anbefalet 2 Max er 4):");
        testBil.setWheels(input.nextInt());
        System.out.println("Indtast antal vinduer (Anbefalet 4 ved Stor bil og 2 ved Lille):");
        testBil.setWindows(input.nextInt());
        System.out.println("Indtast true eller false afhængig af om du vil have striber eller ej:");
        testBil.setRacerStriber(input.nextBoolean());

//        System.out.println("Indtast farve:");
//        car2.setColor(input.nextLine());

        Pane pane = new Pane();
//        Car car1 = new Car();
//        Car car2 = new Car("Lille", 4, 200, 4, 4, Color.GREEN, "Volksvagen");
//        Car car3 = new Car("Stor", 4, 200, 4, 4, Color.RED, "Mercedes");
        this.drawCar(pane, testBil);
        Scene scene = new Scene(pane, 500, 500);
        stage.setTitle("Shapes");
        stage.setScene(scene);
        stage.show();
    }

    public void drawCar(Pane pane, Car car) {

        int xAks = 50; //Variabel til at rykke det hele lidt længere til højre eller venstre på x-aksen.
        int yAks = 0; //Variabel til at rykke det hele lidt længere op eller ned på y-aksen.
        int xAksL = 100;

        //Alt inden i if "Stor" statement vil bestemme, hvordan en stor bil bliver tegnet.
        if (car.getStørrelse() == "Stor") {
            //BilDel 1-4 tegner selve karosseriet på bilen

            Rectangle udstødning = new Rectangle( -45 + xAksL, 190 + yAks, 100, 6);
            pane.getChildren().add(udstødning);
            udstødning.setFill(Color.BLACK);

            Rectangle bilDel1 = new Rectangle(100 + xAks, 100 + yAks, 200, 100);
            pane.getChildren().add(bilDel1);
            bilDel1.setFill(car.getColor());

            Rectangle bilDel2 = new Rectangle(20 + xAks, 150 + yAks, 360, 50);
            pane.getChildren().add(bilDel2);
            bilDel2.setFill(car.getColor());

            Polygon bilDel3 = new Polygon(50 + xAks, 150 + yAks, 100 + xAks, 100 + yAks, 100 + xAks, 150 + yAks);
            pane.getChildren().add(bilDel3);
            bilDel3.setFill(car.getColor());
            bilDel3.setStroke(car.getColor());

            Polygon bilDel4 = new Polygon(350 + xAks, 150 + yAks, 300 + xAks, 100 + yAks, 300 + xAks, 150 + yAks);
            pane.getChildren().add(bilDel4);
            bilDel4.setFill(car.getColor());
            bilDel4.setStroke(car.getColor());

            //BilVindue 1-2 tegner bilens forrude og bagrude.
            Polygon bilVindue1 = new Polygon(55 + xAks, 150 + yAks, 95 + xAks, 110 + yAks, 95 + xAks, 150 + yAks);
            pane.getChildren().add(bilVindue1);
            bilVindue1.setFill(Color.BLACK);

            Polygon bilVindue2 = new Polygon(345 + xAks, 150 + yAks, 305 + xAks, 110 + yAks, 305 + xAks, 150 + yAks);
            pane.getChildren().add(bilVindue2);
            bilVindue2.setFill(Color.BLACK);

            //Denne kodedel tegner fartstriber på bilen, hvis der bliver valgt true.
            if (car.getRacerStriber() == true) {
                Rectangle fartStriber = new Rectangle(20 + xAks, 155 + yAks, 360, 6);
                pane.getChildren().add(fartStriber);
                fartStriber.setFill(Color.BLACK);
                Rectangle fartStriber2 = new Rectangle(20 + xAks, 163 + yAks, 360, 6);
                pane.getChildren().add(fartStriber2);
                fartStriber2.setFill(Color.BLACK);
                Rectangle fartStriber3 = new Rectangle(20 + xAks, 171 + yAks, 360, 6);
                pane.getChildren().add(fartStriber3);
                fartStriber3.setFill(Color.BLACK);

            } else if (car.getRacerStriber() == false) {
                ;
            }


            //Denne kodedel bestemmer antal af døre der bliver tegnet på bilen og afstand imellem.
            int x2 = 155; //dør kordinater (x,y)
            int y2 = 150;
            int b1 = 40; //Bredde og højde på døre
            int h1 = 40;

            if (car.getDoors() == 2) {
                for (int i = 0; i < car.getDoors(); i++) {
                    Rectangle doors = new Rectangle(x2, y2, b1, h1);
                    pane.getChildren().add(doors);
                    doors.setFill(car.getColor());
                    doors.setStroke(Color.BLACK);
                    Line doorHandle1 = new Line(x2 + 5, y2 + 5, x2 + 20, y2 + 5);
                    pane.getChildren().add(doorHandle1);
                    doorHandle1.setStroke(Color.BLACK);
                    Line doorHandle2 = new Line(x2 + 20, y2 + 5, x2 + 20, y2 + 8);
                    pane.getChildren().add(doorHandle2);
                    doorHandle2.setStroke(Color.BLACK);
                    x2 = x2 + 150;
                }
            }
            else {
                    for (int i = 0; i < car.getDoors(); i++) {
                        Rectangle doors = new Rectangle(x2, y2, b1, h1);
                        pane.getChildren().add(doors);
                        doors.setFill(car.getColor());
                        doors.setStroke(Color.BLACK);
                        Line doorHandle1 = new Line(x2 + 5, y2 + 5, x2 + 20, y2 + 5);
                        pane.getChildren().add(doorHandle1);
                        doorHandle1.setStroke(Color.BLACK);
                        Line doorHandle2 = new Line(x2 + 20, y2 + 5, x2 + 20, y2 + 8);
                        pane.getChildren().add(doorHandle2);
                        doorHandle2.setStroke(Color.BLACK);
                        x2 = x2 + 50;
                    }
                }


            //Denne kodedel bestemmer antallet af hjul der bliver tegnet og afstand imelllem.

            int x = 140; //Hjulets kordinater (x,y)
            int y = 200;
            int r = 25; //Hjulets størrelse
            int rC = 15; //Hjulets størrelse

            if (car.getWheels() == 2) {
                for (int i = 0; i < car.getWheels(); i++) {
                    Circle hjul = new Circle(x, y, r);
                    pane.getChildren().add(hjul);
                    hjul.setFill(Color.BLACK);
                    Circle hjul1 = new Circle(x, y, rC);
                    pane.getChildren().add(hjul1);
                    hjul1.setFill(Color.WHITE);
                    x = x + 220;
                }
            } else {

                for (int i = 0; i < car.getWheels(); i++) {
                    Circle hjul = new Circle(x, y, r);
                    pane.getChildren().add(hjul);
                    hjul.setFill(Color.BLACK);
                    Circle hjul1 = new Circle(x, y, rC);
                    pane.getChildren().add(hjul1);
                    hjul1.setFill(Color.WHITE);
                    x = x + 75;
                }
            }
            //Denne kodedel bestemmer antallet af vinduer der bliver tegnet og afstand imellem.
            int x1 = 155; //vindue kordinater (x,y)
            int y1 = 110;
            int br = 40; //Bredde og højde på vindue
            int h = 40;

            if(car.getWindows() == 2){
            for (int i = 0; i < car.getWindows(); i++) {
                Rectangle vindue = new Rectangle(x1, y1, br, h);
                pane.getChildren().add(vindue);
                vindue.setFill(Color.BLACK);
                x1 = x1 + 150;
            }
            }
            else {
                for (int i = 0; i < car.getWindows(); i++) {
                    Rectangle vindue = new Rectangle(x1, y1, br, h);
                    pane.getChildren().add(vindue);
                    vindue.setFill(Color.BLACK);
                    x1 = x1 + 50;
                }
            }

            //Fronten af bilen

            Rectangle frontHjul1 = new Rectangle(90 + xAks, 370 + yAks, 25, 50);
            pane.getChildren().add(frontHjul1);
            frontHjul1.setFill(Color.BLACK);

            Rectangle frontHjul2 = new Rectangle(290 + xAks, 370 + yAks, 25, 50);
            pane.getChildren().add(frontHjul2);
            frontHjul2.setFill(Color.BLACK);

            Rectangle frontDel1 = new Rectangle(100 + xAks, 300 + yAks, 200, 100);
            pane.getChildren().add(frontDel1);
            frontDel1.setFill(car.getColor());

            Rectangle frontDel2 = new Rectangle(110 + xAks, 310 + yAks, 180, 40);
            pane.getChildren().add(frontDel2);
            frontDel2.setFill(Color.BLACK);

            if (car.getRacerStriber() == true) {
                Rectangle fartStriber = new Rectangle(100 + xAks, 355 + yAks, 200, 6);
                pane.getChildren().add(fartStriber);
                fartStriber.setFill(Color.BLACK);
                Rectangle fartStriber2 = new Rectangle(100 + xAks, 363 + yAks, 200, 6);
                pane.getChildren().add(fartStriber2);
                fartStriber2.setFill(Color.BLACK);
                Rectangle fartStriber3 = new Rectangle(100 + xAks, 371 + yAks, 200, 6);
                pane.getChildren().add(fartStriber3);
                fartStriber3.setFill(Color.BLACK);

            } else if (car.getRacerStriber() == false){
                ;}

            Rectangle frontlys1 = new Rectangle(105 + xAks, 370 + yAks, 25, 25);
            pane.getChildren().add(frontlys1);
            frontlys1.setFill(Color.YELLOW);
            frontlys1.setStroke(Color.BLACK);

            Rectangle frontlys2 = new Rectangle(270 + xAks, 370 + yAks, 25, 25);
            pane.getChildren().add(frontlys2);
            frontlys2.setFill(Color.YELLOW);
            frontlys2.setStroke(Color.BLACK);

            //Denne kodedel tegner mærket på bilen udfra, hvilket String navn der bliver angivet.
            int mX = -2; //Mulighed for at række på hele mærkes x og y koordinater.
            int mY = -10;

            if (car.getMærke() == "Volkswagen") {
                Circle volksC = new Circle(255 + mX, 387 + mY, 15);
                pane.getChildren().add(volksC);
                volksC.setFill(Color.BLUE);
                volksC.setStroke(Color.WHITE);
                Line ToyotaM1 = new Line(240 + mX, 380 + mY, 250 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM1);
                ToyotaM1.setStroke(Color.WHITE);
                Line ToyotaM2 = new Line(250 + mX, 380 + mY, 260 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM2);
                ToyotaM2.setStroke(Color.WHITE);
                Line ToyotaM3 = new Line(260 + mX, 380 + mY, 250 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM3);
                ToyotaM3.setStroke(Color.WHITE);
                Line ToyotaM4 = new Line(270 + mX, 380 + mY, 260 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM4);
                ToyotaM4.setStroke(Color.WHITE);


            } else if (car.getMærke() == "Mercedes") {
                Circle volksC = new Circle(255 + mX, 387 + mY, 15);
                pane.getChildren().add(volksC);
                volksC.setFill(Color.WHITE);
                volksC.setStroke(Color.BLACK);
                Line ToyotaM1 = new Line(255 + mX, 385 + mY, 245 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM1);
                ToyotaM1.setStroke(Color.BLACK);
                Line ToyotaM2 = new Line(255 + mX, 385 + mY, 265 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM2);
                ToyotaM2.setStroke(Color.BLACK);
                Line ToyotaM3 = new Line(255 + mX, 385 + mY, 255 + mX, 370 + mY);
                pane.getChildren().add(ToyotaM3);
                ToyotaM3.setStroke(Color.BLACK);
            }


        }
        //Alt i else if "Lille" bil bestemmer hvordan en lille bil bliver tegnet.

        else if (car.getStørrelse() == "Lille") {

            Rectangle udstødning = new Rectangle( 5 + xAksL, 190 + yAks, 100, 6);
            pane.getChildren().add(udstødning);
            udstødning.setFill(Color.BLACK);

            Rectangle BilDel1 = new Rectangle(100 + xAksL, 100 + yAks, 100, 100);
            pane.getChildren().add(BilDel1);
            BilDel1.setFill(car.getColor());

            Rectangle BilDel2 = new Rectangle(20 + xAksL, 150 + yAks, 260, 50);
            pane.getChildren().add(BilDel2);
            BilDel2.setFill(car.getColor());

            Polygon BilDel3 = new Polygon(50 + xAksL, 150 + yAks, 100 + xAksL, 100 + yAks, 100 + xAksL, 150 + yAks);
            pane.getChildren().add(BilDel3);
            BilDel3.setFill(car.getColor());
            BilDel3.setStroke(car.getColor());

            Polygon BilDel4 = new Polygon(250 + xAksL, 150 + yAks, 200 + xAksL, 100 + yAks, 200 + xAksL, 150 + yAks);
            pane.getChildren().add(BilDel4);
            BilDel4.setFill(car.getColor());
            BilDel4.setStroke(car.getColor());

            Polygon BilVindue1 = new Polygon(55 + xAksL, 150 + yAks, 95 + xAksL, 110 + yAks, 95 + xAksL, 150 + yAks);
            pane.getChildren().add(BilVindue1);
            BilVindue1.setFill(Color.BLACK);

            Polygon BilVindue2 = new Polygon(245 + xAksL, 150 + yAks, 205 + xAksL, 110 + yAks, 205 + xAksL, 150 + yAks);
            pane.getChildren().add(BilVindue2);
            BilVindue2.setFill(Color.BLACK);


            if (car.getRacerStriber() == true) {
                Rectangle fartStriber = new Rectangle(70 + xAks, 155 + yAks, 260, 6);
                pane.getChildren().add(fartStriber);
                fartStriber.setFill(Color.BLACK);
                Rectangle fartStriber2 = new Rectangle(70 + xAks, 163 + yAks, 260, 6);
                pane.getChildren().add(fartStriber2);
                fartStriber2.setFill(Color.BLACK);
                Rectangle fartStriber3 = new Rectangle(70 + xAks, 171 + yAks, 260, 6);
                pane.getChildren().add(fartStriber3);
                fartStriber3.setFill(Color.BLACK);

            } else if (car.getRacerStriber() == false) {
                ;
            }

            //Denne kodedel bestemmer antal af døre der bliver tegnet på bilen og afstand imellem
            int x2 = 155; //dør kordinater (x,y)
            int y2 = 150;
            int b1 = 40; //Bredde og højde på døre
            int h1 = 40;

            for (int i = 0; i < car.getDoors(); i++) {
                Rectangle doors = new Rectangle(x2, y2, b1, h1);
                pane.getChildren().add(doors);
                doors.setFill(car.getColor());
                doors.setStroke(Color.BLACK);
                Line doorHandle1 = new Line(x2 + 5, y2 + 5, x2 + 20, y2 + 5);
                pane.getChildren().add(doorHandle1);
                doorHandle1.setStroke(Color.BLACK);
                Line doorHandle2 = new Line(x2 + 20, y2 + 5, x2 + 20, y2 + 8);
                pane.getChildren().add(doorHandle2);
                doorHandle2.setStroke(Color.BLACK);
                x2 = x2 + 50;

            }

            //Denne kodedel bestemmer antallet af hjul der bliver tegnet og afstand imellem

            int x = 180; //Hjulets kordinater (x,y)
            int y = 200;
            int r = 20; //Hjulets størrelse
            int rC = 10; //Hjulets størrelse

            if (car.getWheels() == 2) {
                for (int i = 0; i < car.getWheels(); i++) {
                    Circle hjul = new Circle(x, y, r);
                    pane.getChildren().add(hjul);
                    hjul.setFill(Color.BLACK);
                    Circle hjul1 = new Circle(x, y, rC);
                    pane.getChildren().add(hjul1);
                    hjul1.setFill(Color.WHITE);
                    x = x + 140;
                }
            } else if (car.getWheels() > 2) {

                for (int i = 0; i < car.getWheels(); i++) {
                    Circle hjul = new Circle(x, y, r);
                    pane.getChildren().add(hjul);
                    hjul.setFill(Color.BLACK);
                    Circle hjul1 = new Circle(x, y, rC);
                    pane.getChildren().add(hjul1);
                    hjul1.setFill(Color.WHITE);
                    x = x + 48;
                }
            }
            //Denne kodedel bestemmer antallet af vinduer der bliver tegnet og afstand imellem.
            int x1 = 205; //vindue kordinater (x,y)
            int y1 = 110;
            int br = 40; //Bredde og højde på større vinduer
            int h = 40;
            int brS = 17; //Bredde og højde på små vinduer
            int hS = 40;

            if (car.getWindows() > 2) {
                for (int i = 0; i < car.getWindows(); i++) {
                    Rectangle vindue = new Rectangle(x1, y1, brS, hS);
                    pane.getChildren().add(vindue);
                    vindue.setFill(Color.BLACK);
                    x1 = x1 + 25;
                }
            } else {
                for (int i = 0; i < car.getWindows(); i++) {
                    Rectangle vindue = new Rectangle(x1, y1, br, h);
                    pane.getChildren().add(vindue);
                    vindue.setFill(Color.BLACK);
                    x1 = x1 + 50;
                }
            }

            //Fronten af bilen

            Rectangle frontHjul1 = new Rectangle(90 + xAks, 370 + yAks, 25, 40);
            pane.getChildren().add(frontHjul1);
            frontHjul1.setFill(Color.BLACK);

            Rectangle frontHjul2 = new Rectangle(255 + xAks, 370 + yAks, 25, 40);
            pane.getChildren().add(frontHjul2);
            frontHjul2.setFill(Color.BLACK);

            Rectangle frontDel1 = new Rectangle(100 + xAks, 300 + yAks, 170, 100);
            pane.getChildren().add(frontDel1);
            frontDel1.setFill(car.getColor());

            Rectangle frontDel2 = new Rectangle(110 + xAks, 310 + yAks, 150, 40);
            pane.getChildren().add(frontDel2);
            frontDel2.setFill(Color.BLACK);

            if (car.getRacerStriber() == true) {
                Rectangle fartStriber = new Rectangle(100 + xAks, 355 + yAks, 170, 6);
                pane.getChildren().add(fartStriber);
                fartStriber.setFill(Color.BLACK);
                Rectangle fartStriber2 = new Rectangle(100 + xAks, 363 + yAks, 170, 6);
                pane.getChildren().add(fartStriber2);
                fartStriber2.setFill(Color.BLACK);
                Rectangle fartStriber3 = new Rectangle(100 + xAks, 371 + yAks, 170, 6);
                pane.getChildren().add(fartStriber3);
                fartStriber3.setFill(Color.BLACK);

            } else if (car.getRacerStriber() == false){
                ;}

            Rectangle frontlys1 = new Rectangle(105 + xAks, 370 + yAks, 25, 25);
            pane.getChildren().add(frontlys1);
            frontlys1.setFill(Color.YELLOW);
            frontlys1.setStroke(Color.BLACK);

            Rectangle frontlys2 = new Rectangle(240 + xAks, 370 + yAks, 25, 25);
            pane.getChildren().add(frontlys2);
            frontlys2.setFill(Color.YELLOW);
            frontlys2.setStroke(Color.BLACK);

            //Denne kodedel tegner mærket udfra den angivet String navn.
            int mX = -17; //Mulighed for at række på hele mærkes x og y koordinater.
            int mY = -10;

            if (car.getMærke() == "Volkswagen") {
                Circle volksC = new Circle(255 + mX, 387 + mY, 15);
                pane.getChildren().add(volksC);
                volksC.setFill(Color.BLUE);
                volksC.setStroke(Color.WHITE);
                Line ToyotaM1 = new Line(240 + mX, 380 + mY, 250 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM1);
                ToyotaM1.setStroke(Color.WHITE);
                Line ToyotaM2 = new Line(250 + mX, 380 + mY, 260 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM2);
                ToyotaM2.setStroke(Color.WHITE);
                Line ToyotaM3 = new Line(260 + mX, 380 + mY, 250 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM3);
                ToyotaM3.setStroke(Color.WHITE);
                Line ToyotaM4 = new Line(270 + mX, 380 + mY, 260 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM4);
                ToyotaM4.setStroke(Color.WHITE);


            } else if (car.getMærke() == "Mercedes") {
                Circle volksC = new Circle(255 + mX, 387 + mY, 15);
                pane.getChildren().add(volksC);
                volksC.setFill(Color.WHITE);
                volksC.setStroke(Color.BLACK);
                Line ToyotaM1 = new Line(255 + mX, 385 + mY, 245 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM1);
                ToyotaM1.setStroke(Color.BLACK);
                Line ToyotaM2 = new Line(255 + mX, 385 + mY, 265 + mX, 400 + mY);
                pane.getChildren().add(ToyotaM2);
                ToyotaM2.setStroke(Color.BLACK);
                Line ToyotaM3 = new Line(255 + mX, 385 + mY, 255 + mX, 370 + mY);
                pane.getChildren().add(ToyotaM3);
                ToyotaM3.setStroke(Color.BLACK);
            }

        }

    }
}
