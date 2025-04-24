package applikation.model;


public class Lager {
    private String navn;
    private String adresse;
    private Fad[][] lokation;

    public Lager(String navn, String adresse, final int antalRow, final int antalCol ) {
        this.navn = navn;
        this.adresse = adresse;
        lokation = new Fad[antalRow][antalCol];
    }

    public void addFadFørsteLedig(Fad fad){
        int counter = 0;
        int row = 0;
        int col = 0;
        for(int i = 0; i < lokation.length; i++){
            for (int j = 0; j < lokation[i].length; j++){
                if(lokation[i][j] == null && counter == 0){
                    row = i;
                    col = j;
                    counter++;
                }
            }
        }
        if(lokation[row][col] != fad) {
            lokation[row][col] = fad;
        }
    }



    public void addFadTilBestemPlads(Fad fad ,int row,int col ){
        if(lokation[row][col] == null){
            lokation[row][col] = fad;
        }
        else {
            throw new RuntimeException("Pladsen er i brug ");
        }
    }



    public void removeFad(Fad fad) throws Exception{
        for (int row = 0; row < lokation.length; row++) {
            for (int col = 0; col < lokation[row].length; col++) {
                if(lokation[row][col] == fad){
                    lokation[row][col] = null;
                    fad.setLagerBestemtPlads(null,row,col);
                }

            }
        }
    }

    public void bytPlads(int row1, int col1, int row2, int col2 ){
        Fad fad1 = null;
        Fad fad2 = null;
        fad1 = lokation[row1][col1];
        fad2 = lokation[row2][col2];
        lokation[row1][col1] = fad2;
        lokation[row2][col2] = fad1;
        }

        public int[] getFadPlads(Fad fad){
        int[] fadlokation = new int[2];
            for (int row = 0; row < lokation.length; row++) {
                for (int col = 0; col < lokation[row].length; col++) {
                    if(lokation[row][col] == fad){
                        fadlokation = new int[]{row, col};
                    }
                }
            }
            return fadlokation;
        }

    public Fad[][] getLokation(){
        return lokation;
    }

    public String udskrivArray() {
        String string = "";
        for (int row = 0; row < lokation.length; row++) {
            for (int col = 0; col < lokation[row].length; col++) {
                string += lokation[row][col] + " ";
            }
            string+="\n";
        }
        return string;
    }

    @Override
    public String toString() {
        return navn;
    }
}
