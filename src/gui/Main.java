package gui;

import datamodel.TemplateFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage window;                        // For ease of use
    private static Scene start;                         // Opening scene
    private static Scene createPlTitle;                 // Popup window for naming new PL
    private static Scene plView;                        // Pattern Language view
    private static Scene templateView;                  // Template view
    private static TemplateFactory templateFactory;     // TemplateFactory object used for initialization

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


    private void initialize() {

        templateFactory = new TemplateFactory();
    }

    public static Stage getWindow() {
        return window;
    }

    public static Scene getStart() {
        return start;
    }

    public static void setStart(Scene startScene) {
        start = startScene;
    }

    public static Scene getCreatePltitle() {
        return createPlTitle;
    }

    public static void setCreatePltitle(Scene createPlTitleScene) {
        createPlTitle = createPlTitleScene;
    }

    public static Scene getPlView() {
        return plView;
    }

    public static void setPlView(Scene plViewScene) {
        plView = plViewScene;
    }

    public static Scene getTemplateView() {
        return templateView;
    }

    public static void setTemplateView(Scene templateViewScene) {
        templateView = templateViewScene;
    }

    public static TemplateFactory getTemplateFactory() {
        return templateFactory;
    }
}
