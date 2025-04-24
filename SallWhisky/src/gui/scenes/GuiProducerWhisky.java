package gui.scenes;

import applikation.controller.Controller;
import applikation.model.Destillat;
import applikation.model.DestillatComponent;
import applikation.model.Fad;
import applikation.model.Whisky;
import gui.layout.GuiLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;


public class GuiProducerWhisky extends GuiBase {
    public GuiProducerWhisky(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1,"Producér whisky");
        initContent(gridPane);
    }
    private ArrayList<Destillat> readyDestillater = new ArrayList<>();
    private ArrayList<Destillat> selectedDestillater = new ArrayList<>();
    private ListView<Destillat> lvwDestillater = new ListView<>(readyDestillater());
    private ListView<Destillat> lvwSelectedDestillater = new ListView<>(selectedDestillater());
    private TextArea txaInfo = new TextArea();
    private Label lblAntalLiter = new Label("Antal liter:");
    private Label lblDestillater = new Label("Destillater klar til Whisky:");
    private Label lblSelectedDestillater = new Label("Valgte destillater:");
    private TextField txfAntalLiter = new TextField();
    private CheckBox chbVand = new CheckBox("Tilføj vand");
    private Label lblNavn = new Label("Whisky navn: ");
    private TextField txfNavn = new TextField();
    private Button btnTilfoej = new Button("Tilføj destillat");
    private Button btnProducer = new Button("Færdiggør whisky");
    private int tempAntalLiter = 0;
    private Whisky whisky;

    private void initContent(GridPane gridPane) {
        gridPane = GuiLayout.setUpGridPane(this, gridPane);

//        Nodes
        GridPane midGrid = new GridPane();
        midGrid.setHgap(GuiLayout.getHgap());
        midGrid.setVgap(GuiLayout.getVgap());
        HBox hBox = new HBox(lblAntalLiter,txfAntalLiter);
        midGrid.add(hBox,0,0);
        midGrid.add(btnTilfoej,0,1);
        VBox midBox = new VBox(midGrid);
        midBox.setAlignment(Pos.CENTER);

        GridPane leftGrid = new GridPane();
        leftGrid.setHgap(GuiLayout.getHgap());
        leftGrid.setVgap(GuiLayout.getVgap());
        leftGrid.add(lblDestillater,0,0);
        leftGrid.add(lvwDestillater,0,1);
        HBox hbNavn = new HBox(lblNavn,txfNavn);
        gridPane.add(hbNavn,1,1);

        GridPane rightGrid = new GridPane();
        rightGrid.setHgap(GuiLayout.getHgap());
        rightGrid.setVgap(GuiLayout.getVgap());
        rightGrid.add(lblSelectedDestillater,0,0);
        rightGrid.add(lvwSelectedDestillater,0,1);

        gridPane.add(midBox,1,0);
        gridPane.add(leftGrid,0,0);
        gridPane.add(rightGrid,3,0);
        gridPane.add(btnProducer,1,2);
        gridPane.add(chbVand,3,1);
        gridPane.add(txaInfo,3,2);

        lvwDestillater.setMaxSize(250,400);
        lvwSelectedDestillater.setMaxSize(250,400);
        txaInfo.setMaxSize(250,300);
        lblAntalLiter.setLabelFor(btnTilfoej);
        lblAntalLiter.setMinWidth(70);
        btnTilfoej.setPrefSize(200, 100);
        btnProducer.setPrefSize(200,100);

        chbVand.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");
        btnTilfoej.setFont(GuiLayout.getLargeFont());
        btnProducer.setFont(GuiLayout.getLargeFont());

        GridPane finalGridPane = gridPane;
        gridPane.getChildren().forEach(e -> finalGridPane.setHalignment(e,HPos.CENTER));
        midGrid.getChildren().forEach(e -> midGrid.setHalignment(e, HPos.CENTER));

//        Button Actions
        btnTilfoej.setOnAction(e -> onBtnTilfoejAction());
        btnProducer.setOnAction(e -> onBtnProducerAction());
    }
    private void onBtnTilfoejAction() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl!");
        alert.setHeaderText("Du skal vælge et gyldigt antal liter");
        try {
            Destillat selectedItem = (Destillat) lvwDestillater.getSelectionModel().getSelectedItem();

            if (selectedItem != null && Integer.parseInt(txfAntalLiter.getText()) <= selectedItem.getAntalLiter()) {
                tempAntalLiter += (int) Integer.parseInt(txfAntalLiter.getText());
                selectedItem.setAntalLiter(selectedItem.getAntalLiter() - Integer.parseInt(txfAntalLiter.getText()));

                lvwSelectedDestillater.getItems().add(selectedItem);
                lvwDestillater.getItems().remove(selectedItem);

                txaInfo.setText("Samlet antal liter: " + tempAntalLiter);
                lvwDestillater.refresh();
            } else {
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            alert.showAndWait();
        }
    }

    private void onBtnProducerAction() {
        try {
            ArrayList<DestillatComponent> destillater = new ArrayList<>(lvwSelectedDestillater.getItems());
            Fad placeholder = Controller.createFad(0, "", 0, null);
            boolean vand = chbVand.isSelected();
            int alkohol = 0;
            String navn = txfNavn.getText();
            for (Destillat d : selectedDestillater) {
                alkohol += d.getAlkoholProcent();
            }
            alkohol = alkohol / lvwSelectedDestillater.getItems().size();

            Destillat destillat = new Destillat(placeholder, LocalDate.now(), alkohol, destillater, tempAntalLiter);
            if (!navn.isEmpty()) {
                whisky = Controller.createWhisky(navn, destillat, vand, destillat.getPåfyldningsDato(), tempAntalLiter);
            }
            Storage.getStorage().removeFad(placeholder);

            String string = whisky.toString();
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Whisky Registreret");
            confirmation.setHeaderText("Whiskyen er færdigproduceret!");
            confirmation.setContentText(string);
            confirmation.setResizable(true);
            confirmation.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    private ObservableList<Destillat> readyDestillater() {
        for (Destillat d : Storage.getStorage().getDestillat()) {
            int antalAar = LocalDate.now().getYear() - d.getPåfyldningsDato().getYear();
            if (d.isWhiskyReady() || antalAar >= 3) {
                readyDestillater.add(d);
            }
        }
        ObservableList<Destillat> observableList = FXCollections.observableArrayList(readyDestillater);

        return observableList;
    }
    private ObservableList<Destillat> selectedDestillater() {
        ObservableList<Destillat> observableList = FXCollections.observableArrayList(selectedDestillater);
        return observableList;
    }
}