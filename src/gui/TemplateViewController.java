package gui;

import datamodel.PatternLanguage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TemplateViewController {

    @FXML private VBox templateContainer;
    private PatternLanguage newPL;

    /**
     * Populate the template selection scene with buttons corresponding to pattern templates
     */
    @FXML
    public void populateTemplates() {

        List<HBox> buttonList = new ArrayList<>(); //our Collection to hold created Button objects (HBox for styling)

//            ArrayList<Template> templatesList = getTemplatesList();

            ArrayList templatesList = new ArrayList(); // For now
            templatesList.add("asdf");
            templatesList.add("qwerty");

            /*
             * Iterate through list of templates and create a button for each one
             * using its index as id and set the event handler
             */
            for (int i = 0; i < templatesList.size(); i++) { //iterate over every row returned

                String title = templatesList.get(i).toString();     // get title of template i
                HBox hbox = new HBox();                             // Create the HBox container for the button
                Button btn = new Button(title);                     // Create the Button
                btn.setId(Integer.toString(i));                     // Set button id to its index
                btn.setOnAction((e) -> this.handlePickPattern(e));  // Set button handler to handlePickPattern

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

    @FXML
    public void handlePickPattern(ActionEvent event) {
        Control src = (Control)event.getSource();
        Integer buttonId = Integer.parseInt(src.getId());
        System.out.println("Button with id " + Integer.toString(buttonId) + " clicked.");
//        Pattern newPattern = TemplateFactory.getTemplatesList(buttonId).clone();

    }

    public void setNewPL(PatternLanguage newPL) {
        this.newPL = newPL;
    }

}
