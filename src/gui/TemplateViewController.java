package gui;

import datamodel.PatternLanguage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

        /* TODO: Make buttonList into a list of Hboxes, so that they are center-aligned
         * TODO: Style buttons, add paddings etc. */
        List<Button> buttonList = new ArrayList<>(); //our Collection to hold newly created Buttons

//            ArrayList<Template> templatesList = getTemplatesList();

            ArrayList templatesList = new ArrayList(); // For now
            templatesList.add("asdf");
            templatesList.add("qwerty");

            for (int i = 0; i < templatesList.size(); i++) { //iterate over every row returned
                String title = templatesList.get(i).toString(); //extract button text, adapt the String to the column name that you are interested in
                buttonList.add(new Button(title));

            }
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

    public void setNewPL(PatternLanguage newPL) {
        this.newPL = newPL;
    }

}
