package storage;

import applikation.model.*;

import java.util.ArrayList;

public class Storage {
    private static Storage storage;
    private ArrayList<Batch> listBatch;
    private ArrayList<Destillat> listDestillat;
    private ArrayList<Fad> listFad;
    private ArrayList<Medarbejder> listMedarbejders;
    private ArrayList<Påfyldning> listPåfyldning;
    private ArrayList<Whisky> listWhiskys;
    private ArrayList<Lager> listLager;

    protected Storage(){
        listBatch = new ArrayList<>();
        listDestillat = new ArrayList<>();
        listFad = new ArrayList<>();
        listMedarbejders = new ArrayList<>();
        listPåfyldning = new ArrayList<>();
        listWhiskys = new ArrayList<>();
        listLager = new ArrayList<>();
    }

    public static Storage getStorage(){
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public ArrayList<Batch> getBatchs(){
        return new ArrayList<>(listBatch);
    }

    public void addBatch(Batch batch){
        if(!listBatch.contains(batch)){
            listBatch.add(batch);
        }
    }

    public void removeBatch(Batch batch){
        if(listBatch.contains(batch)){
            listBatch.remove(batch);
        }
    }

    public ArrayList<Destillat> getDestillat(){
        return new ArrayList<>(listDestillat);
    }

    public void addDestillat(Destillat destillat){
        if(!listDestillat.contains(destillat)){
            listDestillat.add(destillat);
        }
    }

    public void removeDestillat(Destillat destillat){
        if(listDestillat.contains(destillat)){
            listDestillat.remove(destillat);
        }
    }

    public ArrayList<Fad> getFad(){
        return new ArrayList<>(listFad);
    }

    public void addFad(Fad fad){
        if(!listFad.contains(fad)){
            listFad.add(fad);
        }
    }

    public void removeFad(Fad fad){
        if(listFad.contains(fad)){
            listFad.remove(fad);
        }
    }

    public ArrayList<Medarbejder> getMedarbejder(){
        return new ArrayList<>(listMedarbejders);
    }

    public void addMedarbejder(Medarbejder medarbejder){
        if(!listMedarbejders.contains(medarbejder)){
            listMedarbejders.add(medarbejder);
        }
    }

    public void removeMedarbejder(Medarbejder medarbejder){
        if(listMedarbejders.contains(medarbejder)){
            listMedarbejders.remove(medarbejder);
        }
    }

    public ArrayList<Påfyldning> getPåfyldninger(){
        return new ArrayList<>(listPåfyldning);
    }

    public void addPåfyldning(Påfyldning påfyldning){
        if(!listPåfyldning.contains(påfyldning)){
            listPåfyldning.add(påfyldning);
        }
    }

    public void removePåfyldning(Påfyldning påfyldning){
        if(listPåfyldning.contains(påfyldning)){
            listPåfyldning.remove(påfyldning);
        }
    }

    public ArrayList<Whisky> getWhiskys() {
        return new ArrayList<>(listWhiskys);
    }

    public void addWhisky(Whisky whisky) {
        if(!listWhiskys.contains(whisky)){
            listWhiskys.add(whisky);
        }
    }

    public ArrayList<Lager> getLagers(){
        return new ArrayList<>(listLager);
    }

    public void addLager(Lager lager){
        if(!listLager.contains(lager)){
            listLager.add(lager);
        }
    }

    public void removeLager(Lager lager){
        if(listLager.contains(lager)){
            listLager.remove(lager);
        }

    }


}
