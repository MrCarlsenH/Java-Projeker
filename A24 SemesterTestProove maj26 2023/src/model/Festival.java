package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Festival {

    private String navn;
    private LocalDate fradato;
    private LocalDate tildato;
    private ArrayList<Job> jobs = new ArrayList<>();

    public Festival(String navn, LocalDate fradato, LocalDate tildato) {
        this.navn = navn;
        this.fradato = fradato;
        this.tildato = tildato;
    }

    public Job createJob(String kode, String beskrivelse, LocalDate dato, int timeHonorar, int antalTimer){
        Job job = new Job(kode, beskrivelse, dato, timeHonorar, antalTimer);
        jobs.add(job);
        return job;
    }

    public ArrayList<Job> getJobs() {
        return new ArrayList<>(jobs);
    }

    public String getNavn() {
        return navn;
    }

    public LocalDate getFradato() {
        return fradato;
    }

    public LocalDate getTildato() {
        return tildato;
    }

    public int budgetteretJobUdgift(){
        int sumAfjob = 0;
        int sumtotal = 0;
        for (int i = 0; i < jobs.size(); i++) {
           sumAfjob = jobs.get(i).getAntalTimer() * jobs.get(i).getTimeHonorar();
           sumtotal += sumAfjob;
        }
        return sumtotal;
    }

    public int realiseretJobUdgift(){
        int sumAfVagt = 0;
        int sumAfjobs = 0;
        for (int i = 0; i < jobs.size(); i++) {
            for (int j = 0; j < jobs.get(i).getVagter().size(); j++) {
                sumAfVagt = jobs.get(i).getVagter().get(j).getTimer() * jobs.get(i).getTimeHonorar();
            }
           sumAfjobs += sumAfVagt;
            sumAfVagt = 0;
            }
        return sumAfjobs;
        }

    }
