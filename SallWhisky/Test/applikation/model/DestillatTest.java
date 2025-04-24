package applikation.model;

import applikation.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DestillatTest {
    private Batch batch1;

    private DestillatComponent destillat1;
    private DestillatComponent destillat2;
    private DestillatComponent destillat3;

    private DestillatComponent påfyldning1;
    private DestillatComponent påfyldning2;
    private DestillatComponent påfyldning3;

    private Fad fad1;
    private Fad fad2;
    private Fad fad3;

    private Lager lager;




    @BeforeEach
    void setUp(){

        batch1 = Controller.createBatch("NM1",
                LocalDate.of(2023,10,11),LocalDate.of(2023,10,12),
                60,"mal1","Evergreen",150,true,"Hey",
                Controller.createMedarbejder("Hans",555));

        påfyldning1 = Controller.createPåfyldning(30, batch1);
        påfyldning2 = Controller.createPåfyldning(30, batch1);
        påfyldning3 = Controller.createPåfyldning(40, batch1);



        lager = Controller.createLager("Sal1","Sal vejs whisky",3,3);

        fad1 = Controller.createFad(1,"bourbon",130,lager);
        fad2 = Controller.createFad(2,"Sherry",130,lager);
        fad3 = Controller.createFad(3,"Bourbon",130,lager);

        //ACT




    }

    @Test
    void constructorTilOpretteDestillatFraPåfyldning_TC1() {
        //Arrange
        ArrayList<DestillatComponent> destillatComponents = new ArrayList<>();
        destillatComponents.add(påfyldning1);
        destillatComponents.add(påfyldning2);
        destillatComponents.add(påfyldning3);


        //ACT
        destillat1 = new Destillat( fad1,LocalDate.of(2026,10,11),
                70,destillatComponents);

        int forventetAntalLiter = påfyldning1.getAntalLiter() +
                påfyldning2.getAntalLiter()+ påfyldning3.getAntalLiter();


        //Assert
        assertEquals(forventetAntalLiter, destillat1.getAntalLiter());

        for(int i = 0; i < destillatComponents.size(); i++){
            assertEquals(destillatComponents.get(i),((Destillat)destillat1).getDestillatComponents().get(i));
        }
    }



    @Test
    void constructorTilOpretteDestillatFraDestillat_TC1() {
        //Arrange
        ArrayList<DestillatComponent> destillatComponentsPåfyldning = new ArrayList<>();
        ArrayList<DestillatComponent> destillatComponentsDestillat = new ArrayList<>();

        destillatComponentsPåfyldning.add(påfyldning1);
        destillatComponentsPåfyldning.add(påfyldning2);
        destillatComponentsPåfyldning.add(påfyldning3);


        destillat1 = new Destillat(fad1,LocalDate.of(2026,10,11),70 ,destillatComponentsPåfyldning);
        destillat2 = new Destillat(fad2,LocalDate.of(2026,10,11),
                70, destillatComponentsPåfyldning);

        destillatComponentsDestillat.add(destillat1);
        destillatComponentsDestillat.add(destillat2);

        LocalDate påfyldningsDato = LocalDate.of(2026,10,11);
        int alkoholProcent = 70;
        int antalLiter = 50;


        //ACT
       destillat3 =  new Destillat(fad3,påfyldningsDato,
                alkoholProcent, destillatComponentsDestillat,antalLiter);






        //Assert
        assertEquals(påfyldningsDato,destillat3.getPåfyldningsDato());

        assertEquals(alkoholProcent,destillat3.getAlkoholProcent());

        assertEquals(fad3,((Destillat) destillat3).getFad());

        for(int i = 0; i < destillatComponentsDestillat.size(); i++){
            assertEquals(destillatComponentsDestillat.get(i),
                    ((Destillat) destillat3).getDestillatComponents().get(i));
        }

        assertEquals(antalLiter,destillat3.getAntalLiter());

    }

    @Test
    void setFad_TC1() {
        //Arrange
        ArrayList<DestillatComponent> destillatComponents = new ArrayList<>();
        destillatComponents.add(påfyldning1);
        destillatComponents.add(påfyldning2);
        destillatComponents.add(påfyldning3);

        destillat1 = new Destillat(fad1,LocalDate.of(2026,10,11),
                70, destillatComponents);

        Fad aktuelleFad = new Fad(2222,"Sherry",150,lager);
        //ACT
        ((Destillat) destillat1).setFad(aktuelleFad);

        //ASSERT
        assertEquals(aktuelleFad,((Destillat) destillat1).getFad());

    }









}