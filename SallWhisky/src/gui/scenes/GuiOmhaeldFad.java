package gui.scenes;

import applikation.controller.Controller;
import applikation.model.*;
import gui.layout.GuiLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class GuiOmhaeldFad extends GuiBase {
    public GuiOmhaeldFad(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1,"Omhæld fad");
        initContent(gridPane);
    }
    private ArrayList<Destillat> readyDestillat = new ArrayList<>();
    private ArrayList<Destillat> selectedDestillat = new ArrayList<>();
    private ArrayList<Integer> antalLiterList = new ArrayList<>();
    private Label lblFadType = new Label("1: Vælg fadtype:");
    private ComboBox<String> cmbFadType = new ComboBox<>(fadTyper());
    private ListView<Destillat> lvwDestillat = new ListView<>(readyDestillat());
    private ListView<Destillat> lvwSelectedDestillat = new ListView<>(selectedDestillat());
    private javafx.scene.control.TextArea txaInfo = new javafx.scene.control.TextArea();
    private Label lblAntalLiter = new Label("Antal liter:");
    private Label lblDestillater = new Label("Destillater klar til påfyldning:");
    private Label lblSelectedDestillater = new Label("Valgte destillater:");
    private javafx.scene.control.TextField txfAntalLiter = new javafx.scene.control.TextField();
    private Label lblFad = new Label("2: Vælg fad:");
    private ComboBox cmbFad = new ComboBox<Fad>();
    private javafx.scene.control.Button btnTilfoej = new javafx.scene.control.Button("Tilføj batch");
    private javafx.scene.control.Button btnPaafyld = new Button("Udfør påfyldning");
    private int tempAntalLiter = 0;

    private void initContent(GridPane gridPane) {
        gridPane = GuiLayout.setUpGridPane(this, gridPane);
//        gridPane.setGridLinesVisible(true);

//        Nodes
        GridPane midGrid = new GridPane();
        midGrid.setHgap(GuiLayout.getHgap());
        midGrid.setVgap(GuiLayout.getVgap());
        HBox hBox = new HBox(lblAntalLiter,txfAntalLiter);
        midGrid.add(hBox,0,0);
        midGrid.add(btnTilfoej,0,1);
        VBox midBox = new VBox(midGrid);
        midBox.setAlignment(Pos.CENTER);

        HBox fadTypeBox = new HBox(lblFadType,cmbFadType);
        fadTypeBox.setSpacing(GuiLayout.getVgap());

        GridPane leftGrid = new GridPane();
        leftGrid.setHgap(GuiLayout.getHgap());
        leftGrid.setVgap(GuiLayout.getVgap());
        leftGrid.add(lblDestillater,0,0);
        leftGrid.add(lvwDestillat,0,1);

        GridPane rightGrid = new GridPane();
        rightGrid.setHgap(GuiLayout.getHgap());
        rightGrid.setVgap(GuiLayout.getVgap());
        rightGrid.add(lblSelectedDestillater,0,0);
        rightGrid.add(lvwSelectedDestillat,0,1);

        gridPane.add(midBox,1,0);
        gridPane.add(fadTypeBox,1,1);
        gridPane.add(leftGrid,0,0);
        gridPane.add(rightGrid,3,0);
        gridPane.add(btnPaafyld,1,2);
        gridPane.add(lblFad,2,1);
        gridPane.add(cmbFad,3,1);
        gridPane.add(txaInfo,3,2);

        lvwDestillat.setMaxSize(250,400);
        lvwSelectedDestillat.setMaxSize(250,400);
        txaInfo.setMaxSize(250,300);
        lblAntalLiter.setLabelFor(btnTilfoej);
        lblAntalLiter.setMinWidth(70);
        btnTilfoej.setPrefSize(200, 100);
        btnPaafyld.setPrefSize(200,100);

        btnTilfoej.setFont(GuiLayout.getLargeFont());
        btnPaafyld.setFont(GuiLayout.getLargeFont());

        GridPane finalGridPane = gridPane;
        gridPane.getChildren().forEach(e -> finalGridPane.setHalignment(e, HPos.CENTER));
        midGrid.getChildren().forEach(e -> midGrid.setHalignment(e, HPos.CENTER));

//        Button Actions
        btnTilfoej.setOnAction(e -> onBtnTilfoejAction());
        btnPaafyld.setOnAction(e -> onBtnPaafyldAction());
        cmbFadType.setOnAction(e -> onFadTypeAction());
        cmbFad.setOnAction(e -> onBtnFadAction());
    }

    private void onBtnFadAction() {
        Destillat destillat = null;
        for (Destillat d : Storage.getStorage().getDestillat()) {
            if (d.getFad().equals(cmbFad.getValue())) {
                destillat = d;
                tempAntalLiter += destillat.getAntalLiter();
                antalLiterList.add(destillat.getAntalLiter());
                txaInfo.setText("Samlet antal liter: " + tempAntalLiter);
                lvwSelectedDestillat.getItems().add(destillat);
            }
        }

    }

    private void onBtnTilfoejAction() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl!");
        alert.setHeaderText("Du skal vælge et gyldigt antal liter");
        try {
            Destillat selectedItem = lvwDestillat.getSelectionModel().getSelectedItem();
            if (selectedItem != null && Integer.parseInt(txfAntalLiter.getText()) <= selectedItem.getAntalLiter()) {
                tempAntalLiter += (int) Integer.parseInt(txfAntalLiter.getText());
                selectedItem.setAntalLiter(selectedItem.getAntalLiter() - Integer.parseInt(txfAntalLiter.getText()));

                lvwSelectedDestillat.getItems().add(selectedItem);
                antalLiterList.add(Integer.parseInt(txfAntalLiter.getText()));
                txaInfo.setText("Samlet antal liter: " + tempAntalLiter);
                lvwDestillat.refresh();
            } else {
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            alert.showAndWait();
        }
    }

    private void onBtnPaafyldAction() {
        ArrayList<Destillat> destillater = new ArrayList<>(lvwSelectedDestillat.getItems());
        ArrayList<DestillatComponent> påfyldninger = new ArrayList<>();
        Fad fad = (Fad) cmbFad.getValue();
        if (!destillater.isEmpty() && cmbFad.getValue() != null && tempAntalLiter <= fad.getStørrelse()) {
            int alkoholdProcent = udregnAlkoholProcent(destillater,antalLiterList);
            Destillat destillat = Controller.createDestillat(LocalDate.now(),alkoholdProcent,fad,påfyldninger,tempAntalLiter);

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Omhældning Registreret");
            confirmation.setHeaderText("Omhældning er registreret!");
            confirmation.setContentText(destillat.historik());
            confirmation.setHeight(500);
            confirmation.setResizable(true);
            confirmation.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText("Du skal vælge fad og destillater, og sørge for at fadets størrelse er passende");
            alert.showAndWait();
        }
    }

    private ObservableList<Destillat> readyDestillat() {
        readyDestillat.addAll(Storage.getStorage().getDestillat());
        ObservableList<Destillat> observableList = FXCollections.observableArrayList(readyDestillat);

        return observableList;
    }
    private ObservableList<Destillat> selectedDestillat() {
        ObservableList<Destillat> observableList = FXCollections.observableArrayList(selectedDestillat);
        return observableList;
    }
    private ObservableList<Fad> getFade(String string) {
        ArrayList<Fad> fadList = new ArrayList<>();
        for (Fad f : Storage.getStorage().getFad()) {
            if (f.getFadType().equals(string)) {
                fadList.add(f);
            }
        }
        ObservableList<Fad> observableList = FXCollections.observableArrayList(fadList);
        return observableList;
    }
    private ObservableList<String> fadTyper() {
        ArrayList<String> list = new ArrayList<>();
        for (Fad f : Storage.getStorage().getFad()) {
            if (!list.contains(f.getFadType())) {
                list.add(f.getFadType());
            }
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(list);
        return observableList;
    }
    private void onFadTypeAction() {
        cmbFad.setItems(getFade(cmbFadType.getValue()));
    }
    public static int udregnAlkoholProcent (ArrayList<Destillat> destillater, ArrayList<Integer> antalLiter) {
        int procent = 0;
        int mængde = 0;
        for (int i = 0; i < destillater.size(); i++) {
            procent += destillater.get(i).getAlkoholProcent() * antalLiter.get(i);
        }
        for (int i : antalLiter) {
            mængde += i;
        }
        return procent / mængde;
    }

}