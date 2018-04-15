package gui;

import datamodel.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class TemplateViewController {

    @FXML private VBox templateContainer;
    private PatternLanguage newPL;
    private String templateId = null;

    /**
     * Populate the template selection scene with buttons corresponding to pattern templates
     */
    @FXML
    public void populateTemplates() {

        List<HBox> buttonList = new ArrayList<>();              // Collection to hold created Button objects (HBox for styling)

        //TODO: get a hashmap from templateFactory and iterate
        Set templatesSet = Main.getTemplateFactory().getTemplatesList().keySet(); // Get all names so we can put them on buttons
        for (Object name: templatesSet) {

            String title = name.toString();
            HBox hbox = new HBox();                             // Create the HBox container for the button
            Button btn = new Button(title);                     // Create the Button
            btn.setId(title);                                   // Set button id to its title
            if (title == "Micro-Pattern")
                btn.setDefaultButton(true);
            btn.setOnAction((e) -> this.handlePickTemplate(e));  // Set button handler to handlePickTemplate

            /* Create left and right regions to center-align the button
             * and set their attributes so that they are dynamically set
             */
            Region regionLeft = new Region();
            Region regionRight = new Region();

            HBox.setHgrow(hbox, Priority.ALWAYS);
            HBox.setHgrow(regionLeft, Priority.ALWAYS);
            HBox.setHgrow(regionRight, Priority.ALWAYS);

            regionLeft.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            regionRight.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            regionLeft.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            regionRight.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

            regionLeft.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            regionRight.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            hbox.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

            btn.setPadding(new Insets(10));

            /* Add the button and regions to the HBox */
            hbox.getChildren().add(regionLeft);
            hbox.getChildren().add(btn);
            hbox.getChildren().add(regionRight);

            /* Finally, add the hbox containing the button to the list */
            buttonList.add(hbox);
        }


        /* Add buttons to gui */
        templateContainer.setSpacing(20);
        templateContainer.getChildren().clear(); //remove all Buttons that are currently in the container
        templateContainer.getChildren().addAll(buttonList); // add new Buttons from the list

    }

    /**
     * Returns to the new Pattern Language scene view on clicking Cancel
     * @param event the button click
     * @throws Exception on failure to load fxml file
     */
    @FXML
    public void handleCancel(ActionEvent event) throws Exception {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        MainViewController c = new MainViewController();
        c.viewNewPL(this.newPL, currentStage);
    }

    /**
     * TODO
     * @param event the button click
     */
    @FXML
    public void handlePickTemplate(ActionEvent event) {
        Control src = (Control)event.getSource();
        this.templateId = src.getId();

    }

    public void setNewPL(PatternLanguage newPL) {
        this.newPL = newPL;
    }

    public void handleCreate(ActionEvent event) {
        /* Get the current window into a variable */
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        if (this.templateId == null || this.templateId.isEmpty() || this.templateId == "null") {
            this.notifyDefault(window);
        }
        else {
            this.switchToPatternView(window);
        }
    }

    public void switchToPatternView(Stage window) {

        this.newPL.add(Main.getTemplateFactory().createTemplate(templateId));
        PLViewController c = (PLViewController) Main.getPlView().getUserData();
        c.renderPLView(window);
    }

    public void notifyDefault(Stage window) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You did not select a template for the new pattern.");
        alert.setContentText("Are you sure you want to use the default template \"Micro-Pattern\"?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            alert.close();
            this.templateId = "Micro-Pattern";
            switchToPatternView(window);
        }
        else {
            alert.close();
        }
    }

}
