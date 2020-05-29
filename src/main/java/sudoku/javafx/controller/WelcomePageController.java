package sudoku.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import sudoku.javafx.SudokuApplication;
import sudoku.state.SudokuState;

import javax.inject.Inject;
import java.io.IOException;

@Slf4j
public class WelcomePageController {

    @Inject
    private FXMLLoader fxmlLoader;

    public SudokuState sudokuState = new SudokuState();

    @FXML
    private TextField playerNameTextField;

    @FXML
    private Label errorLabel;

    public void startAction(ActionEvent actionEvent) throws IOException {
        if (playerNameTextField.getText().isEmpty()) {
            errorLabel.setText("Enter your name!");
            errorLabel.setTextFill(Color.web("#ff0000"));
            log.error("Missing player name!", playerNameTextField.getText());
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/gamePage.fxml"));
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    sudokuState.currentState[i][j] = SudokuState.initialState[i][j];
                }
            }
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/style.css");
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            SudokuApplication.stage.getScene().setRoot(root);
            //log.info("The players name is set to {}, loading game scene", playerNameTextField.getText());
        }
    }
}