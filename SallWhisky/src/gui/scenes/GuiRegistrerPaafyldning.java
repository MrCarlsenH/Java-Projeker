package gui.scenes;

import applikation.controller.Controller;
import applikation.model.*;
import gui.layout.GuiLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class GuiRegistrerPaafyldning extends GuiBase {
    public GuiRegistrerPaafyldning(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1, "Registrér påfyldning");
        initContent(gridPane);
    }
    private ArrayList<Batch> readyBatches = new ArrayList<>();
    private ArrayList<Batch> selectedBatches = new ArrayList<>();
    private ArrayList<Integer> antalLiterList = new ArrayList<>();
    private ArrayList<Fad> ledigeFad = new ArrayList<>();
    private Label lblFadType = new Label("1: Vælg fadtype:");
    private ComboBox<String> cmbFadType = new ComboBox<>(fadTyper());
    private ListView<Batch> lvwBatches = new ListView<>(readyBatches());
    private ListView<Batch> lvwSelectedBatches = new ListView<>(selectedBatches());
    private TextArea txaInfo = new TextArea();
    private Label lblAntalLiter = new Label("Antal liter:");
    private Label lblBatches = new Label("Batches klar til påfyldning:");
    private Label lblSelectedBatches = new Label("Valgte batches:");
    private TextField txfAntalLiter = new TextField();
    private Label lblFad = new Label("2: Vælg fad:");
    private ComboBox cmbFad = new ComboBox<Fad>();
    private Button btnTilfoej = new Button("Tilføj batch");
    private Button btnPaafyld = new Button("Udfør påfyldning");
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
        leftGrid.add(lblBatches,0,0);
        leftGrid.add(lvwBatches,0,1);

        GridPane rightGrid = new GridPane();
        rightGrid.setHgap(GuiLayout.getHgap());
        rightGrid.setVgap(GuiLayout.getVgap());
        rightGrid.add(lblSelectedBatches,0,0);
        rightGrid.add(lvwSelectedBatches,0,1);

        gridPane.add(midBox,1,0);
        gridPane.add(fadTypeBox,1,1);
        gridPane.add(leftGrid,0,0);
        gridPane.add(rightGrid,3,0);
        gridPane.add(btnPaafyld,1,2);
        gridPane.add(lblFad,2,1);
        gridPane.add(cmbFad,3,1);
        gridPane.add(txaInfo,3,2);

        lvwBatches.setMaxSize(250,400);
        lvwSelectedBatches.setMaxSize(250,400);
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
    }
    private void onBtnTilfoejAction() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl!");
        alert.setHeaderText("Du skal vælge et gyldigt antal liter");
        try {
            Batch selectedItem = lvwBatches.getSelectionModel().getSelectedItem();
            if (selectedItem != null && Integer.parseInt(txfAntalLiter.getText()) <= selectedItem.getAntalLiter()) {
                tempAntalLiter += (int) Integer.parseInt(txfAntalLiter.getText());
                selectedItem.setAntalLiter(selectedItem.getAntalLiter() - Integer.parseInt(txfAntalLiter.getText()));

                lvwSelectedBatches.getItems().add(selectedItem);
                antalLiterList.add(Integer.parseInt(txfAntalLiter.getText()));
                txaInfo.setText("Samlet antal liter: " + tempAntalLiter);
                lvwBatches.refresh();
            } else {
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            alert.showAndWait();
        }
    }

    private void onBtnPaafyldAction() {
        ArrayList<Batch> batches = new ArrayList<>(lvwSelectedBatches.getItems());
        ArrayList<DestillatComponent> påfyldninger = new ArrayList<>();
        Fad fad = (Fad) cmbFad.getValue();
        if (!batches.isEmpty() && cmbFad.getValue() != null && tempAntalLiter <= fad.getStørrelse()) {
            for (int i = 0; i < batches.size(); i++) {
                Påfyldning påfyldning = Controller.createPåfyldning(antalLiterList.get(i),batches.get(i));
                påfyldninger.add(påfyldning);
            }
            int alkoholdProcent = Controller.udregnAlkoholProcent(batches,antalLiterList);
            Destillat destillat = Controller.createDestillat(LocalDate.now(),alkoholdProcent,fad,påfyldninger,tempAntalLiter);

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Påfyldning Registreret");
            confirmation.setHeaderText("Påfyldningen er registreret!");
            confirmation.setContentText(destillat.historik());
            confirmation.setHeight(500);
            confirmation.setResizable(true);
            confirmation.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText("Du skal vælge fad og batches, og sørge for at fadets størrelse er passende");
            alert.showAndWait();
        }
    }

    private ObservableList<Batch> readyBatches() {
        for (Batch b : Storage.getStorage().getBatchs()) {
            if (b.getAntalLiter() > 0) {
                readyBatches.add(b);
            }
        }
        ObservableList<Batch> observableList = FXCollections.observableArrayList(readyBatches);

        return observableList;
    }
    private ObservableList<Batch> selectedBatches() {
        ObservableList<Batch> observableList = FXCollections.observableArrayList(selectedBatches);
        return observableList;
    }
    private ObservableList<Fad> ledigeFad(String string) {
        ObservableList<Fad> observableList = FXCollections.observableArrayList(Controller.findLedigFad(string));
        return observableList;
    }
    private ObservableList<String> fadTyper() {
        ArrayList<String> list = new ArrayList<>();
        for (Fad f : Storage.getStorage().getFad()) {
            if (f.isLedig() && !list.contains(f.getFadType())) {
                list.add(f.getFadType());
            }
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(list);
        return observableList;
    }
    private void onFadTypeAction() {
        cmbFad.setItems(ledigeFad(cmbFadType.getValue()));
    }
}