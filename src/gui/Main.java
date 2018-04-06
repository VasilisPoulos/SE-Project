package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage window;               // For ease of use
    private Scene start;                // Opening scene
    private Scene new_pl;               // New pattern language scene
    private Scene pick_template;        // Pick a template scene
    private Scene edit_pattern;         // Edit pattern fields scene
    private Scene pattern_language;     // Pattern Language view scene

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
//        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        Parent root = loader.load();

        window.setTitle("Rocking Machines - Patterns Editor");
        start = new Scene(root, 800, 600);
        window.setScene(start);
        window.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
