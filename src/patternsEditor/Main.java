package patternsEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;               // For ease of use
    Scene start;                // Opening scene
    Scene new_pl;               // New pattern language scene
    Scene pick_template;        // Pick a template scene
    Scene edit_pattern;         // Edit pattern fields scene
    Scene pattern_language;     // Pattern Language view scene

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        window.setTitle("Rocking Machines - Patterns Editor");
        start = new Scene(root, 800, 600);
        window.setScene(start);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
