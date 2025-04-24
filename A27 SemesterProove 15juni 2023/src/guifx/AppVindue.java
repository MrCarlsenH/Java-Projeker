package guifx;

import application.controller.Controller;
import application.modules.Bane;
import application.modules.Booking;
import application.modules.Spiller;
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
import java.util.Arrays;

public class AppVindue extends Application {

    private ListView<Spiller> lvwSpillere = new ListView<>();

    private TextArea txaSpillerBooking, txaBaner;
    private ListView<Bane> lvwBaner = new ListView<>();
    private TextField txfDato, txfTid;
    private CheckBox isSingle = new CheckBox();
    private Button bookBane = new Button();


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
        Label lblBaner = new Label("Baner");
        pane.add(lblBaner, 0, 0);

        pane.add(lvwBaner,0,1,1,1);
        lvwBaner.setPrefWidth(250);
        lvwBaner.setPrefHeight(200);
        lvwBaner.getItems().setAll(Storage.getBaner());

        Label lblSpillere = new Label("Spillere");
        pane.add(lblSpillere, 1, 0);

        pane.add(lvwSpillere,1,1,1,1);
        lvwSpillere.setPrefWidth(100);
        lvwSpillere.setPrefHeight(200);
        lvwSpillere.getItems().setAll(Storage.getSpillere());

        Label lblBookinger = new Label("Spillerens Bookinger");
        pane.add(lblBookinger, 2, 0);

        txaSpillerBooking = new TextArea();
        pane.add(txaSpillerBooking,2,1,4,1);
        txaSpillerBooking.setPrefWidth(280);
        txaSpillerBooking.setPrefHeight(100);
        txaSpillerBooking.setEditable(false);

        ChangeListener<Spiller> listener = (ov, oldCustomer, newCustomer) -> this.updateControlsSpiller();
        lvwSpillere.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblAntalBookinger = new Label("Antal bookinger på banen");
        pane.add(lblAntalBookinger,0,2);

        txaBaner = new TextArea();
        pane.add(txaBaner ,0,3,1,3);
        txaBaner .setPrefWidth(250);
        txaBaner .setPrefHeight(100);
        txaBaner .setEditable(false);

        ChangeListener<Bane> listener1 = (ov, oldCustomer, newCustomer) -> this.updateControlsBaner();
        lvwBaner.getSelectionModel().selectedItemProperty().addListener(listener1);

        Label lblOpret = new Label("Opret booking:");
        pane.add(lblOpret,2,2);

        Label lblDato = new Label("Dato:");
        pane.add(lblDato,2,3);

        txfDato = new TextField();
        pane.add(txfDato,3,3,2,1);
        txfDato.setPrefWidth(250);

        Label lblTid = new Label("Tid:");
        pane.add(lblTid,2,4);

        txfTid = new TextField();
        pane.add(txfTid,3,4,2,1);
        txfTid.setPrefWidth(250);

        Label lblSingle = new Label("Single:");
        pane.add(lblSingle,4,5);

        isSingle = new CheckBox();
        pane.add(isSingle,3,5);

        bookBane = new Button("Book bane til spiller");
        pane.add(bookBane,2,6);
        bookBane.setOnAction(event -> opretBooking());

    }

    public void updateControlsSpiller() {
        Spiller spiller = lvwSpillere.getSelectionModel().getSelectedItem();
        if (spiller != null) {
            StringBuilder sBooking = new StringBuilder();
            for (Booking booking : spiller.getBookinger()){
                sBooking.append(booking.toString() +"\n");
//                booking.getDato() + booking.getTid() + booking.getBane().getNummer() + booking.getSpiller().getNavn()
            }
            txaSpillerBooking.setText(sBooking.toString());
            }
         else {
            txaSpillerBooking.clear();
        }
    }

    public void updateControlsBaner() {
        Bane bane = lvwBaner.getSelectionModel().getSelectedItem();
        if (bane != null) {
            StringBuilder sBooking = new StringBuilder();
            for (int i = 0; i < bane.getSidsteTid().getHour() - bane.getFørsteTid().getHour() + 1; i++) {
                    sBooking.append("Tid: "  + (bane.getFørsteTid().getHour() + i) + " antal: " + bane.antalBookingerPrTime()[i] + "\n");
                }
            txaBaner.setText(sBooking.toString());
            }
        else {
            txaBaner.clear();
        }
    }

    public void opretBooking() {
        Bane bane = lvwBaner.getSelectionModel().getSelectedItem();
        if (bane == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mangler valg");
            alert.setHeaderText("Vælg en bane");
            alert.setContentText("Se listen over baner");
            alert.show();
            return;
        }
        Spiller spiller = lvwSpillere.getSelectionModel().getSelectedItem();
        if (spiller == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mangler valg");
            alert.setHeaderText("Vælg en spiller");
            alert.setContentText("Se listen over spillere");
            alert.show();
            return;
        }

        LocalDate dato;
        try {
            dato = LocalDate.parse(txfDato.getText());
        } catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Kunne ikke analysere dato");
            alert.setContentText("Vælg en dato i formatet 'yyyy-MM-dd'");
            alert.showAndWait();
            return;
        }
        LocalTime tid;
        try {
            tid = LocalTime.of(1, 1).parse(txfTid.getText());
        } catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Kunne ikke analysere tidspunktet");
            alert.setContentText("Vælg en tid i formatet timer:minutter ");
            alert.showAndWait();
            return;
        }
        boolean single = isSingle.isSelected();
        if (bane.tidLedig(dato, tid))
            Controller.createBooking(dato, tid, single, bane, spiller);

        lvwSpillere.getItems().setAll(Storage.getSpillere());
        int index = lvwSpillere.getItems().size() - 1;
        lvwSpillere.getSelectionModel().select(index);
    }

}
