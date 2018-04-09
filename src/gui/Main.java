package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage window;        // For ease of use
    private Scene start;                // Opening scene

    public static Stage getWindow() {
        return window;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        Parent root = loader.load();

        window.getIcons().add(new Image("/assets/img/icon.png"));
        window.setTitle("Rocking Machines - Patterns Editor");
        start = new Scene(root, 800, 600);
        window.setScene(start);
        window.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}
