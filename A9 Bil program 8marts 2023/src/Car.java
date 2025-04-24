import javafx.scene.paint.Color;

public class Car {

    private int doors;
    private int speed;
    private Color color;
    private int wheels;
    private int windows;
    private String mærke;
    private String størrelse;
    private boolean racerStriber;


    public Car(String størrelse, int doors, int speed, int wheels, int windows, Color color, String mærke,boolean racerStriber) {
        this.størrelse = størrelse;
        this.doors = doors;
        this.speed = speed;
        this.color = color;
        this.windows = windows;
        this.wheels = wheels;
        this.mærke = mærke;
        this.racerStriber = racerStriber;
    }

    public Car(int doors) {
        this.doors = doors;
    }

    public Car() {
        this.doors = 1;
        this.wheels = 3;
        this.color = Color.ROSYBROWN;
//        this.mærke = "Honda";
        this.windows = 2;
        this.speed = 60;
    }

    public Car(int doors, int windows) {
        this();
        this.doors = doors;
        this.windows = windows;
    }

    public int getDoors() {
        return this.doors;
    }

    public Color getColor() {
        return this.color;
    }

    public int getWheels() {
        return this.wheels;
    }

    public int getWindows() {
        return this.windows;
    }
    public boolean getRacerStriber(){
        return this.racerStriber;
    }
    public void setWindows(int newWindows){
        if(newWindows > 4) {
            this.windows = 4;
        }
        else {
            this.windows = newWindows;
        }
    }

    public void setDoors(int newDoors){
        if(newDoors > 4) {
            this.doors = 4;
        }
        else {
            this.doors = newDoors;
        }
    }
    public void setStørrelse(String newStørrelse){
        this.størrelse = newStørrelse;
    }

    public void setWheels(int newWheels){
        if(newWheels > 4) {
            this.wheels = 4;
        }
        else {
            this.wheels = newWheels;
        }
    }
//    public void setColor(String newColor){
//        if ("Rød".equals(newColor.toString())){
//            color = Color.RED;
//        }
//        else if ("Grøn".equals(newColor.toString())){
//            color = Color.GREEN;
//        }
//    }
    public void setRacerStriber(boolean newStriber){
        racerStriber = newStriber;
    }
    public void setMærke(String newMærke){
        mærke = newMærke;
    }
    public String getMærke(){
        return mærke;
    }
    public String getStørrelse(){
        return størrelse;
    }

    @Override
    public String toString(){
        return color + " " + mærke + " med " + wheels + " hjul og " + doors + " døre og en topfart på " + speed + "km/time.";
    }
}
