package gui.layout;

import gui.scenes.GuiBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Stack;

public final class GuiLayout {
    private static final double prefHeight = 1366/1.5; /* sætter skærmstørrelse til iPad dimensioner */
    private static final double prefWidth = 1024/1.5; /* sætter skærmstørrelse til iPad dimensioner */
    private static final int padding = 10;
    private static final int Hgap = 10;
    private static final int Vgap = 10;
    private static final Font largeFont = Font.font(20);
    private static Stack<Scene> previousScenes = new Stack<>(); /* stack der holder alle de tidligere scenes, som backButton kan gennemløbe*/
    private static Stage stage;

    private GuiLayout() {
    }
    public static GridPane setUpGridPane(GuiBase guiBase, GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        GridPane innerPane = new GridPane();
        Button backButton = new Button("Tilbage");
        Label lblName = new Label(guiBase.getName());

        BorderPane outerPane = new BorderPane();
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        BorderPane.setAlignment(lblName, Pos.TOP_CENTER);
        if (!previousScenes.empty()) {
            outerPane.setTop(lblName);
            lblName.setFont(Font.font(20));
            outerPane.setBottom(backButton);
            backButton.setPrefSize(GuiLayout.getPrefHeight()/10,GuiLayout.getPrefWidth()/12);
            backButton.setOnAction(e -> backButtonAction());
        }
        outerPane.setCenter(innerPane);
        gridPane.add(outerPane,0,0);
        outerPane.setPrefSize(GuiLayout.getPrefHeight(),GuiLayout.getPrefWidth());
        outerPane.setPadding(new Insets(GuiLayout.getPadding()));
//        gridPane.setGridLinesVisible(true);

        innerPane.setPadding(new Insets(GuiLayout.getPadding()));
        innerPane.setHgap(GuiLayout.getHgap());
        innerPane.setVgap(GuiLayout.getVgap());
//        innerPane.setGridLinesVisible(true);
        innerPane.setAlignment(Pos.CENTER);

        return innerPane;
    }

    private static void backButtonAction() {
        stage.setScene(GuiLayout.getLastScene());
    }

    public static double getPrefHeight() {
        return prefHeight;
    }

    public static double getPrefWidth() {
        return prefWidth;
    }

    public static int getPadding() {
        return padding;
    }

    public static int getHgap() {
        return Hgap;
    }

    public static int getVgap() {
        return Vgap;
    }

    public static Font getLargeFont() {
        return largeFont;
    }

    public static Scene getLastScene() {
        return GuiLayout.previousScenes.pop();
    }

    public static void setLastScene(Scene lastScene) {
        GuiLayout.previousScenes.add(lastScene);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GuiLayout.stage = stage;
    }
}
