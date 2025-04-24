package application.modules;


public class Vare {

    private String navn;
    private int udbudspris;
    private boolean solgt;
    private Varekategori kategori;
    private Salgsannonce salgsannonce;
    public Vare(String navn, int udbudspris, boolean solgt, Varekategori kategori) {
        this.navn = navn;
        this.udbudspris = udbudspris;
        this.solgt = solgt;
        this.kategori = kategori;

    }

    public void setSalgsannonce(Salgsannonce salgsannonce) {
        if (this.salgsannonce != salgsannonce) {
            Salgsannonce oldSalgsannonce = this.salgsannonce;
            if (oldSalgsannonce != null) {
                salgsannonce.removeVare(this);
            }
            this.salgsannonce = salgsannonce;
            if (salgsannonce != null) {
                salgsannonce.addVare(this);
            }
        }
    }
    public Salgsannonce getSalgsannonce() {
        return salgsannonce;
    }
    public String getNavn() {
        return navn;
    }

    public int getUdbudspris() {
        return udbudspris;
    }

    public boolean isSolgt() {
        return solgt;
    }

    public Varekategori getKategori() {
        return kategori;
    }

    @Override
    public String toString() {
        return "Kategori: " + kategori + " Navn: " + navn + " Udbudspris: " + udbudspris;
    }
}
