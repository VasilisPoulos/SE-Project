package gui;

import datamodel.PatternComponent;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class PatternViewController {

    /* The container in which we add the Pattern Fields */
    @FXML private Pane pane;

    /* Maps the name of the pattern part to the TextField corresponding to it */
    @FXML private HashMap<String, TextField> names = new HashMap<>();

    /* Maps the name of the pattern part to the TextArea corresponding to its contents */
    @FXML private HashMap<String, TextArea> contents = new HashMap<>();

    /**
     * Handles the click on the Save button, updating the name and content of each pattern part corresponding to the
     *  pattern currently being edited
     * @param event the click on the save button
     */
    public void handleSavePattern(ActionEvent event) {

        /* Hold each pattern part/section into an ArrayList */
        ArrayList<PatternComponent> partsList = Main.getCurrentPattern().getComponentsList();
        /* Iterate through the parts/sections and update their fields by using the user input */
        for (PatternComponent part: partsList) {
            part.setContents(contents.get(part.getName()).getText());
            part.setName(names.get(part.getName()).getText());
        }

        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        /* Go back to Pattern Language View scene */
        PLViewController c = (PLViewController) Main.getPatternView().getUserData();
        c.renderPLView(window);


    }

    /**
     * Handles the click on the Cancel button, leaving the Pattern Edit scene
     * @param event
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

        /* Create the GridPane which will hold the parts/sections of the pattern */
        GridPane gp = new GridPane();

        /* Clear instance HashMaps, we need to start fresh */
        this.names.clear();
        this.contents.clear();

        /* Iterate through the list of pattern parts/sections */
        for (PatternComponent part: partsList) {

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
            contents.setFont(Font.font("Deja Vu Mono", 12));

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
            gp.setHgap(20);
            gp.setVgap(20);

            col++;
        }

        gp.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        gp.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        gp.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        pane.getChildren().clear(); //remove previous GridPane
        pane.getChildren().add(gp); // add the GridPane
//        pane.setMaxHeight(Main.getWindow().getMaxHeight());


    }





}
