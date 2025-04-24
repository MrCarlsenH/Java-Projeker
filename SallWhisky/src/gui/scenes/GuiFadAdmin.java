package gui.scenes;

import gui.layout.GuiLayout;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GuiFadAdmin extends GuiBase {
    public GuiFadAdmin(GridPane gridPane, double v, double v1) {
        super(gridPane, v, v1, "Fad Administration");
        initContent(gridPane);
    }

    private String[] buttonNames = {"Fjern fad", "Opret nyt fad", "Opdater fad", "Omhæld fad", "Opret batch", "Registrér påfyldning", "Producér whisky", "Udskriv historik"};
    private Button[] buttons = new Button[buttonNames.length];

    private void initContent(GridPane gridPane) {
        gridPane = GuiLayout.setUpGridPane(this, gridPane);

//        Nodes
        int[][] grid = new int[buttonNames.length / 2][2]; /* bruges som tæller til et grid til knapperne */
        int count = 0; /* bruges til at løbe knap-arrayet igennem */

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                buttons[count] = new Button(buttonNames[count]);
                buttons[count].setPrefSize(GuiLayout.getPrefHeight() / 3, GuiLayout.getPrefWidth() / 6);
                buttons[count].setFont(GuiLayout.getLargeFont());
                gridPane.add(buttons[count], j, i);
                gridPane.setHalignment(buttons[count], HPos.CENTER);
                int finalCount = count;
                buttons[count].setOnAction(e -> buttonAction(buttons[finalCount]));
                count++;
            }
        }
    }

    private void buttonAction(Button button) {
        GuiLayout.setLastScene(this);
        if (button == buttons[0]) {
            GuiLayout.getStage().setScene(new GuiFjernFad(new GridPane(), GuiLayout.getPrefHeight(), GuiLayout.getPrefWidth()));
        } else if (button == buttons[1]) {
            GuiLayout.getStage().setScene(new GuiOpretFad(new GridPane(), GuiLayout.getPrefHeight(), GuiLayout.getPrefWidth()));
        } else if (button == buttons[2]) {
            GuiLayout.getStage().setScene(new GuiOpdaterFad(new GridPane(), GuiLayout.getPrefHeight(), GuiLayout.getPrefWidth()));
        } else if (button == buttons[3]) {
            GuiLayout.getStage().setScene(new GuiOmhaeldFad(new GridPane(), GuiLayout.getPrefHeight(), GuiLayout.getPrefWidth()));
        } else if (button == buttons[4]) {
            GuiLayout.getStage().setScene(new GuiOpretBatch(new GridPane(), GuiLayout.getPrefHeight(), GuiLayout.getPrefWidth()));
        } else if (button == buttons[5]) {
            GuiLayout.getStage().setScene(new GuiRegistrerPaafyldning(new GridPane(), GuiLayout.getPrefHeight(), GuiLayout.getPrefWidth()));
        } else if (button == buttons[6]) {
            GuiLayout.getStage().setScene(new GuiProducerWhisky(new GridPane(), GuiLayout.getPrefHeight(), GuiLayout.getPrefWidth()));
        } else if (button == buttons[7]) {
            GuiLayout.getStage().setScene(new GuiUdskrivHistorik(new GridPane(), GuiLayout.getPrefHeight(), GuiLayout.getPrefWidth()));
        }
    }
}
