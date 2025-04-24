package applikation.model;

import java.time.LocalDate;

public abstract class DestillatComponent {

    public LocalDate getPåfyldningsDato(){
        throw new UnsupportedOperationException();

    }
    public int getAlkoholProcent(){
        throw new UnsupportedOperationException();
    }

    public int getAntalLiter(){
        throw new UnsupportedOperationException();
    }


    public String historik(){
        throw new UnsupportedOperationException();
    }

    public String getName() { throw new UnsupportedOperationException();};



}
