package gui;

import javafx.event.Event;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.YatzyDice;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class YatzyGui extends Application {
    private YatzyDice dice = new YatzyDice();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Yatzy");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // -------------------------------------------------------------------------

    // txfValues shows the face values of the 5 dice.
    private final TextField[] txfValues = new TextField[5];
    // cbxHolds shows the hold status of the 5 dice.
    private final CheckBox[] cbxHolds = new CheckBox[5];
    // txfResults shows the obtained results.
    // For results not set yet, the possible result of 
    // the actual face values of the 5 dice are shown.
    private final TextField[] txfPoints = new TextField[dice.getResults().length];
    private final ArrayList<TextField> txfResults = new ArrayList<>(15);
    // Shows points in sums, bonus and total.
    private final TextField txfSumSame = new TextField();
    private final TextField txfBonus = new TextField();
    private final TextField txfSumOther = new TextField();
    private final TextField txfTotal = new TextField();
    private final int finalSameSum = 0;
    private final Label lblThrowCount = new Label();
    private final Button btnThrow = new Button(" Throw ");

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        // ---------------------------------------------------------------------

        GridPane dicePane = new GridPane();
        pane.add(dicePane, 0, 0);
        dicePane.setGridLinesVisible(false);
        dicePane.setPadding(new Insets(10));
        dicePane.setHgap(10);
        dicePane.setVgap(10);
        dicePane.setStyle("-fx-border-color: black");

        // add txfValues, chbHolds
        // TODO
        int chold = 0;
        int rhold = 1;
        for (int i = 0; i < cbxHolds.length; i++) {
            cbxHolds[i] = new CheckBox();
            dicePane.add(cbxHolds[i], chold, rhold);
            cbxHolds[i].setText("Hold");
            cbxHolds[i].setSelected(false);
            cbxHolds[i].setOnAction(event -> this.checkBoxAction());
            chold++;
        }

        int cVal = 0;
        int rVal = 0;
        for (int i = 0; i < txfValues.length; i++) {
            txfValues[i] = new TextField();
            txfValues[i].setFont(Font.font("Times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
            txfValues[i].setPrefHeight(80);
            txfValues[i].setPrefWidth(80);
            dicePane.add(txfValues[i], cVal, rVal);
            cVal++;
        }

        // add lblThrowCount and btnThrow
        btnThrow.setText("Throw " + +dice.getThrowCount());
        btnThrow.setOnAction(event -> btnThrowAction());
        dicePane.add(btnThrow, cbxHolds.length - 1, 2);

        // ---------------------------------------------------------------------

        GridPane scorePane = new GridPane();
        pane.add(scorePane, 0, 3);
        scorePane.setGridLinesVisible(false);
        scorePane.setPadding(new Insets(10));
        scorePane.setVgap(5);
        scorePane.setHgap(10);
        scorePane.setStyle("-fx-border-color: black");
        int width = 50; // width of the text fields

        // add labels for results

        String[] labelText = new String[]{"1-s", "2-s", "3-s", "4-s", "5-s", "6-s", "One Pair", "Two Pairs", "Three-same", "Four-same",
                "Full House", "Small Str.", "Large Str.", "Chance", "Yatzy"};

        int cLResult = 0;
        int cResult = 2;
        int rResult = 0;

        for (int i = 0; i < labelText.length; i++) {
            if (i == 6) {
                Label sum = new Label("Sum");
                TextField txfSum = new TextField();
                txfSum.setText(txfSumSame.getText());
                scorePane.add(sum, 3, 5);
                scorePane.add(txfSum, 4, 5);

                Label bonus = new Label("Bonus");
                TextField txfBonus1 = new TextField();
                txfBonus1.setText(txfBonus.getText());
                scorePane.add(bonus, 3, 6);
                scorePane.add(txfBonus1, 4, 6);
                rResult++;

            }
            Label Lresult = new Label(labelText[i]);
            scorePane.add(Lresult, cLResult, rResult);

            //Add the Textfields for the results.
            txfPoints[i] = new TextField();
            txfPoints[i].setOnMouseClicked(event -> clickResult(event));
            txfPoints[i].setPrefWidth(80);
            scorePane.add(txfPoints[i], cResult, rResult);
            rResult++;
        }
        Label sum = new Label("Sum");
        TextField txfSum = new TextField();
        txfSum.setText(txfSumOther.getText());
        scorePane.add(sum, 3, labelText.length);
        scorePane.add(txfSum, 4, labelText.length);

        Label total = new Label("Total");
        TextField txfBonus = new TextField();
        scorePane.add(total, 3, labelText.length + 1);
        scorePane.add(txfBonus, 4, labelText.length + 1);
        rResult++;


        // add txfResults,
        // TODO
        // add labels and text fields for sums, bonus and total.
        // TODO

    }

    // -------------------------------------------------------------------------

    // Create an action method for btnThrow's action.
    // Hint: Create small helper methods to be used in the action method.
    private void btnThrowAction() {
        boolean[] holds = new boolean[5];
        for (int i = 0; i < cbxHolds.length; i++) {
            holds[i] = cbxHolds[i].isSelected();
        }
        if (dice.getThrowCount() <= 3) {
            dice.throwDice(holds);
            btnThrow.setText("Throw " + dice.getThrowCount());
        }
        if (dice.getThrowCount() == 4) {
            btnThrow.setText("Click Result");
        }
        updateResult();
    }

    // -------------------------------------------------------------------------
    private void checkBoxAction() {
        for (int i = 0; i < cbxHolds.length; i++) {
            if (cbxHolds[i].isSelected()) {
                txfValues[i].setEditable(false);
                cbxHolds[i].setDisable(true);
            } else {
                txfValues[i].setEditable(true);
            }
        }

    }

    private void updateResult() {
        int[] v = dice.getValues();
        for (int i = 0; i < txfValues.length; i++) {
            String value = String.valueOf(v[i]);
            txfValues[i].setText(value);
        }
        int[] res = dice.getResults();
        for (int i = 0; i < dice.getResults().length; i++) {
            String res1 = String.valueOf(res[i]);
            if (!txfPoints[i].isDisable()) {
                txfPoints[i].setText(res1);
            } else {
            }
        }
        if (dice.getThrowCount() == 4) {
            btnThrow.setDisable(true);
        }
    }


    private void clickResult(Event event) {
        TextField txf = (TextField) event.getSource();
        txf.setDisable(true);
        int value = Integer.parseInt(txf.getText());
        int sumS = Integer.parseInt(txfSumSame.getText());
        txf.setText(value + sumS + "");
        sumofSame();
        sumOfOther();
        sumTotal();
        resetRound();
    }
    private void sumofSame() {
        int totalSum = Integer.parseInt(txfSumSame.getText());
        for (int i = 0; i < dice.getResults().length-9; i++) {
            totalSum += (dice.getResults()[i]);
        }
        txfSumSame.setText(String.valueOf(totalSum));
        if(totalSum >= 63){
            txfBonus.setText("50");
        }
    }

    private void sumOfOther(){
        int totalSum = Integer.parseInt(txfSumOther.getText());
        for (int i = 6; i < dice.getResults().length; i++) {
            totalSum += (dice.getResults()[i]);
        }
        txfSumOther.setText(String.valueOf(totalSum));
    }

    private void resetRound(){
        btnThrow.setDisable(false);
        dice.resetThrowCount();
        lblThrowCount.setText("Throw: " + dice.getThrowCount());
        for (int i = 0; i < txfValues.length; i++) {
            txfValues[i].clear();
            cbxHolds[i].setDisable(false);
            cbxHolds[i].setSelected(false);
        }
        for (int i = 0; i < txfPoints.length; i++) {
            if(!txfPoints[i].isDisable()){
                txfPoints[i].setText("0");
            }

        }
    }

    private void sumTotal(){
        int sumSame = Integer.parseInt(txfSumSame.getText());
        int sumOther = Integer.parseInt(txfSumOther.getText());
        int sumBonus = Integer.parseInt(txfBonus.getText());
        int sumTotal = Integer.parseInt(txfTotal.getText());

        sumTotal = sumOther + sumSame + sumBonus;

        txfTotal.setText(String.valueOf(sumTotal));

    }

    // Create a method for mouse click on one of the text fields in txfResults.
    // Hint: Create small helper methods to be used in the mouse click method.
    // TODO

}
