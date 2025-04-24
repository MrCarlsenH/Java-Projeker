package gui.scenes;

import applikation.controller.Controller;
import applikation.model.Medarbejder;
import gui.layout.GuiLayout;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class GuiOpretBatch extends GuiBase {
    public GuiOpretBatch(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1,"Opret batch");
        initContent(gridPane);
    }
    private Label lblNavn = new Label("New spirit batch nr.");
    private TextField txfNavn = new TextField();
    private Label lblStartdato = new Label("Start dato");
    private DatePicker dpStartDato = new DatePicker();
    private Label lblSlutDato = new Label("Slut dato");
    private DatePicker dpSlutDato = new DatePicker();
    private Label lblAlkohol = new Label("Alkoholprocent");
    private TextField txfAlkohol = new TextField();
    private Label lblMaltBatch = new Label("Malt batch");
    private TextField txfMaltBatch = new TextField();
    private Label lblAntalLiter = new Label("Antal liter");
    private TextField txfAntalLiter = new TextField();
    private Label lblKornSort = new Label("Kornsort");
    private TextField txfKornSort = new TextField();
    private CheckBox chbRygemateriale = new CheckBox("Rygemateriale");
    private Label lblKommentar = new Label("Kommentar");
    private TextField txfKommentar = new TextField();
    private Label lblMedarbejder = new Label("Medarbejder");
    private ComboBox cmbMedarbejder = new ComboBox<Medarbejder>(FXCollections.observableList(Storage.getStorage().getMedarbejder()));

    private Button btnBekraeft = new Button("Opret batch");


    private void initContent(GridPane gridPane) {
        gridPane = GuiLayout.setUpGridPane(this, gridPane);

//        Nodes
        Node[][] nodes = new Node[9][2];
        nodes[0][0] = lblNavn;
        nodes[0][1] = txfNavn;
        nodes[1][0] = lblStartdato;
        nodes[1][1] = dpStartDato;
        nodes[2][0] = lblSlutDato;
        nodes[2][1] = dpSlutDato;
        nodes[3][0] = lblAlkohol;
        nodes[3][1] = txfAlkohol;
        nodes[4][0] = lblMaltBatch;
        nodes[4][1] = txfMaltBatch;
        nodes[5][0] = lblKornSort;
        nodes[5][1] = txfKornSort;
        nodes[6][0] = lblAntalLiter;
        nodes[6][1] = txfAntalLiter;
        nodes[7][0] = lblKommentar;
        nodes[7][1] = txfKommentar;
        nodes[8][0] = lblMedarbejder;
        nodes[8][1] = cmbMedarbejder;

        GridPane textfieldPane = new GridPane();
        textfieldPane.setPadding(new Insets(GuiLayout.getPadding()));
        textfieldPane.setHgap(GuiLayout.getHgap());
        textfieldPane.setVgap(GuiLayout.getVgap());

        txfKommentar.setMinHeight(80);

        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                textfieldPane.add(nodes[i][j],j,i);
                textfieldPane.setHalignment(nodes[i][j], HPos.RIGHT);
            }
        }
        gridPane.add(textfieldPane,0,0);
        gridPane.add(btnBekraeft,0,1);

        btnBekraeft.setPrefSize(GuiLayout.getPrefHeight() / 3, GuiLayout.getPrefWidth() / 6);
        btnBekraeft.setFont(GuiLayout.getLargeFont());
        gridPane.setHalignment(btnBekraeft, HPos.CENTER);

        btnBekraeft.setOnAction(e -> {bekraeftAction();});
    }

    private void bekraeftAction() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl!");
        alert.setHeaderText("Èn eller flere felter er ikke udfyldt korrekt");
        try {
            String newSpiritBatchNr = txfNavn.getText();
            LocalDate startDato = dpStartDato.getValue();
            LocalDate slutDato = dpSlutDato.getValue();
            Integer alkoholProcent = Integer.parseInt(txfAlkohol.getText());
            String maltBatch = txfMaltBatch.getText();
            String kornSort = txfKornSort.getText();
            Integer antalLiter = Integer.parseInt(txfAntalLiter.getText());
            boolean rygemateriale = chbRygemateriale.isSelected();
            String kommentar = txfKommentar.getText();
            Medarbejder medarbejder = (Medarbejder) cmbMedarbejder.getValue();

            ArrayList<Object> values = new ArrayList<>();
            values.add(newSpiritBatchNr);
            values.add(startDato);
            values.add(slutDato);
            values.add(alkoholProcent);
            values.add(maltBatch);
            values.add(kornSort);
            values.add(antalLiter);
            values.add(kommentar);
            values.add(medarbejder);

            for (Object o : values) {
                if (o == null) {
                    alert.showAndWait();
                    throw new RuntimeException();
                }
            }
            Controller.createBatch(newSpiritBatchNr, startDato, slutDato, (int) alkoholProcent, maltBatch, kornSort, (int) antalLiter, rygemateriale, kommentar, medarbejder);
//            System.out.println(Storage.getStorage().getBatchs().toString());
        } catch (NumberFormatException e) {
            alert.showAndWait();
        }
    }
}
