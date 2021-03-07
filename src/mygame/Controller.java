package mygame;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class Controller {

    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    Button b3;
    @FXML
    Button b4;
    @FXML
    Button b5;
    @FXML
    Button b6;
    @FXML
    Button b7;
    @FXML
    Button b8;
    @FXML
    Button b9;

    @FXML
    GridPane gameBoard;

    Button[] buttons;

    private boolean isFirstPlayer = true;
    private boolean isGameOver;

    public void buttonClickHandler(ActionEvent evt) {
        if (isGameOver) {
            return;
        }

        Button clickedButton = (Button) evt.getTarget();
        String buttonLabel = clickedButton.getText();

        if ("".equals(buttonLabel) && isFirstPlayer) {
            clickedButton.setText("X");
            isFirstPlayer = false;
        } else if ("".equals(buttonLabel) && !isFirstPlayer) {
            clickedButton.setText("O");
            isFirstPlayer = true;
        }

        isGameOver = find3InARow();  // is there a winner?
    }

    private boolean find3InARow() {
        //Row 1
        if ("" != b1.getText() && b1.getText() == b2.getText()
                && b2.getText() == b3.getText()) {
            highlightWinningCombo(b1, b2, b3);
            return true;
        }
        //Row 2
        if ("" != b4.getText() && b4.getText() == b5.getText()
                && b5.getText() == b6.getText()) {
            highlightWinningCombo(b4, b5, b6);
            return true;
        }
        //Row 3
        if ("" != b7.getText() && b7.getText() == b8.getText()
                && b8.getText() == b9.getText()) {
            highlightWinningCombo(b7, b8, b9);
            return true;
        }
        //Column 1
        if ("" != b1.getText() && b1.getText() == b4.getText()
                && b4.getText() == b7.getText()) {
            highlightWinningCombo(b1, b4, b7);
            return true;
        }
        //Column 2
        if ("" != b2.getText() && b2.getText() == b5.getText()
                && b5.getText() == b8.getText()) {
            highlightWinningCombo(b2, b5, b8);
            return true;
        }
        //Column 3
        if ("" != b3.getText() && b3.getText() == b6.getText()
                && b6.getText() == b9.getText()) {
            highlightWinningCombo(b3, b6, b9);
            return true;
        }
        //Diagonal 1
        if ("" != b1.getText() && b1.getText() == b5.getText()
                && b5.getText() == b9.getText()) {
            highlightWinningCombo(b1, b5, b9);
            return true;
        }
        //Diagonal 2
        if ("" != b3.getText() && b3.getText() == b5.getText()
                && b5.getText() == b7.getText()) {
            highlightWinningCombo(b3, b5, b7);
            return true;
        }
        return false;
    }

    private void highlightWinningCombo(Button first, Button second, Button third) {
        first.getStyleClass().add("winning-square");
        second.getStyleClass().add("winning-square");
        third.getStyleClass().add("winning-square");


        applyFadeTransition(first);
        applyFadeTransition(second);
        applyFadeTransition(third);
    }

    private void applyFadeTransition(Button winningButton) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), winningButton);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(10);
        ft.setAutoReverse(true);
        ft.play();
    }

    public void menuClickHandler(ActionEvent evt) {
        MenuItem clickedMenu = (MenuItem) evt.getTarget();
        String menuLabel = clickedMenu.getText();

        if ("Play".equals(menuLabel)) {
            ObservableList<Node> buttons =
                    gameBoard.getChildren();

            buttons.forEach(btn -> {
                ((Button) btn).setText("");
                btn.getStyleClass().remove("winning-button");
            });

            isFirstPlayer = true;
        }
    }

    public void playMenu(ActionEvent actionEvent) {
        setBoard(new String[] {
                "", "", "",
                "", "", "",
                "", "", ""});
        isGameOver = false;
    }

    private void setBoard(String[] cells) {
        for (int i = 0; i < cells.length; i++) {
            getBoard()[i].setText(cells[i]);
            getBoard()[i].getStyleClass().remove("winning-square");
        }
    }

    private Button[] getBoard() {
        if (buttons == null) {
            buttons = new Button[9];
            buttons[0] = b1;
            buttons[1] = b2;
            buttons[2] = b3;
            buttons[3] = b4;
            buttons[4] = b5;
            buttons[5] = b6;
            buttons[6] = b7;
            buttons[7] = b8;
            buttons[8] = b9;
        }
        return buttons;
    }
}


