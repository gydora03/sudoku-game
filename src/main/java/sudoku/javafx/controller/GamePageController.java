package sudoku.javafx.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;
import sudoku.javafx.SudokuApplication;
import sudoku.results.GameResult;
import sudoku.results.GameResultDao;
import sudoku.state.SudokuState;

import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Slf4j
public class GamePageController {

    @Inject
    private FXMLLoader fxmlLoader;

    @Inject
    private GameResultDao gameResultDao;

    private String playerName;
    private SudokuState gameState;
    private IntegerProperty steps = new SimpleIntegerProperty();
    private Instant startTime;

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private GridPane numbersGrid;

    public int pressedButton = 0;

    @FXML
    private Label stepsLabel;

    @FXML
    private Label stopWatchLabel;

    private Timeline stopWatchTimeline;

    @FXML
    private Button resetButton;

    @FXML
    private Button giveUpButton;

    private BooleanProperty gameOver = new SimpleBooleanProperty();

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }




    /*@FXML
    public void initialize() {
        stepsLabel.textProperty().bind(steps.asString());
        gameOver.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                log.info("Game is over");
                log.debug("Saving result to database...");
                gameResultDao.persist(createGameResult());
                stopWatchTimeline.stop();
            }
        });
        resetGame();
    }*/

    private void resetGame() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                SudokuState.currentState[i][j] = SudokuState.initialState[i][j];
            }
        }
        steps.setValue(0);
        stepsLabel.setText(steps.asString().get());
        startTime = Instant.now();
        gameOver.setValue(false);
        displayGameState();
        createStopWatch();
    }

    public void handleResetButton(ActionEvent actionEvent)  {
        log.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        log.info("Resetting game...");
        resetGame();
    }

    public void handleGiveUpButton(ActionEvent actionEvent) throws IOException {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                SudokuState.currentState[i][j] = SudokuState.initialState[i][j];
            }
        }
        gameOver.setValue(true);
        log.info("Loading high scores scene...");

        Parent root = fxmlLoader.load(getClass().getResource("/fxml/scoresPage.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        SudokuApplication.stage.getScene().setRoot(root);
    }

    public void handleClickOnNumbersGrid(MouseEvent mouseEvent) {
        int row = numbersGrid.getRowIndex((Node) mouseEvent.getSource());
        int col = numbersGrid.getColumnIndex((Node) mouseEvent.getSource());
        pressedButton = row*3+col+1;
        System.out.println("pressedButton: " + pressedButton);
    }


    public void handleClickOnSudokuGrid(MouseEvent mouseEvent) {

        int row = sudokuGrid.getRowIndex((Node) mouseEvent.getSource());
        int col = sudokuGrid.getColumnIndex((Node) mouseEvent.getSource());
        log.info("Index ({}, {}) is chosen.", row, col);
        if (SudokuState.initialState[row][col]==0) {
            SudokuState.currentState[row][col] = pressedButton;
            steps.setValue(steps.get()+1);
            stepsLabel.setText(steps.asString().get());
        }

        displayGameState();
        if (gameState.isGoal()) {
            gameOver.setValue(true);
            log.info("Player {} has solved the game in {} steps", playerName, steps.get());
            resetButton.setDisable(true);
            giveUpButton.setText("Game Over");
        }
    }


    private void displayGameState() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ImageView view = (ImageView) sudokuGrid.getChildren().get(i * 9 + j);
                if (view.getImage() != null) {
                    log.trace("Image({}, {}) = {}", i, j, view.getImage().getUrl());
                }
                if(SudokuState.currentState[i][j] == 0){
                    view.setImage(SudokuApplication.numberImages.get(0));
                } else {
                    view.setImage(SudokuApplication.numberImages.get(SudokuState.currentState[i][j]));
                }
            }
        }
    }



    private GameResult createGameResult() {
        GameResult result = GameResult.builder()
                .player(playerName)
                .solved(gameState.checkForSolution())
                .duration(Duration.between(startTime, Instant.now()))
                .steps(steps.get())
                .build();
        return result;
    }

    private void createStopWatch() {
        stopWatchTimeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            long millisElapsed = startTime.until(Instant.now(), ChronoUnit.MILLIS);
            stopWatchLabel.setText(DurationFormatUtils.formatDuration(millisElapsed, "HH:mm:ss"));
        }), new KeyFrame(javafx.util.Duration.seconds(1)));
        stopWatchTimeline.setCycleCount(Animation.INDEFINITE);
        stopWatchTimeline.play();
    }
}
