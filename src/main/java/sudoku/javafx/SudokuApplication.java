package sudoku.javafx;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

    public static List<Image> numberImages;

    @Inject
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws Exception {
        log.info("Starting application...");
        //context
        Parent root = fxmlLoader.load(getClass().getResource("/fxml/welcomePage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/style.css");
        stage.setTitle("Sudoku");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
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
    }
}
