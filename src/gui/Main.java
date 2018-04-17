package gui;

import datamodel.Pattern;
import datamodel.PatternLanguage;
import datamodel.TemplateFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage window;                        // The primaryStage
    private static Scene start;                         // Opening scene
    private static Scene plView;                        // Pattern Language view
    private static Scene patternView;                   // Pattern Edit view
    private static TemplateFactory templateFactory;     // TemplateFactory object used for initialization
    private static PatternLanguage pl;                  // The Pattern Language we're working on
    private static Pattern currentPattern;              // The Pattern we're currently editing


    /**
     * Runs the application, creating a window with the Start scene and running initialize()
     * @param primaryStage the root Stage object
     */
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

        this.initialize();

    }

    public static void main(String[] args) {
        launch(args);
    }

    /** Creates a new TemplateFactory object */
    private void initialize() {
        Main.templateFactory = new TemplateFactory();
    }

    /*************************
     * Getters and Setters   *
     *************************/

    /**
     *
     * @return window
     */
    public static Stage getWindow() {
        return window;
    }

    public static Scene getStart() {
        return start;
    }

    public static Scene getPlView() {
        return plView;
    }

    public static void setPlView(Scene plViewScene) {
        Main.plView = plViewScene;
    }

    public static Scene getPatternView() {
        return patternView;
    }

    public static void setPatternView(Scene patternView) {
        Main.patternView = patternView;
    }

    public static TemplateFactory getTemplateFactory() {
        return templateFactory;
    }

    public static PatternLanguage getPl() {
        return pl;
    }

    public static void setPl(PatternLanguage pl) {
        Main.pl = pl;
    }

    public static Pattern getCurrentPattern() {
        return currentPattern;
    }

    public static void setCurrentPattern(Pattern currentPattern) {
        Main.currentPattern = currentPattern;
    }
}
