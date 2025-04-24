package gui.scenes;

import gui.layout.GuiLayout;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class GuiOpdaterFad extends GuiBase {
    public GuiOpdaterFad(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1, "Opdatér fad");
        initContent(gridPane);
    }
    private void initContent(GridPane gridPane) {
        gridPane = GuiLayout.setUpGridPane(this, gridPane);

//        Nodes

    }
}