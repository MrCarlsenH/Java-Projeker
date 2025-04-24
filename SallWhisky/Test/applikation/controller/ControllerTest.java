package applikation.controller;

import applikation.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerTest {

    @Test
    void createBatch_TC1() {
        //Arrange
        Storage storage = mock(Storage.class);


        doNothing().when(storage).addBatch(any(Batch.class));

        String spiritBatchNr = "20000";
        LocalDate startDato = LocalDate.of(2002,10, 10);
        LocalDate slutDato = LocalDate.of(2005,10,11);
        int alkoholProcent = 10;
        String malBatch = "MAL42";
        String kornsort = "CORN";
        int antalLiter = 100;
        boolean rygemateriale = true;
        String kommentar = "Dette er en test af controller";
        Medarbejder medarbejder = new Medarbejder("Hans",1111);










        //ACT
        Batch aktuelleBatch = Controller.createBatch(spiritBatchNr,startDato ,slutDato,alkoholProcent,malBatch,
                kornsort,antalLiter,true,
                kommentar,medarbejder);

        Batch forventetBatch = new Batch(spiritBatchNr,startDato ,slutDato,alkoholProcent,malBatch,
                kornsort,antalLiter,true,
                kommentar,medarbejder);

        //ASSERT

        assertEquals(forventetBatch.getNewSpiritBatchNr(),aktuelleBatch.getNewSpiritBatchNr());
        assertEquals(forventetBatch.getStartDato(),aktuelleBatch.getStartDato());
        assertEquals(forventetBatch.getSlutDato(),aktuelleBatch.getSlutDato());
        assertEquals(forventetBatch.getAlkoholProcent(),aktuelleBatch.getAlkoholProcent());
        assertEquals(forventetBatch.getMalBatch(),aktuelleBatch.getMalBatch());
        assertEquals(forventetBatch.getKornsort(),aktuelleBatch.getKornsort());
        assertEquals(forventetBatch.getKommentar(),aktuelleBatch.getKommentar());
        assertEquals(forventetBatch.isRygemateriale(),aktuelleBatch.isRygemateriale());
        assertEquals(forventetBatch.isRygemateriale(),aktuelleBatch.isRygemateriale());
        assertEquals(forventetBatch.getMedarbejder(),aktuelleBatch.getMedarbejder());

    }


    @Test
    void createPåfyldning_TC1() {
        //Arrange
        Batch batch = Controller.createBatch("20000", LocalDate.of(2002,10,
                        10),LocalDate.of(2005,10,11),10,"Hvad er det",
                "CORN",100,true,
                "Dette er en test af controller",new Medarbejder("hans",1111));

        int antalLitter = 30;

        //ACT
        Påfyldning aktuelPåfyldning = Controller.createPåfyldning(antalLitter,batch);

        //Assert
        assertEquals(antalLitter,aktuelPåfyldning.getAntalLiter());
        assertEquals(batch,aktuelPåfyldning.getBatch());

    }

    @Test
    void createPåfyldning_TC2() {
//        try {

            //Arrange
            Batch batch = Controller.createBatch("20000", LocalDate.of(2002, 10,
                            10), LocalDate.of(2005, 10, 11), 10, "Hvad er det",
                    "CORN", 100, true,
                    "Dette er en test af controller", new Medarbejder("hans", 1111));

            int antalLitter = 0;


            //ACT & Assert
            assertThrows(RuntimeException.class,()->{
                Controller.createPåfyldning(antalLitter,batch);
            });

    }

    @Test
    void getTilgængligeBatchs_TC1() {
//
        //Arrange
        boolean ikkeRygeMateriale = false;
        boolean erRygeMateriale = true;
        String kornsort1 = "Irina";
        String kornsort2 = "Evergreen";

        Medarbejder medarbejder = Controller.createMedarbejder("Hans",100);

        Batch batch1 = Controller.createBatch("NM1",
                LocalDate.of(2023,12,1),LocalDate.of(2023,12,3),
                50,"MAL41",kornsort1,1000,erRygeMateriale,
                "Et god produkt",medarbejder);

        Batch batch2 = Controller.createBatch("NM3",
                LocalDate.of(2023,12,1),LocalDate.of(2023,12,3),
                50,"MAL40",kornsort1,1000,erRygeMateriale,
                "Et god produkt",medarbejder);

        Batch batch3 = Controller.createBatch("NM2",
                LocalDate.of(2023,12,1),LocalDate.of(2023,12,3),
                50,"MAL40",kornsort2,1000,ikkeRygeMateriale,
                "Et god produkt",medarbejder);

        //ACT
        ArrayList<Batch> aktuelleBatches = Controller.getTilgængligeBatchs();
        Storage storage = Storage.getStorage();
        int size = storage.getBatchs().size();


        //ASSERT
        assertEquals(size,aktuelleBatches.size());
    }

    @Test
    void getTilgængligeBatchs_TC2() {
//
        //Arrange
        boolean ikkeRygeMateriale = false;
        boolean erRygeMateriale = true;
        String kornsort1 = "Irina";
        String kornsort2 = "Evergreen";

        Medarbejder medarbejder = Controller.createMedarbejder("Hans",100);

        Batch batch1 = Controller.createBatch("NM1",
                LocalDate.of(2023,12,1),LocalDate.of(2023,12,3),
                50,"MAL41",kornsort1,1000,erRygeMateriale,
                "Et god produkt",medarbejder);

        Batch batch2 = Controller.createBatch("NM3",
                LocalDate.of(2023,12,1),LocalDate.of(2023,12,3),
                50,"MAL40",kornsort1,1000,erRygeMateriale,
                "Et god produkt",medarbejder);

        Batch batch3 = Controller.createBatch("NM2",
                LocalDate.of(2023,12,1),LocalDate.of(2023,12,3),
                50,"MAL40",kornsort2,1000,ikkeRygeMateriale,
                "Et god produkt",medarbejder);

        //ACT
        ArrayList<Batch> aktuelleBatches = Controller.getTilgængligeBatchs();
        Storage storage = Storage.getStorage();
        int size = storage.getBatchs().size();

        //ASSERT
       assertEquals(size,aktuelleBatches.size());
    }




    @Test
    void getTilgængligeBatchs_TC3() {
//
        //Arrange
        boolean ikkeRygeMateriale = false;
        boolean erRygeMateriale = true;
        String kornsort1 = "Irina";
        String kornsort2 = "Evergreen";

        Medarbejder medarbejder = Controller.createMedarbejder("Hans",100);

        Batch batch1 = Controller.createBatch("NM1",
                LocalDate.of(2023,12,1),LocalDate.of(2023,12,3),
                50,"MAL41",kornsort1,0,erRygeMateriale,
                "Et god produkt",medarbejder);

        Batch batch2 = Controller.createBatch("NM3",
                LocalDate.of(2023,12,1),LocalDate.of(2023,12,3),
                50,"MAL40",kornsort1,0,erRygeMateriale,
                "Et god produkt",medarbejder);

        Batch batch3 = Controller.createBatch("NM2",
                LocalDate.of(2023,12,1),LocalDate.of(2023,12,3),
                50,"MAL40",kornsort2,0,ikkeRygeMateriale,
                "Et god produkt",medarbejder);

        //ACT
        ArrayList<Batch> aktuelleBatches = Controller.getTilgængligeBatchs();
        Storage storage = Storage.getStorage();
        int size = storage.getBatchs().size();

        //ASSERT
        assertNotEquals(size,aktuelleBatches.size());
    }





    @Test
    void findLedigFad_TC1() {

        //Arrange
        DestillatComponent påfyldning = mock(Påfyldning.class);
        ArrayList<DestillatComponent> påfyldnings = new ArrayList<>();
        påfyldnings.add(påfyldning);
        when(påfyldning.getPåfyldningsDato()).thenReturn(LocalDate.of(2022,3,3));
        Lager lager = new Lager("Lars Lager","Sal whisky lager",10,10);


        Fad fad1 = Controller.createFad(1,"Bourbon",135,lager);
        Fad fad2 = Controller.createFad(2,"Bourbon",135,lager);
        Fad fad3 = Controller.createFad(3,"Sheery",135,lager);



        Destillat destillat = Controller.createDestillat(LocalDate.of(2020,10,11),60,fad1,påfyldnings, 40);

        //ACT
        ArrayList<Fad> aktuelleFade = Controller.findLedigFad("Bourbon");

        //ASSERT
        assertTrue(aktuelleFade.size() == 1);

    }

    @Test
    void isWhiskyReady_True() {

        //Arrange
        Fad fad = mock(Fad.class);
        DestillatComponent destilat1 = mock(Destillat.class);
        DestillatComponent destilat2 = mock(Destillat.class);
        when(destilat1.getPåfyldningsDato()).thenReturn(LocalDate.of(2000,1,1));
        when(destilat2.getPåfyldningsDato()).thenReturn(LocalDate.of(2009,1,1));
        ArrayList<DestillatComponent> list = new ArrayList<>();
        list.add(destilat1);
        list.add(destilat2);

        //ACT
        Destillat newDestillat = new Destillat(fad,LocalDate.of(2023,2,1),60,list, 40);
        //ASSERT
        assertTrue(newDestillat.isWhiskyReady());
    }
    @Test
    void isWhiskyReady_False() {
        //Arrange
        Fad fad = mock(Fad.class);
        DestillatComponent destilat1 = mock(Destillat.class);
        DestillatComponent destilat2 = mock(Destillat.class);
        when(destilat1.getPåfyldningsDato()).thenReturn(LocalDate.of(2023,1,1));
        when(destilat2.getPåfyldningsDato()).thenReturn(LocalDate.of(2022,1,1));
        ArrayList<DestillatComponent> list = new ArrayList<>();
        list.add(destilat1);
        list.add(destilat2);

        //ACT
        Destillat newDestillat = new Destillat(fad,LocalDate.of(2023,2,1), 60,list, 40);
        //ASSERT
        assertFalse(newDestillat.isWhiskyReady());
    }

    @Test
    void isWhiskyReady_False_Paafylding_i_Destillat() {
        //Arrange
        Fad fad = mock(Fad.class);
        DestillatComponent destilat1 = mock(Destillat.class);
        DestillatComponent paafyldning = mock(Påfyldning.class);
        when(destilat1.getPåfyldningsDato()).thenReturn(LocalDate.of(2000,1,1));
        when(paafyldning.getPåfyldningsDato()).thenReturn(LocalDate.of(2009,1,1));
        ArrayList<DestillatComponent> list = new ArrayList<>();
        list.add(destilat1);
        list.add(paafyldning);

        //ACT
        Destillat newDestillat = new Destillat(fad,LocalDate.of(2023,1,1),60,list, 40);
        //ASSERT
        assertFalse(newDestillat.isWhiskyReady());
    }
}