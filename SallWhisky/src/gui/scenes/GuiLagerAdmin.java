package gui.scenes;

import applikation.controller.Controller;
import applikation.model.Destillat;
import applikation.model.Fad;
import applikation.model.Lager;
import gui.layout.GuiLayout;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import storage.Storage;

public class GuiLagerAdmin extends GuiBase {
    public GuiLagerAdmin(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1, "Lager Administration");
        initContent(gridPane);
    }
    private Label lblLager = new Label("Vælg lager: ");
    private ComboBox<Lager> cmbLager = new ComboBox<>(FXCollections.observableArrayList(Storage.getStorage().getLagers()));
    private Pane paneLager = new Pane();

    private void initContent(GridPane gridPane) {
        gridPane = GuiLayout.setUpGridPane(this, gridPane);

//        Nodes
        HBox hBox = new HBox(lblLager,cmbLager);
        gridPane.add(hBox,0,0);
        gridPane.add(paneLager,0,1);


//        Button action
        cmbLager.setOnAction(e -> onCmbLagerAction(cmbLager.getValue()));
    }
    private void onCmbLagerAction(Lager lager) {
        Fad[][] lagerpladser = lager.getLokation();
        int kol = lagerpladser.length;
        int row = lagerpladser[0].length;
        GridPane lagerGrid = new GridPane();
        lagerGrid.setVgap(GuiLayout.getVgap());
        lagerGrid.setHgap(GuiLayout.getHgap());
        paneLager.getChildren().add(0,lagerGrid);
        paneLager.layout();

        Button[][] buttons = new Button[kol][row];
        for (int i = 0; i < kol; i++) {
            for (int j = 0; j < row; j++) {
                if (lagerpladser[i][j] != null) {
                    String string = String.valueOf(lagerpladser[i][j].getFadNr());
                    if (!lagerpladser[i][j].isLedig()) {
                        buttons[i][j] = new Button(string);
                        buttons[i][j].setStyle("-fx-background-color: #FFFF00;");
                        lagerGrid.add(buttons[i][j], i, j);
                        int finalI = i;
                        int finalJ = j;
                        buttons[i][j].setOnAction(e -> onButtonAction(buttons[finalI][finalJ], lagerpladser[finalI][finalJ]));
                    } else {
                        buttons[i][j] = new Button(string);
                        buttons[i][j].setStyle("-fx-background-color: #00ff00;");
                        lagerGrid.add(buttons[i][j], i, j);
                        int finalI = i;
                        int finalJ = j;
                        buttons[i][j].setOnAction(e -> onButtonAction(buttons[finalI][finalJ], lagerpladser[finalI][finalJ]));
                    }
                } else {
                    buttons[i][j] = new Button();
                    buttons[i][j].setStyle("-fx-background-color: #FF0000;");
                    lagerGrid.add(buttons[i][j],i,j);
                }
                buttons[i][j].setPrefSize(30,30);
            }
        }
    }

    private void onButtonAction(Button button, Fad fad) {
        int[] plads = fad.getLokation(fad);
        int kol = plads[0];
        int row = plads[1];
        Destillat destillat = Controller.getFadIndhold(fad);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Fad Information");
        info.setHeaderText("Fad nr. " + fad.getFadNr() + ", Størrelse: " + fad.getStørrelse() + "\nPlads: kolonne " + kol + ", række " + row);
        if (destillat != null) {
            info.setContentText("Indhold: \n" + destillat.toString());
        } else info.setContentText("Fadet er ledigt.");
        info.showAndWait();
    }
}
