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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;
import sudoku.results.GameResult;
import sudoku.results.GameResultDao;
import sudoku.state.SudokuState;

import javax.inject.Inject;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

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
    private List<Image> numberImages;

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private GridPane numbersGrid;

    @FXML
    private Label stepsLabel;

    @FXML
    private Label stopWatchLabel;

    private Timeline stopWatchTimeline;

    @FXML Button buttonOne;
    @FXML Button buttonTwo;
    @FXML Button buttonThree;
    @FXML Button buttonFour;
    @FXML Button buttonFive;
    @FXML Button buttonSix;
    @FXML Button buttonSeven;
    @FXML Button buttonEight;
    @FXML Button buttonNine;
    @FXML Canvas canvas;

    int player_selected_row;
    int player_selected_col;

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
        numberImages = List.of(
                new Image(getClass().getResource("/images/empty.png").toExternalForm()),
                new Image(getClass().getResource("/images/1.png").toExternalForm()),
                new Image(getClass().getResource("/images/2.png").toExternalForm()),
                new Image(getClass().getResource("/images/3.png").toExternalForm()),
                new Image(getClass().getResource("/images/4.png").toExternalForm()),
                new Image(getClass().getResource("/images/5.png").toExternalForm()),
                new Image(getClass().getResource("/images/6.png").toExternalForm()),
                new Image(getClass().getResource("/images/7.png").toExternalForm()),
                new Image(getClass().getResource("/images/8.png").toExternalForm()),
                new Image(getClass().getResource("/images/9.png").toExternalForm())
        );
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
    }

    private void resetGame() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                SudokuState.currentState[i][j] = SudokuState.initialState[i][j];
            }
            System.out.println();
        }

        steps.setValue(0);
        stepsLabel.setText(steps.asString().get());
        startTime = Instant.now();
        gameOver.setValue(false);
        displayGameState();
        createStopWatch();
    }

    private void displayGameState() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ImageView view = (ImageView) sudokuGrid.getChildren().get(i * 9 + j);
                if (view.getImage() != null) {
                    log.trace("Image({}, {}) = {}", i, j, view.getImage().getUrl());
                }
                if(SudokuState.currentState[i][j] != 0){
                    view.setImage(numberImages.get(SudokuState.currentState[i][j]));
                }
            }
        }
    }*/


    public void buttonOnePressed(MouseEvent mouseEvent) {
        System.out.println("1 button");
    }

    /***
     * Method connected with a button onclick event handler
     */
    public void buttonTwoPressed(MouseEvent mouseEvent) {
        System.out.println("2 button");
    }

    /***
     * Method connected with a button onclick event handler
     */
    public void buttonThreePressed(MouseEvent mouseEvent) {
        System.out.println("3 button");
    }

    /***
     * Method connected with a button onclick event handler
     */
    public void buttonFourPressed(MouseEvent mouseEvent) {
        System.out.println("4 button");
    }

    /***
     * Method connected with a button onclick event handler
     */
    public void buttonFivePressed(MouseEvent mouseEvent) {
        System.out.println("5 button");
    }

    /***
     * Method connected with a button onclick event handler
     */
    public void buttonSixPressed(MouseEvent mouseEvent) {
        System.out.println("6 button");

    }

    /***
     * Method connected with a button onclick event handler
     */
    public void buttonSevenPressed(MouseEvent mouseEvent) {
        System.out.println("7 button");
    }

    /***
     * Method connected with a button onclick event handler
     */
    public void buttonEightPressed(MouseEvent mouseEvent) {
        System.out.println("8 button");
    }

    /***
     * Method connected with a button onclick event handler
     */
    public void buttonNinePressed(MouseEvent mouseEvent) {
        System.out.println("9 button");
    }


    /*public void initialize() {
        gameOver.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                log.info("Game is over");
                log.debug("Saving result to database...");
                stopWatchTimeline.stop();
                gameResultDao.persist(createGameResult());
            }
        });
        resetGame();
    }*/

    /*public void displayGameState() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = (TextField) gameTable.getChildren().get(i*9+j);
                if (SudokuState.currentState[i][j] != 0) {
                    textField.setText(String.valueOf(SudokuState.currentState[i][j]));
                }
            }
        }
    }*/

    /*private void resetGame() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                SudokuState.currentState[i][j] = SudokuState.initialState[i][j];
            }
            System.out.println();
        }

        steps.setValue(0);
        stepsLabel.setText(steps.asString().get());
        startTime = Instant.now();
        gameOver.setValue(false);
        displayGameState();
        createStopWatch();
    }*/



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
