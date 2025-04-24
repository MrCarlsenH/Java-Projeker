package gui.scenes;

import applikation.controller.Controller;
import applikation.model.Fad;
import applikation.model.Lager;
import gui.layout.GuiLayout;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import storage.Storage;


public class GuiOpretFad extends GuiBase {
    public GuiOpretFad(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1,"Opret fad");
        initContent(gridPane);
    }
    private Label lblFadType = new Label("Fadtype: ");
    private TextField txfFadType = new TextField();
    private Label lblStørrelse = new Label("Størrelse: ");
    private TextField txfStørrelse = new TextField();
    private Label lblLager = new Label("Vælg lager: ");
    private ComboBox<Lager> cmbLager = new ComboBox<>(FXCollections.observableArrayList(Storage.getStorage().getLagers()));
    private Label lblEvtPlads = new Label("Vælg eventuelt lagerplads:");
    private Label lblKolonne = new Label("Kolonne: ");
    private TextField txfKolonne = new TextField();
    private Label lblRække = new Label("Række: ");
    private TextField txfRække = new TextField();
    private Button btnOpret = new Button("Opret fad");

    private void initContent(GridPane gridPane) {
        gridPane = GuiLayout.setUpGridPane(this, gridPane);

//        Nodes
        gridPane.add(lblFadType,0,0);
        gridPane.add(txfFadType,1,0);
        gridPane.add(lblStørrelse,0,1);
        gridPane.add(txfStørrelse,1,1);
        gridPane.add(lblLager,0,2);
        gridPane.add(cmbLager,1,2);
        gridPane.add(lblEvtPlads,0,3);
        gridPane.add(lblKolonne,0,4);
        gridPane.add(txfKolonne,1,4);
        gridPane.add(lblRække,0,5);
        gridPane.add(txfRække,1,5);
        gridPane.add(btnOpret,1,6);

        btnOpret.setPrefSize(GuiLayout.getPrefHeight()/3, GuiLayout.getPrefWidth()/6);

//        Button action
        btnOpret.setOnAction(e -> onBtnOpretAction());

    }
    private void onBtnOpretAction() {
        try {
            String fadType = txfFadType.getText();
            int fadStørrelse = Integer.parseInt(txfStørrelse.getText());
            Lager lager = cmbLager.getValue();
            int kolonne = -1;
            int række = -1;
            if (!txfKolonne.getText().isEmpty() && !txfRække.getText().isEmpty()) {
                kolonne = Integer.parseInt(txfKolonne.getText());
                række = Integer.parseInt(txfRække.getText());
            }
            if (lager != null && !fadType.isEmpty()) {
                Fad fad = null;
                if (kolonne >= 0 && række >= 0) {
                    fad = Controller.createFad(getNextFadNr(), fadType, fadStørrelse, lager, kolonne, række);
                } else {
                    fad = Controller.createFad(getNextFadNr(), fadType, fadStørrelse, lager);
                }
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle("Fad oprettet");
                confirmation.setHeaderText("Fadet er oprettet!");
                confirmation.setContentText(fad.toString() + ", plads: " + parseFadPlads(fad));
                confirmation.setHeight(500);
                confirmation.setResizable(true);
                confirmation.showAndWait();
            } else throw new Exception("Vælg lager og type");

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }
    private int getNextFadNr() {
        int i = -1;
        for (Fad fad : Storage.getStorage().getFad()) {
            if (fad.getFadNr() >= i) {
                i = fad.getFadNr() + 1;
            }
        }

        return i;
    }
    private String parseFadPlads(Fad fad) {
        int[] plads = fad.getLokation(fad);
        int kolonne = plads[0];
        int række = plads[1];
        String string = "kolonne " + kolonne + ", række " + række;

        return string;
    }
}