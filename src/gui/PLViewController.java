package gui;

import datamodel.Pattern;
import datamodel.PatternComponent;
import datamodel.PatternLanguage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PLViewController {

    @FXML private Text plTitle;
    @FXML private Pane patternContainer;
    private String selectedPatternId = null;


    /**
     * Sets the Text field as the title of the Pattern Language
     * @param title the name of the Pattern Language
     */
    @FXML
    protected void setTitle(String title) {
        plTitle.setText(title);
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

        Scene templateView = new Scene(templateViewRoot, 800, 600);
        Main.setTemplateView(templateView);

        window.close();
        window.setTitle("Rocking Machines - Patterns Editor");
        window.setScene(templateView);
        window.show();
    }

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


    public void populatePatterns() {
        ArrayList<PatternComponent> patternsList = Main.getPl().getComponentsList();
        int size = patternsList.size();
        int gpRows;
        int gpCols;
        if (size/3 == 0) {
            gpCols = size;
            gpRows = 1;
        }
        else {
            gpRows = (size / 3) + 1;
            gpCols = 3;
        }
        GridPane gp = new GridPane();

        int row = 0;
        int col = 0;

        for (PatternComponent pattern: patternsList) {

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
            col++;
        }

        patternContainer.getChildren().clear(); //remove previous GridPane
        patternContainer.getChildren().add(gp); // add the GridPane
    }

    private void handlePickPattern(ActionEvent event) {

        Control src = (Control)event.getSource();
        this.selectedPatternId = src.getId();

    }

    public void handleDeletePattern(ActionEvent event) {
        if (this.selectedPatternId == null || this.selectedPatternId.isEmpty() || this.selectedPatternId == "null") {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You did not select a pattern.");
            alert.setContentText("Please select a pattern to remove.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Are you sure you want to delete " + this.selectedPatternId + "?");
            alert.setContentText("All patterns named " + this.selectedPatternId + " will be deleted!");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Main.getPl().remove(this.selectedPatternId);
                this.selectedPatternId = null;
                this.renderPLView((Stage) ((Node)event.getSource()).getScene().getWindow());
            }
            else {
                alert.close();

            }

        }
    }

    // TODO
    public void handleEditPattern(ActionEvent event) throws Exception {

        Pattern pattern = Main.getTemplateFactory().getTemplatesList().get(selectedPatternId);
        System.out.println("ID: " + selectedPatternId + "\n" + pattern.toString());
        Main.setCurrentPattern(pattern);

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

    public void renderPLView(Stage window) {

        this.setTitle(Main.getPl().getName());
        this.populatePatterns();

        /* Close pop-up window and change the window variable to the primaryStage */
        window.close();
        window = Main.getWindow();

        /* Render the new scene into primaryStage */
        window.setScene(Main.getPlView());
        window.show();
    }

}
