package gui;

import datamodel.PatternLanguage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
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

            /*
             * Iterate through list of templates and create a button for each one
             * using its index as id and set the event handler
             */
            for (int i = 0; i < templatesList.size(); i++) { //iterate over every row returned
                String title = templatesList.get(i).toString(); // get title of template i
                Button btn = new Button(title);
                btn.setId(Integer.toString(i));
                btn.setOnAction((e) -> this.handlePickPattern(e));
                buttonList.add(btn);
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
