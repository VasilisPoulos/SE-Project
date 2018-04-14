package gui;

import datamodel.PatternComponent;
import datamodel.PatternLanguage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.util.ArrayList;

public class PLViewController {

    @FXML private Text plTitle;
    @FXML private Pane patternContainer;
    private String selectedPatternId = null;

    private PatternLanguage newPL;

    /**
     * Sets the Text field as the title of the Pattern Language
     * @param newPL the PatternLanguage object
     */
    @FXML
    protected void setTitle(PatternLanguage newPL) {
        this.newPL = newPL;
        plTitle.setText(newPL.getName());
    }

    /**
     * Switch to template selection scene
     * @param event the button click
     * @throws Exception on failure to load the fxml file
     */
    @FXML
    void handleAddPattern(ActionEvent event) throws Exception{
        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("templateView.fxml"));
        Parent templateViewRoot = loader.load();
        TemplateViewController c = loader.getController();
        c.populateTemplates();
        c.setNewPL(newPL);


        Scene templateView = new Scene(templateViewRoot, 800, 600);
        Main.setTemplateView(templateView);

        window.close();
        window.setTitle("Rocking Machines - Patterns Editor");
        window.setScene(templateView);
        window.show();
    }

//    @FXML
//    void handleLoadPattern() {
//        //TODO: Decide if we're going to implement this (no reason imo)
//    }

    /**
     * Returns to the starting scene, so we can change the pattern language
     * @param event the button click
     * @throws Exception on failure to load fxml file
     */
    @FXML
    void handleChangePL(ActionEvent event) throws Exception {

        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        window.close();

        window.setScene(Main.getStart());
        window.show();
    }

    /**
     * TODO: create a GridPane of dynamic size, align items, create a button for each pattern, put it in a GridPane cell
     */
    public void populatePatterns() {
        ArrayList<PatternComponent> patternsList = newPL.getComponentsList();
        int size = patternsList.size();
        int gpRows;
        int gpCols;
        if (size/3 == 0) {
            gpCols = size;
            gpRows = 1;
        }
        else {
            gpRows = (size / 3);
            gpCols = 3;
        }
        GridPane gp = new GridPane();

        int row = 0;
        int col = 0;

        for (PatternComponent pattern: patternsList) {

            if (row >= gpRows) {
                System.out.println("Patterns exceeded calculated number!");
            }

            if (col >= gpCols) {
                col = 0;
                row++;
            }

            String name = pattern.getName();
            Button btn = new Button(name);                      // Create the Button
            btn.setId(name);                                    // Set button id to its title
            btn.setOnAction((e) -> this.handlePickPattern(e));  // Set button handler to handlePickTemplate

            btn.setPadding(new Insets(10));

            gp.add(btn, col, row);
        }

        patternContainer.getChildren().clear(); //remove all Buttons that are currently in the container
        patternContainer.getChildren().add(gp); // add new Buttons from the list
    }

    private void handlePickPattern(ActionEvent event) {

        Control src = (Control)event.getSource();
        this.selectedPatternId = src.getId();
        System.out.println("Pattern " + selectedPatternId + " selected.");

    }

}
