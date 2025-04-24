package guifx;

import application.controller.Controller;
import application.modules.Salgsannonce;
import application.modules.Sælger;
import application.modules.Vare;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class AppVindue extends Application {

    private ListView<Vare> lvwVarer = new ListView<>();
    private ListView<Sælger> lvwSalgsannoncer = new ListView<>();
    private TextField txfKøber, txfAftaltPris;
    private Button opretSalg = new Button();
    private Button SalgsFil = new Button();


    public void start(Stage stage) {
        stage.setTitle("Bane Bookings");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        //LISTVIEW og LABELS
        Label lblBaner = new Label("Aktive annoncer");
        pane.add(lblBaner, 0, 0);

        pane.add(lvwSalgsannoncer, 0, 1, 1, 1);
        lvwSalgsannoncer.setPrefWidth(250);
        lvwSalgsannoncer.setPrefHeight(200);
        lvwSalgsannoncer.getItems().setAll(Storage.getSælgere());

        Label lblSpillere = new Label("Varer");
        pane.add(lblSpillere, 1, 0);

        pane.add(lvwVarer, 1, 1, 1, 1);
        lvwVarer.setPrefWidth(250);
        lvwVarer.setPrefHeight(200);
        lvwVarer.getItems().setAll(Storage.getVarer());
    }

}
