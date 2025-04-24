package gui.scenes;

import applikation.model.Destillat;
import applikation.model.Whisky;
import gui.layout.GuiLayout;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import storage.Storage;

public class GuiUdskrivHistorik extends GuiBase {
    public GuiUdskrivHistorik(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1,"Udskriv historik");
        initContent(gridPane);
    }

    private Label lblWhisky = new Label("Whiskys:");
    private ListView<Whisky> lvwWhisky = new ListView<>(FXCollections.observableArrayList(Storage.getStorage().getWhiskys()));
    private Button btnUdskriv = new Button("Udskriv historik");

    private void initContent(GridPane gridPane) {
        gridPane = GuiLayout.setUpGridPane(this, gridPane);

//        Nodes
        gridPane.add(lblWhisky,0,0);
        gridPane.add(lvwWhisky,0,1);
        gridPane.add(btnUdskriv,0,2);

        btnUdskriv.setPrefSize(GuiLayout.getPrefHeight()/3,GuiLayout.getPrefWidth()/6);

//        Button action
        btnUdskriv.setOnAction(e -> onBtnUdskrivAction());
    }

    private void onBtnUdskrivAction() {
        try {
            Whisky whisky = lvwWhisky.getSelectionModel().getSelectedItem();
            Destillat destillat = (Destillat) whisky.getDestillat();
            Alert historik = new Alert(Alert.AlertType.INFORMATION);
            historik.setHeight(400);
            historik.setResizable(true);

            TextArea textArea = new TextArea(destillat.historik());
            ScrollPane scrollPane = new ScrollPane(textArea);
            scrollPane.fitToHeightProperty().set(true);

            historik.setTitle("Whisky historik");
            historik.setHeaderText(whisky.toString());
            historik.getDialogPane().setContent(scrollPane);

            historik.showAndWait();

        } catch (Exception e){}
    }
}
