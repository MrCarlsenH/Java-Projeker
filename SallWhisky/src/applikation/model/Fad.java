package applikation.model;

public class Fad {
    private int fadNr;
    private String fadType;
    private int størrelse;
    private boolean ledig;
    private int antalGangeBrugt;
    private boolean pensioneret;
    private Lager lager;

    public Fad(int fadNr, String fadType, int størrelse, Lager lager) {
        this.fadNr = fadNr;
        this.fadType = fadType;
        this.størrelse = størrelse;
        this.lager = lager;
        this.ledig = true;
        this.antalGangeBrugt = 0;
        this.pensioneret = false;
    }

    public void inkrementerAntalGangeBrugt() {
        antalGangeBrugt++;
        if (antalGangeBrugt > 3) {
            this.setPensioneret(true);
        }
    }

    public int getFadNr() {
        return fadNr;
    }

    public boolean isLedig() {
        return ledig;
    }

    public void setLedig(boolean ledig) {
        this.ledig = ledig;
    }

    public String getFadType() {
        return fadType;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public Lager getLager() {
        return lager;
    }

    public int[] getLokation(Fad fad){
       return lager.getFadPlads(this);
    }

    public void setLagerFørsteLedig(Lager lager) throws Exception {
        if(this.lager != lager){
            Lager oldLager = this.lager;
            if(oldLager != null){
                oldLager.removeFad(this);
            }
        }
        this.lager = lager;
        if(lager != null){
            lager.addFadFørsteLedig(this);
        }
    }

    public void setLagerBestemtPlads(Lager lager,int row,int col) throws Exception{
        if(this.lager != lager){
            Lager oldLager = this.lager;
            if(oldLager != null){
                oldLager.removeFad(this);
            }

        }
        this.lager = lager;
        if(lager != null){
            lager.addFadTilBestemPlads(this,row,col);
        }
    }

    public int getAntalGangeBrugt() {
        return antalGangeBrugt;
    }

    public boolean isPensioneret() {
        return pensioneret;
    }

    public void setPensioneret(boolean pensioneret) {
        this.pensioneret = pensioneret;
    }


    @Override
    public String toString() {
        return "fadNr: " + fadNr + ", Type: " + fadType;
    }
}
