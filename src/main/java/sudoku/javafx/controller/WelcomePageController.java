package sudoku.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField playerNameTextField;

    @FXML
    private Label errorLabel;

    public void startAction(ActionEvent actionEvent) throws IOException {
        if (playerNameTextField.getText().isEmpty()) {
            errorLabel.setText("Enter your name!");
            log.error("Missing player name!", playerNameTextField.getText());
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/gamePage.fxml"));
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    SudokuState.currentState[i][j] = SudokuState.initialState[i][j];
                }
                System.out.println();
            }
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            log.info("The players name is set to {}, loading game scene", playerNameTextField.getText());
        }
    }
}
