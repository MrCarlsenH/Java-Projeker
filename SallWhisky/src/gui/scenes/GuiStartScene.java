package gui.scenes;

import gui.layout.GuiLayout;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GuiStartScene extends GuiBase {
    public GuiStartScene(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1,"");
        initContent(gridPane);
    }
    private ImageView imageView;
    private Button lagerAdmnin = new Button("Lager Administration");
    private Button fadAdmin = new Button("Fad Administration");

    public void initContent(GridPane gridPane) {
        gridPane = GuiLayout.setUpGridPane(this, gridPane);

//        Nodes
        try {
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/Sall.png")));
        } catch (Exception e) {
            throw new RuntimeException("Billede loadede ikke korrekt!");
        }
        gridPane.add(imageView,0,0);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(GuiLayout.getPrefHeight()/3);

        gridPane.add(fadAdmin, 0,1);
        fadAdmin.setPrefSize(GuiLayout.getPrefHeight()/3,GuiLayout.getPrefWidth()/6);
        fadAdmin.setFont(GuiLayout.getLargeFont());

        gridPane.add(lagerAdmnin, 0,2);
        lagerAdmnin.setPrefSize(GuiLayout.getPrefHeight()/3,GuiLayout.getPrefWidth()/6);
        lagerAdmnin.setFont(GuiLayout.getLargeFont());

        GridPane finalGridPane = gridPane;
        gridPane.getChildren().forEach(e -> finalGridPane.setHalignment(e,HPos.CENTER)); /* aligner alle nodes indenfor deres gridcelle */

//        Actions
        fadAdmin.setOnAction(e -> fadAdminAction());
        lagerAdmnin.setOnAction(e -> LagerAdminAction());
    }

    private void fadAdminAction() {
        GuiLayout.setLastScene(this);
        GuiLayout.getStage().setScene(new GuiFadAdmin(new GridPane(), GuiLayout.getPrefHeight(),GuiLayout.getPrefWidth()));
    }
    private void LagerAdminAction() {
        GuiLayout.setLastScene(this);
        GuiLayout.getStage().setScene(new GuiLagerAdmin(new GridPane(), GuiLayout.getPrefHeight(),GuiLayout.getPrefWidth()));
    }
}
