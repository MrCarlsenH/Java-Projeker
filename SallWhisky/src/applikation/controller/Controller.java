package applikation.controller;

import applikation.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Controller  {
    private  static Storage storage = Storage.getStorage();

    public static void initStorage() {
        Medarbejder m1 = createMedarbejder("Minik",001);
        Medarbejder m2 = createMedarbejder("Nikolaj", 002);
        Medarbejder m3 = createMedarbejder("Emil", 003);

        Batch b1 = createBatch("SB001", LocalDate.of(2023,8,8), LocalDate.of(2023,9,8),40,"MB001","Evergreen",500,true,"",m1);
        Batch b2 = createBatch("SB002", LocalDate.of(2023,8,8), LocalDate.of(2023,9,8),70,"MB002","Evergreen",500,false,"",m2);
        Batch b3 = createBatch("SB003", LocalDate.of(2023,8,8), LocalDate.of(2023,9,8),60,"MB003","Evergreen",500,true,"",m3);
        Lager lager = createLager("Lars Lager","Sal whisky lager",10,10);
        //Til Destillat1
        Fad f1 = createFad(1,"Bourbon", 120,lager);
        //Til Destillat2
        Fad f2 = createFad(2,"Bourbon", 120,lager);
        //Til Destillat3
        Fad f3 = createFad(3,"Sherry", 120,lager);

        Fad f4 = createFad(4,"Bourbon", 120,lager);
        Fad f5 = createFad(5,"Bourbon", 120,lager);
        Fad f6 = createFad(6,"Sherry", 120,lager);
        Fad f7 = createFad(7,"Bourbon", 120,lager);



        //Til Destillat1
        DestillatComponent påfyldningTilDestillat1 = createPåfyldning(50,b1);
        DestillatComponent påfyldningTilDestillat2 = createPåfyldning(50,b2);
        DestillatComponent påfyldningTilDestillat3 = createPåfyldning(50,b3);

        //Til Destillat2
        DestillatComponent påfyldningTilDestillat4 = createPåfyldning(50,b1);
        DestillatComponent påfyldningTilDestillat5 = createPåfyldning(50,b2);
        DestillatComponent påfyldningTilDestillat6 = createPåfyldning(50,b3);

        //Til Destillat3
        DestillatComponent påfyldningTilDestillat7 = createPåfyldning(50,b1);
        DestillatComponent påfyldningTilDestillat8 = createPåfyldning(50,b2);
        DestillatComponent påfyldningTilDestillat9 = createPåfyldning(50,b3);



        ArrayList<DestillatComponent> påfyldningerDestillat1 = new ArrayList<>();
        ArrayList<DestillatComponent> påfyldningerDestillat2 = new ArrayList<>();
        ArrayList<DestillatComponent> påfyldningerDestillat3 = new ArrayList<>();

        påfyldningerDestillat1.add(påfyldningTilDestillat1);
        påfyldningerDestillat1.add(påfyldningTilDestillat2);
        påfyldningerDestillat1.add(påfyldningTilDestillat3);

        påfyldningerDestillat2.add(påfyldningTilDestillat1);
        påfyldningerDestillat2.add(påfyldningTilDestillat2);
        påfyldningerDestillat2.add(påfyldningTilDestillat3);

        påfyldningerDestillat3.add(påfyldningTilDestillat1);
        påfyldningerDestillat3.add(påfyldningTilDestillat2);
        påfyldningerDestillat3.add(påfyldningTilDestillat3);

        DestillatComponent destillat1  = Controller.createDestillat(LocalDate.of(2020,10,12),45,f1,påfyldningerDestillat1, 40);
        DestillatComponent destillat2  = Controller.createDestillat(LocalDate.of(2019,9,5),45,f2,påfyldningerDestillat2, 40);
        DestillatComponent destillat3  = Controller.createDestillat(LocalDate.of(2023,7,23),45,f3,påfyldningerDestillat3, 40);

        ArrayList<DestillatComponent> destillatComponents = new ArrayList<>();
        destillatComponents.add(destillat1);
        destillatComponents.add(destillat2);
        DestillatComponent destillat4 =  Controller.createDestillat(LocalDate.of(2020,10,12),45,f4,destillatComponents, 40);


        ArrayList<DestillatComponent> samlingAfDestillater = new ArrayList<>();

        samlingAfDestillater.add(destillat1);
        samlingAfDestillater.add(destillat2);
        samlingAfDestillater.add(destillat3);

        DestillatComponent samletDestillat  =  Controller.createDestillat(LocalDate.of(2023,9,9),45,f4,samlingAfDestillater, 40);
        Whisky w1 = Controller.createWhisky("Old Smokey McSmokeface",samletDestillat,false,LocalDate.now(),30);


        System.out.println(Arrays.toString(((Destillat) samletDestillat).getFadPlads()));

        System.out.println(samletDestillat.historik());
    }

    public static Batch createBatch(String newSpiritBatchNr, LocalDate startDato,
                                    LocalDate slutDato, int alkoholProcent,
                              String malBatch, String kornSort, int antalLiter,
                                    boolean rygemateriale, String kommentar,
                              Medarbejder medarbejder) {

        Batch batch = new Batch(newSpiritBatchNr,startDato,slutDato,alkoholProcent,
                malBatch,kornSort,antalLiter,rygemateriale,kommentar,medarbejder );
        storage.addBatch(batch);
        return batch;
    }


    public static ArrayList<Batch> getTilgængligeBatchs(){
        ArrayList<Batch> tilgængligeBatches = new ArrayList<>();
        for(Batch batch : storage.getBatchs()){
            if(batch.beregnResterendeMængde() > 0){
                tilgængligeBatches.add(batch);
            }
        }
        return tilgængligeBatches;
    }

    public static Fad createFad(int fadNr, String fadType, int størrelse, Lager lager){
        Fad fad = new Fad(fadNr,fadType,størrelse,lager);
        storage.addFad(fad);
        try {
            fad.setLagerFørsteLedig(lager);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fad;
    }

    public static Fad createFad(int fadNr, String fadType, int størrelse, Lager lager,int row,int col) throws Exception{
        Fad fad = new Fad(fadNr,fadType,størrelse,lager);
        storage.addFad(fad);
        fad.setLagerBestemtPlads(lager,row,col);
        return fad;
    }


    public static ArrayList<Fad> findLedigFad(String fadType){
        ArrayList<Fad> ledigeFad = new ArrayList<>();
        for(Fad fad : storage.getFad()){
            if(fad.isLedig() && fad.getFadType().equals(fadType)){
                ledigeFad.add(fad);
            }
        }
        return ledigeFad;
    }

    public  static Destillat createDestillat(LocalDate påfyldningsDato, int alkoholProcent,
                                             Fad fad, ArrayList<DestillatComponent> destillatComponents, int antalLiter){

        Destillat destillat = new Destillat(fad, påfyldningsDato,alkoholProcent,destillatComponents, antalLiter);
        storage.addDestillat(destillat);
        return destillat;
    }

    public  static Destillat createDestillat(LocalDate påfyldningsDato, int alkoholProcent,
                                             Fad fad, ArrayList<DestillatComponent> destillatComponents){
        Destillat destillat = new Destillat(fad, påfyldningsDato,alkoholProcent,destillatComponents);
        storage.addDestillat(destillat);
        return destillat;
    }

    public static Påfyldning createPåfyldning(int antalLiter,Batch batch){
        if(antalLiter > 0) {
            Påfyldning påfyldning = new Påfyldning(antalLiter, batch);
            storage.addPåfyldning(påfyldning);
            return påfyldning;
        }
        else {
            throw new RuntimeException("Antal Liter må ikke være mindre end eller lig med 0");
        }
    }

    public static Whisky createWhisky(String navn, DestillatComponent destillatComponent, boolean tilsatVand, LocalDate tapningsdato, int antalLiter) {
        Whisky whisky = new Whisky(navn, tilsatVand, tapningsdato, antalLiter, destillatComponent);
        storage.addWhisky(whisky);
        return whisky;
    }

    public static Medarbejder createMedarbejder(String navn, int medNr){
        Medarbejder medarbejder = new Medarbejder(navn,medNr);
        storage.addMedarbejder(medarbejder);
        return medarbejder;
    }

    public static Lager createLager(String navn, String adresse,  int antalRow,  int antalCol){
        Lager lager = new Lager(navn,adresse,antalRow,antalCol);
        storage.addLager(lager);
        return lager;

    }


    public static int udregnAlkoholProcent (ArrayList<Batch> batches, ArrayList<Integer> antalLiter) {
        int procent = 0;
        int mængde = 0;
        for (int i = 0; i < batches.size(); i++) {
            procent += batches.get(i).getAlkoholProcent() * antalLiter.get(i);
        }
        for (int i : antalLiter) {
            mængde += i;
        }
        return procent / mængde;
    }



    public static Destillat getFadIndhold(Fad fad){
        Destillat  destillat = null;

        for (Destillat d : storage.getDestillat()) {
            if (d.getFad().getFadNr() == fad.getFadNr()) {
                destillat = d;
            }
        }
        return destillat;
    }
}
