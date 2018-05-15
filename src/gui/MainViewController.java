package gui;
import datamodel.Pattern;
import datamodel.PatternLanguage;
import datamodel.PatternPart;
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
import java.util.stream.Stream;

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

    public void handleLoadPL() throws Exception {

        Path selectedFilePath = this.getFiles().get(this.selectedFileId);

        List<String> data = new ArrayList<String>();
        Stream<String> lines = Files.lines(selectedFilePath);
        lines
                .filter(line -> !line.isEmpty())
                .forEach(line -> data.add(line));
        lines.close();

        PatternLanguage newPL = parsePL(data);
        //TODO: Switch to PLView!
        Main.setPl(newPL);
        this.viewNewPL(Main.getWindow());
    }

    /**
     * Parse the Pattern Language from its text file
     * @param data the lines of the file.
     */
    private PatternLanguage parsePL(List<String> data) {
        // Placeholder variables
        PatternLanguage newPl = new PatternLanguage();
        Pattern currentPattern = new Pattern("");
        PatternPart currentPart = new PatternPart("");

        // Flags for whether we are still parsing a name
        boolean plNameFlag = false;
        boolean patternNameFlag = false;
        boolean partNameFlag = false;
        boolean iterationFlag = false;

        // Variable to hold string up to now
        String currentStr = "";
        // Temporary variable
        String tempStr;

        for (String i: data) {

            if (i.substring(0,4).equals("--- ")) {
                // Set contents of Pattern Part
                currentPart.setContents(currentStr);
                currentStr = "";

                // Pattern Language name
                if (i.endsWith(" ---")) {
                    currentStr += i.substring(4, i.length() - 4);
                    //  Create new Pattern Language
                    newPl.setName(currentStr);

                    currentStr = "";
                    plNameFlag = false;
                }
                else {
                    currentStr += i.substring(4);
                    plNameFlag = true;
                    iterationFlag = true;
                }

            }
            else if (i.substring(0, 3).equals("-- ")) {
                // Set contents of Pattern Part
                currentPart.setContents(currentStr);
                currentStr = "";
                // Pattern name
                if (i.endsWith(" --")) {
                    currentStr += i.substring(3, i.length() - 3);
                    // Create Pattern and add to PL
                    Pattern pattern = new Pattern(currentStr);
                    newPl.add(pattern);
                    currentPattern = pattern;
                    currentStr = "";
                    patternNameFlag = false;
                }
                else {
                    currentStr += i.substring(3);
                    patternNameFlag = true;
                    iterationFlag = true;
                }
            }
            else if (i.substring(0, 2).equals("- ")) {
                // Set contents of Pattern Part
                currentPart.setContents(currentStr);
                currentStr = "";
                // Pattern Part name
                if (i.endsWith(" -")) {
                    currentStr += i.substring(2, i.length() - 2);
                    // Create Pattern Part and add to current Pattern
                    PatternPart part = new PatternPart(currentStr);
                    currentPattern.add(part);
                    currentPart = part;

                    currentStr = "";
                    partNameFlag = false;
                }
                else {
                    currentStr += i.substring(2);
                    partNameFlag = true;
                    iterationFlag = true;
                }
            }
            else {
                // Pattern Part contents
                currentStr += i;
            }

            /* Check if we aren't done reading this string */
            if (plNameFlag) {
                if (i.endsWith(" ---"))
                    currentStr += i.substring(0, i.length() - 4);
                else {
                    if (iterationFlag)
                        iterationFlag = false;
                    else
                        currentStr += i;
                }
                //  Create new Pattern Language
                newPl.setName(currentStr);

                currentStr = "";
                plNameFlag = false;
            }
            else if (patternNameFlag) {
                if (i.endsWith(" --"))
                    currentStr += i.substring(0, i.length() - 3);
                else {
                    if (iterationFlag)
                        iterationFlag = false;
                    else
                        currentStr += i;
                }
                // Create Pattern and add to PL
                Pattern pattern = new Pattern(currentStr);
                newPl.add(pattern);
                currentPattern = pattern;
                currentStr = "";
                patternNameFlag = false;
            }
            else if (partNameFlag) {
                if (i.endsWith(" -"))
                    currentStr += i.substring(0, i.length() - 2);
                else {
                    if (iterationFlag)
                        iterationFlag = false;
                    else
                        currentStr += i;
                }
                // Create Pattern Part and add to current Pattern
                PatternPart part = new PatternPart(currentStr);
                currentPattern.add(part);
                currentPart = part;

                currentStr = "";
                partNameFlag = false;
            }

        }
        // Set contents of Pattern Part
        currentPart.setContents(currentStr);

        return newPl;
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
