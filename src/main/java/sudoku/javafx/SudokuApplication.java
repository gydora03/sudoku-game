package sudoku.javafx;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import sudoku.results.GameResultDao;
import util.guice.PersistenceModule;

import javax.inject.Inject;
import java.util.List;

@Slf4j
public class SudokuApplication extends Application {

    private GuiceContext context = new GuiceContext(this, () -> List.of(
            new AbstractModule() {
                @Override
                protected void configure() {
                    install(new PersistenceModule("sudoku"));
                    bind(GameResultDao.class);
                }
            }
    ));

    public static Stage stage;

    @Inject
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws Exception {
        log.info("Starting application...");
        //SudokuApplication.stage = stage;
        Parent root = fxmlLoader.load(getClass().getResource("/fxml/welcomePage.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Sudoku");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
