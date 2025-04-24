package application.modules;

import java.time.LocalDate;
import java.util.ArrayList;

public class Sælger {

    private String navn;
    private int studiekortNummer;
    private String mobil;
    private ArrayList<Salgsannonce> salgsannoncer = new ArrayList<>();

    public Sælger(String navn, int studiekortNummer, String mobil) {
        this.navn = navn;
        this.studiekortNummer = studiekortNummer;
        this.mobil = mobil;
    }

    public Salgsannonce createSalgsannonce(boolean aktiv, LocalDate udløbsdato) {
        Salgsannonce salgsannonce = new Salgsannonce(aktiv, udløbsdato, this);
        return salgsannonce;
    }

    public void removeSalgsannonce(Salgsannonce salgsannonce) {
        if (salgsannoncer.contains(salgsannonce)) {
            salgsannoncer.remove(salgsannonce);
        }
    }

    //TODO S3
    public ArrayList<Vare> alleIkkesolgteVarerIKategori(Varekategori kategori) {
        ArrayList<Vare> ikkeSolgte = new ArrayList<>();
        for (int i = 0; i < salgsannoncer.size(); i++) {
            for (int j = 0; j < salgsannoncer.get(i).getVarer().size(); j++) {
                if (salgsannoncer.get(i).getVarer().get(j).getKategori().equals(kategori)) {
                    ikkeSolgte.add(salgsannoncer.get(i).getVarer().get(j));
                }
            }
        }
        return ikkeSolgte;
    }

    public String getNavn() {
        return navn;
    }

    public int getStudiekortNummer() {
        return studiekortNummer;
    }

    public String getMobil() {
        return mobil;
    }

    @Override
    public String toString() {
        return "Navn: " + navn + " Studiekortnummer: " + studiekortNummer + " Telefonnummer: " + mobil;
    }
}
