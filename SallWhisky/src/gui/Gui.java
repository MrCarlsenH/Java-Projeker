package gui;

import gui.layout.GuiLayout;
import gui.scenes.GuiStartScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GuiLayout.setStage(stage);
        GridPane gridPane = new GridPane();
        Scene scene = new GuiStartScene(gridPane, GuiLayout.getPrefHeight(),GuiLayout.getPrefWidth());
        stage.setScene(scene);
        stage.setTitle("Sall Whisky Distillery Lager");
        stage.show();
    }

}
