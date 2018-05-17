package gui;

import datamodel.Decorator;
import datamodel.LatexDecoratorFactory;
import datamodel.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class TemplateViewController {

    @FXML private VBox templateContainer;
    private String templateId = null;

    /**
     * Populate the template selection scene with buttons corresponding to pattern templates
     */
    @FXML
    public void populateTemplates() {

        List<Button> buttonList = new ArrayList<>();              // Collection to hold created Button objects

        Set templatesSet = Main.getTemplateFactory().getTemplatesList().keySet(); // Get all names so we can put them on buttons
        for (Object name: templatesSet) {

            String title = name.toString();
            Button btn = new Button(title);                     // Create the Button
            btn.setId(title);                                   // Set button id to its title
            btn.setOnAction((e) -> this.handlePickTemplate(e)); // Set button handler to handlePickTemplate

            /* Make buttons the same size */
            btn.setMaxWidth(MAX_VALUE);
            HBox.setHgrow(btn, Priority.ALWAYS);
            btn.setPadding(new Insets(10));

            /* Finally, add the button to the list */
            buttonList.add(btn);
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
        c.viewNewPL(currentStage);
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
            Main.getPl().add(Main.getTemplateFactory().createTemplate(this.templateId));
            this.switchToPatternView(window);
        }
    }

    /**
     * Changes to the Pattern Language view
     * @param window the current window
     */
    private void switchToPatternView(Stage window) {

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
            Main.getPl().add(Main.getTemplateFactory().createTemplate(this.templateId));
            switchToPatternView(window);
        }
        else {
            alert.close();
        }
    }

    @FXML
    public void handleCreatePattern(ActionEvent event) {
        Pattern pattern = new Pattern("");
        TextInputDialog dialog = new TextInputDialog("Pattern Name");
        dialog.setTitle("Add New Pattern");
        dialog.setHeaderText("Pattern Name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            pattern.setName(name);
            if (Main.getPlDecorator() != null) {
                LatexDecoratorFactory ldf = new LatexDecoratorFactory();
                Decorator newPattern = ldf.createPatternDecorator(pattern);
                Main.getPlDecorator().add(newPattern);
                Main.setCurrentPattern(newPattern);
            }
            else {
                Main.getPl().add(pattern);
                Main.setCurrentPattern(pattern);
            }
            dialog.close();
        });
        this.switchToPatternView(Main.getWindow());

    }



}
