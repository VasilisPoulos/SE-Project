package gui;
import datamodel.Decorator;
import datamodel.LatexDecoratorFactory;
import datamodel.PatternComposite;
import datamodel.PatternLanguage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Integer.MAX_VALUE;


public class MainViewController {


    @FXML private TextField titleInput;
    @FXML private VBox fileContainer;
    private int selectedFileId;


    /**
     * Spawns a modal in order to title the new pattern language
     * @param event The button click
     * @throws Exception if not able to show the dialog
     */
    @FXML
    void handleCreatePLTitle(ActionEvent event) throws Exception {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        dialog.initOwner(window);

        Parent modal = FXMLLoader.load(getClass().getResource("createPLTitle.fxml"));

        Scene dialogScene = new Scene(modal, 600, 400);
        dialog.setTitle("Rocking Machines - Patterns Editor");
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Closes the modal used to title the new pattern language, on pressing the Cancel button
     * @param event the click on the Cancel button
     */
    @FXML
    void handleCancelCreatePL(ActionEvent event) {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    /**
     * Switches to the view Pattern Language scene
     * @param window  the window this function was called from
     * @throws Exception when not able to create and show the new scene
     */
    protected void viewNewPL(Stage window) throws Exception {

        /* Load the new scene into a variable */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("plView.fxml"));

        Parent patternLanguageView = loader.load();
        Scene plView = new Scene(patternLanguageView, 800, 600);

        PLViewController c = loader.getController();
        plView.setUserData(c);
        Main.setPlView(plView);

        c.renderPLView(window);

    }

    /**
     * Handles the Create button click when creating a new pattern language
     * @param event the click on the Create button
     * @throws Exception on failure of viewNewPL, called within
     */
    @FXML
    public void handleCreatePL(ActionEvent event) throws Exception {

        /* Get the current window into a variable */
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        /* Read the new pattern language title from the text field provided
         * to the user, and create a new PatternLanguage object
         */
        String title = this.titleInput.getText();
        if (title == null || title.equals("") || title.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You did not enter a name for the new pattern language.");
            alert.setContentText("Are you sure you want to use a default title?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK){
                alert.close();
                PatternLanguage newPL = new PatternLanguage(title);
                Main.setPl(newPL);
                this.viewNewPL(window);
            }
            else {
                alert.close();
            }
        }
        else {
            PatternLanguage newPL = new PatternLanguage(title);
            Main.setPl(newPL);
            this.viewNewPL(window);
        }


    }

    /**
     * Handles the Load button for loading a Pattern Language
     * @throws Exception on incorrect file structure
     * @throws IOException on failure to read from files
     */
    public void handleLoadPL() throws Exception {

        Path selectedFilePath = this.getFiles().get(this.selectedFileId);

        try {
            PatternComposite newPl = PatternLanguage.loadPatternLanguage(selectedFilePath);
            if (newPl instanceof Decorator) {
                LatexDecoratorFactory ldf = new LatexDecoratorFactory();
                Main.setPl(newPl);
                Main.getPl().decorateComponents(ldf);
            }
            else
                Main.setPl(newPl);
            // Switch to PLView
            this.viewNewPL(Main.getWindow());
        }
        catch (Exception e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Error Dialog");
            err.setHeaderText("There was an error reading from file " + selectedFilePath.toString());
            err.setContentText("Exception encountered: " + e.getMessage());
            err.showAndWait();
        }
    }

    /**
     * @return the list of Paths of all files in current dir and subdirs
     * @throws IOException
     */
    private List<Path> getFiles() throws IOException {
        // walk current directory and subdirs up to 2nd level and get files in list
        List<Path> files = Files.walk(Paths.get("./"), 2)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        return files;
    }

    /**
     * Returns to the starting scene, so we can change the pattern language
     * @param event the button click
     */
    @FXML
    public void handleCancelLoadPL(ActionEvent event) {

        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        window.setScene(Main.getStart());
    }

    /**
     * Populate the file selection scene with buttons corresponding pattern language files
     *
     * @throws IOException on error to read files
     */
    @FXML
    public void populateFiles() throws IOException {

        /* List holding the files in current dir */
        List<Path> files = this.getFiles();
        /* Dictate the number of columns there should be in the GridPane */
        List<Button> buttonList = new ArrayList<>();              // Collection to hold created Button objects

        for (Path p: files) {

            String filename = p.toString().substring(2);
            if (!filename.endsWith(".txt")) {
                continue;
            }
            Button btn = new Button(filename);              // Create the Button
            btn.setId(Integer.toString(files.indexOf(p)));  // Set button id to its title
            btn.setOnAction((e) -> this.handlePickFile(e)); // Set button handler to handlePickTemplate

            /* Make buttons the same size */
            btn.setMaxWidth(MAX_VALUE);
            HBox.setHgrow(btn, Priority.ALWAYS);
            btn.setPadding(new Insets(10));

            /* Finally, add the button to the list */
            buttonList.add(btn);
        }


        /* Add buttons to gui */
        fileContainer.setSpacing(20);
        fileContainer.getChildren().clear(); //remove all Buttons that are currently in the container
        fileContainer.getChildren().addAll(buttonList); // add new Buttons from the list

    }


    /**
     * Handles the click on a button representing a Pattern Language file, setting an instance variable
     * @param event the button click
     */
    private void handlePickFile(ActionEvent event){
        Control src = (Control)event.getSource();
        this.selectedFileId = Integer.parseInt(src.getId());
    }

    /**
     * Switch to file selection scene
     * @param event the button click
     * @throws Exception on failure to load the fxml file
     */
    @FXML
    void renderLoadPL(ActionEvent event) throws Exception {
        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fileView.fxml"));
        Parent fileViewRoot = loader.load();
        MainViewController c = loader.getController();
        c.populateFiles();

        Scene fileView = new Scene(fileViewRoot, 800, 600);

//        window.close();
//        window.setTitle("Rocking Machines - Patterns Editor");
        window.setScene(fileView);
//        window.show();
    }

}
