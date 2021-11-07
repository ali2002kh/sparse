import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SparsePage.fxml")));
        primaryStage.setTitle("Sparse");
        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
