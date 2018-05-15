package gui;

import datamodel.Pattern;
import datamodel.PatternComponent;
import datamodel.PatternLanguage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.MAX_VALUE;

public class PLViewController {

    @FXML private Text plTitle;
    @FXML private Pane patternContainer;
    private String selectedPatternId = null;


    /**
     * Sets the Text field as the title of the Pattern Language
     * @param title the name of the Pattern Language
     */
    @FXML
    private void setTitle(String title) {
        plTitle.setText(title);
    }

    /**
     * Switch to template selection scene
     * @param event the button click
     * @throws Exception on failure to load the fxml file
     */
    @FXML
    void handleAddPattern(ActionEvent event) throws Exception {
        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("templateView.fxml"));
        Parent templateViewRoot = loader.load();
        TemplateViewController c = loader.getController();
        c.populateTemplates();

        Scene templateView = new Scene(templateViewRoot, 800, 600);

        window.close();
        window.setTitle("Rocking Machines - Patterns Editor");
        window.setScene(templateView);
        window.show();
    }

    /**
     * Returns to the starting scene, so we can change the pattern language
     * @param event the button click
     */
    @FXML
    public void handleChangePL(ActionEvent event) {

        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        window.close();

        window.setScene(Main.getStart());
        window.show();
    }


    /** Populates the Pane container with a GridPane holding a button for each pattern in the pattern language */
    private void populatePatterns() {

        /* ArrayList holding the patterns in the pattern language */
        ArrayList<PatternComponent> patternsList = Main.getPl().getComponentsList();

        /* Dictate the number of columns there should be in the GridPane */
        int size = patternsList.size();
        int numCols = 3;
        int gpCols;
        if (size/numCols == 0) {
            gpCols = size;
        }
        else {
            gpCols = numCols;
        }

        /* Initialize row and column index to zero */
        int row = 0;
        int col = 0;

        /* Create the GridPane which will hold the Buttons (patterns) */
        GridPane gp = new GridPane();


        /* Iterate through the list of patterns in the pattern language */
        for (PatternComponent pattern: patternsList) {

            if (col >= gpCols) {
                col = 0;
                row++;
            }

            /* Pattern name is used as the Button name & id */
            String name = pattern.getName();
            Button btn = new Button(name);                      // Create the Button
            btn.setId(name);                                    // Set button id to its title
            btn.setOnAction((e) -> this.handlePickPattern(e));  // Set button handler to handlePickTemplate

            /* Set the buttons to be the same size */
            btn.setMaxWidth(MAX_VALUE);
            btn.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            HBox.setHgrow(btn, Priority.ALWAYS);
            btn.setAlignment(Pos.CENTER);
            btn.setPadding(new Insets(10));

            gp.add(btn, col, row);
            GridPane.setHalignment(btn, HPos.CENTER);
            col++;
        }
        gp.setVgap(20);
        gp.setHgap(20);

        patternContainer.getChildren().clear(); //remove previous GridPane
        patternContainer.getChildren().add(gp); // add the GridPane
    }

    /**
     * Handles the click on a button representing a pattern, setting an instance variable
     * @param event the button click
     */
    private void handlePickPattern(ActionEvent event) {
        Control src = (Control)event.getSource();
        this.selectedPatternId = src.getId();
    }

    /**
     * Handles the click on the Delete button, tries to delete the pattern from the pattern language
     * Shows an error if no pattern is picked
     * Shows a warning before deleting
     * @param event the button click
     */
    public void handleDeletePattern(ActionEvent event) {
        /* Show error dialog if no pattern is picked for removal */
        if (this.selectedPatternId == null || this.selectedPatternId.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You did not select a pattern.");
            alert.setContentText("Please select a pattern to remove.");
            alert.showAndWait();
        }
        /* Show a warning dialog before deleting the pattern */
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Are you sure you want to delete " + this.selectedPatternId + "?");
            alert.setContentText("All patterns named " + this.selectedPatternId + " will be deleted!");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Main.getPl().remove(this.selectedPatternId);
                this.selectedPatternId = null;
                this.renderPLView((Stage) ((Node)event.getSource()).getScene().getWindow());
            }
            else {
                alert.close();

            }

        }
    }

    /**
     * Handles the click on the Edit button, tries to enter the PatternView scene
     * @param event the button click
     * @throws Exception on failure to load the FXML file or failure to find the pattern in the Pattern Language
     */
    public void handleEditPattern(ActionEvent event) throws Exception {

        /* Show an error dialog if no pattern was chosen for editing */
        if (this.selectedPatternId == null || this.selectedPatternId.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You did not select a pattern.");
            alert.setContentText("Please select a pattern to edit.");
            alert.showAndWait();
        }
        else {
            /* hold the pattern the user wants to edit in local variable and set it in the static variable
             * if the pattern is not found (unexpected behaviour), throw an exception
             */
            Boolean flag = true;
            for (PatternComponent i: Main.getPl().getComponentsList()) {
                if (i.getName().equals(this.selectedPatternId)) {
                    Main.setCurrentPattern((Pattern) i);
                    flag = false;
                }
            }
            if (flag) {
                throw new Exception("Could not find pattern. Unexpected behaviour. Please report this issue.");
            }

            /* Get the current window into a variable */
            Stage window = Main.getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("patternView.fxml"));
            Parent patternViewRoot = loader.load();
            PatternViewController c = loader.getController();
            c.populatePatternParts();


            Scene patternView = new Scene(patternViewRoot, 800, 600);
            patternView.setUserData(this);
            Main.setPatternView(patternView);


            window.close();
            window.setTitle("Rocking Machines - Patterns Editor");
            window.setScene(patternView);
            window.show();
        }
    }

    /**
     * Renders the Pattern Language scene
     * @param window the window we want to change into the PLView scene
     */
    public void renderPLView(Stage window) {

        this.setTitle(Main.getPl().getName());
        this.populatePatterns();

        this.selectedPatternId = null;
        /* Close pop-up window and change the window variable to the primaryStage */
        window.close();
        window = Main.getWindow();

        /* Render the new scene into primaryStage */
        window.setScene(Main.getPlView());
        window.show();
    }

    /**
     * Handles events on the Save Pattern Language button
     * @param event the button click
     * @throws IOException on file error
     */
    public void handleSavePL(ActionEvent event) throws IOException {
        PatternLanguage pl = Main.getPl();
        // Ask the user whether to save empty Pattern Language
        if (pl.getComponentsList().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification Dialog");
            alert.setHeaderText("Pattern language \"" + pl.getName() + "\" has no patterns.");
            alert.setContentText("Save anyway?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                this.savePL(pl);
                alert.close();
            }
            else {
                alert.close();
            }
        }
        else {
            this.savePL(pl);
        }
    }

    /**
     * Saves the Pattern Language to a text file
     *
     * @throws IOException on file error
     */
    private void savePL(PatternLanguage pl) throws IOException {
        Path fp = Paths.get("./" + pl.getName() + ".txt");
        Path tmp = Paths.get("./.tmp/");

        if (Files.exists(fp)) {
            if (Files.exists(tmp)){
                Files.walk(tmp)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
            Files.deleteIfExists(tmp);

            Files.createDirectory(tmp);

            // create a backup of the file
            Files.copy(fp, tmp.resolve(fp.getFileName()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification Dialog");
            alert.setHeaderText("File \"" + fp.toString() + "\" already exists.");
            alert.setContentText("Overwrite?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Delete the file
                Files.deleteIfExists(fp);
                alert.close();
            }
            else {
                alert.close();
                // Ask user for a new name
                TextInputDialog dialog = new TextInputDialog(pl.getName() + "-1");
                dialog.setTitle("Text Input Dialog");
                dialog.setHeaderText("You need to input a new name for the file.");
                dialog.setContentText("Please enter a name:");

                Optional<String> name = dialog.showAndWait();
                if (result.isPresent()){
                    Path p = Paths.get("./" + name.get() + ".txt");
                    if (name.get().equals(pl.getName()) || Files.exists(p)) {
                        dialog.close();
                        Alert err = new Alert(Alert.AlertType.ERROR);
                        err.setTitle("Error Dialog");
                        err.setHeaderText("Patten Language not saved.");
                        err.setContentText("File \"" + p.toString() + "\" already exists.");
                        err.showAndWait();
                    }
                    else {
                        fp = p;
                        dialog.close();
                    }
                }
            }
        }
        try {
            pl.saveName(fp);
            pl.saveContents(fp);
        }
        catch (IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());
            // Replace erroneous file with backup
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("There was an error writing \"" + fp.toString() + "\" to disk.");
            alert.setContentText("Recover previous version of the file?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Files.deleteIfExists(fp);
                Path backup = Paths.get(tmp.toString().concat(fp.toString()));
                Path curDir = Paths.get("./");
                Files.copy(backup, curDir.resolve(backup.getFileName()));
                alert.close();
            }
            else {
                alert.close();
            }

        }
        // Delete ./.tmp directory and files
        if (Files.exists(tmp)){
            Files.walk(tmp)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
        Files.deleteIfExists(tmp);
    }


}
