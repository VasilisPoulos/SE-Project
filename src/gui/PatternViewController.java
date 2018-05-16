package gui;

import datamodel.*;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class PatternViewController {

    /* The container in which we add the Pattern Fields */
    @FXML private Pane pane;

    @FXML private TextField patternTitleInput;

    /* Maps the name of the pattern part to the TextField corresponding to it */
    @FXML private HashMap<String, TextField> names = new HashMap<>();

    /* Maps the name of the pattern part to the TextArea corresponding to its contents */
    @FXML private HashMap<String, TextArea> contents = new HashMap<>();

    /**
     * Handles the click on the Save button, updating the name of the pattern, as well as
     * the name and content of each pattern part corresponding to the pattern currently being edited
     * @param event the click on the save button
     * @throws Exception on failure to find the pattern in the pattern language
     */
    public void handleSavePattern(ActionEvent event) throws Exception {

        String newName = this.patternTitleInput.getText();

        /*
         * Update pattern name according to its corresponding user input field
         * Search for the pattern in the pattern language and tag it's index
         * Then check if the new name already exists in the pattern language
         */
        int index = -1;
        ArrayList<PatternComponent> list = Main.getPl().getComponentsList();
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getName().equals(Main.getCurrentPattern().getName()))
                index = i;
        }
        if (index == -1)
            throw new Exception("Error: Pattern not found in the pattern language. Please report this incident.");
        PatternComponent pat = list.get(index);

        /* Find out if the new pattern name already exists */
        Boolean flag = true;
        for (PatternComponent i: Main.getPl().getComponentsList()) {

            if (i.getName().equals(newName)) {
                if (i.equals(list.get(index)))
                    continue;
                flag = false;
                break;
            }

        }

        /* Handle the new pattern name */
        if (flag) {
            pat.setName(newName);


            /* Hold each pattern part/section into an ArrayList */
            ArrayList<PatternComponent> partsList = Main.getCurrentPattern().getComponentsList();
            /* Iterate through the parts/sections and update their fields by using the user input */
            for (PatternComponent part : partsList) {
                part.setContents(contents.get(part.getName()).getText());
                part.setName(names.get(part.getName()).getText());
            }

            /* Get the current window into a variable */
            Stage window = Main.getWindow();

            /* Go back to Pattern Language View scene */
            PLViewController c = (PLViewController) Main.getPatternView().getUserData();
            c.renderPLView(window);
        }
        else {
            /* Show error dialog if pattern name already exists */
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Name not saved: There is already a pattern named \"" + newName + "\"");
            alert.setContentText("Would you like to save the rest of the changes?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                /* Hold each pattern part/section into an ArrayList */
                ArrayList<PatternComponent> partsList = Main.getCurrentPattern().getComponentsList();
                /* Iterate through the parts/sections and update their fields by using the user input */
                for (PatternComponent part : partsList) {
                    part.setContents(contents.get(part.getName()).getText());
                    part.setName(names.get(part.getName()).getText());
                }
            }
            else {
                alert.close();

            }
            PLViewController c = (PLViewController) Main.getPatternView().getUserData();
            c.handleEditPattern(event);
        }

    }

    /**
     * Handles the click on the Cancel button, leaving the Pattern Edit scene
     * @param event the click on the cancel button
     */
    public void handleCancelPattern(ActionEvent event) {
        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        /* Go back to the Pattern Language View scene */
        PLViewController c = (PLViewController) Main.getPatternView().getUserData();
        c.renderPLView(window);
    }

    /**
     * Populates the Pane container with a GridPane containing, in each cell, a TextField and a TextArea,
     *  corresponding to the name and contents of the pattern part/section respectively.
     *
     * The fields are filled with the current names and contents so the user can see and change what
     * they see fit.
     */
    public void populatePatternParts() {

        /* Holds the individual parts/sections of the pattern we're currently editing */
        ArrayList<PatternComponent> partsList = Main.getCurrentPattern().getComponentsList();

        /* Dictate the column number of the GridPane */
        int size = partsList.size();
        int numCols = 2;
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

        /* Create the GridPanes which will hold the pattern rename and the parts/sections of the pattern */
        GridPane gp = new GridPane();

        /* Clear instance HashMaps, we need to start fresh */
        this.names.clear();
        this.contents.clear();

        /*
         * Pattern name input field
         */
        String patternTitle = Main.getCurrentPattern().getName();

        VBox renameVbox = new VBox();
        Label rename = new Label("Rename Pattern: ");
        rename.setPadding(new Insets(10));
        rename.setFont(Font.font("DejaVu Sans Mono", 18));

        TextField renameInput = new TextField(patternTitle);
        renameInput.setPadding(new Insets(10));
        this.patternTitleInput = renameInput;

        GridPane.setHalignment(rename, HPos.CENTER);
        GridPane.setHalignment(renameInput, HPos.CENTER);

        renameVbox.getChildren().clear();
        renameVbox.getChildren().addAll(rename, renameInput);

        gp.add(renameVbox, 0, 0, 2, 1);

        row++;


        /* Iterate through the list of pattern parts/sections */
        for (PatternComponent partUnchecked: partsList) {

            PatternComponent part;
            if (partUnchecked instanceof Decorator)
                part = ((PatternComposite) partUnchecked).getComponentsList().get(0);
            else
                part = partUnchecked;

            if (col >= gpCols) {
                col = 0;
                row++;
            }
            /* Hold the pattern part name for mapping the user input to the respective fields */
            String title = part.getName();

            /* Create the VBox holding the input fields */
            VBox vbox = new VBox();
            TextField name = new TextField(title);
            TextArea contents = new TextArea(part.getContents());

            contents.setFont(Font.font("DejaVu Sans Mono", 12));

            /* Format textArea size so it's nice and big */
            contents.setWrapText(true);
            contents.setMaxSize(300, 100);
            int temp = (contents.getText().length() / contents.getPrefColumnCount()) + 1;
            contents.setPrefRowCount(
                    temp>4 ? temp+1 : 4);

            HBox.setHgrow(name, Priority.ALWAYS);

            /* Map the input fields to the name(title) of the pattern part/section */
            this.names.put(title, name);
            this.contents.put(title, contents);


            vbox.getChildren().clear();
            vbox.getChildren().add(name);
            vbox.getChildren().add(contents);
            vbox.setSpacing(5);

            gp.add(vbox, col, row);
            GridPane.setHalignment(vbox, HPos.CENTER);

            col++;
        }
        gp.setHgap(20);
        gp.setVgap(20);

        gp.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        gp.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        gp.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        pane.getChildren().clear(); //remove previous GridPane
        pane.getChildren().add(gp); // add the GridPane


    }





}
