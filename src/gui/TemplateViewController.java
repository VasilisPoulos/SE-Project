package gui;

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
    private String templateId = null;

    /**
     * Populate the template selection scene with buttons corresponding to pattern templates
     */
    @FXML
    public void populateTemplates() {

        List<HBox> buttonList = new ArrayList<>();              // Collection to hold created Button objects (HBox for styling)

        Set templatesSet = Main.getTemplateFactory().getTemplatesList().keySet(); // Get all names so we can put them on buttons
        for (Object name: templatesSet) {

            String title = name.toString();
            HBox hbox = new HBox();                             // Create the HBox container for the button
            Button btn = new Button(title);                     // Create the Button
            btn.setId(title);                                   // Set button id to its title
//            if (title.equals("Micro-Pattern"))
//                btn.setDefaultButton(true);
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
        PLViewController c = new PLViewController();
        c.renderPLView(currentStage);
    }

    /**
     * Handles the click on a Button representing a pattern, and sets an instance variable to that pattern
     * @param event the button click
     */
    @FXML
    private void handlePickTemplate(ActionEvent event) {
        Control src = (Control)event.getSource();
        this.templateId = src.getId();

    }

    /**
     * Handles the click on the create Button, calling notifyDefault() when no template was picked,
     *  and switchToPatternView() otherwise
     * @param event the click on the Create button
     */
    @FXML
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

    /**
     * Changes to the Pattern Language view
     * @param window the current window
     */
    private void switchToPatternView(Stage window) {

        Main.getPl().add(Main.getTemplateFactory().createTemplate(templateId));
        PLViewController c = (PLViewController) Main.getPlView().getUserData();
        c.renderPLView(window);
    }

    /**
     * Spawns a confirmation dialog, asking the user if they want to continue using the default template
     * @param window the current window
     */
    private void notifyDefault(Stage window) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You did not select a template for the new pattern.");
        alert.setContentText("Are you sure you want to use the default template \"Micro-Pattern\"?");

        Optional<ButtonType> result = alert.showAndWait();

        /* Set the template to the default one and call switchToPatternView() if user clicks on OK */
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
