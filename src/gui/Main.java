package gui;

import datamodel.*;
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
    private static PatternComposite pl;                 // The Pattern Language we're working on
    private static PatternComposite currentPattern;     // The Pattern we're currently editing


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
        PatternComponent realPL;
        if (pl instanceof Decorator)
            realPL = pl.getComponentsList().get(0);
        else
            realPL = pl;
        return (PatternLanguage) realPL;
    }

    public static Decorator getPlDecorator() {
        if (pl instanceof Decorator)
            return (Decorator) pl;
        else
            return null;
    }

    public static void setPl(PatternComposite pl) {
        Main.pl = pl;
    }

    public static Pattern getCurrentPattern() {
        PatternComponent realPattern;
        if (currentPattern instanceof Decorator)
            realPattern = currentPattern.getComponentsList().get(0);
        else
            realPattern = currentPattern;
        return (Pattern) realPattern;
    }

    public static void setCurrentPattern(PatternComposite currentPattern) {
        Main.currentPattern = currentPattern;
    }
}
