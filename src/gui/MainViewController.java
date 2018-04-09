package gui;
import datamodel.PatternLanguage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;


public class MainViewController {


    @FXML private TextField titleInput;


    /**
     * Spawns a modal in order to title the new pattern language
     * @param event The button click
     * @throws Exception if not able to show the dialog
     */
    @FXML
    void handleCreatePLTitle(ActionEvent event) throws Exception {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        dialog.initOwner(window);

        Parent modal = FXMLLoader.load(getClass().getResource("createPLTitle.fxml"));

        Scene dialogScene = new Scene(modal, 600, 400);
        dialog.setTitle("Rocking Machines - Patterns Editor");
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Closes the modal used to title the new pattern language, on pressing the Cancel button
     * @param event the click on the Cancel button
     */
    @FXML
    void handleCancelCreatePL(ActionEvent event) {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    /**
     * Switches to the view Pattern Language scene
     * @param newPL the new PatternLanguage object
     * @param window  the window this function was called from
     * @throws Exception when not able to create and show the new scene
     */
    private void viewNewPL(PatternLanguage newPL, Stage window) throws Exception {

        /* Load the new scene into a variable */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("plView.fxml"));

        Parent patternLanguageView = loader.load();
        Scene plView = new Scene(patternLanguageView, 800, 600);

        /* Close pop-up window and change the window variable to the primaryStage */
        window.close();
        window = Main.getWindow();

        /* Render the new scene into primaryStage */
        PLViewController c = loader.getController();
        c.setTitle(newPL.getTitle());
        window.setTitle("Rocking Machines - Patterns Editor");
        window.setScene(plView);
        window.show();
    }

    /**
     * Handles the Create button click when creating a new pattern language
     * @param event the click on the Create button
     * @throws Exception on failure of viewNewPL, called within
     */
    @FXML
    void handleCreatePL(ActionEvent event) throws Exception {

        /* Get the current window into a variable */
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        /* Read the new pattern language title from the text field provided
         * to the user, and create a new PatternLanguage object
         */
        String title = this.titleInput.getText();
        if (title == null || title.equals("") || title.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You did not enter a name for the new pattern language.");
            alert.setContentText("Are you sure you want to use a default title?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                alert.close();
                PatternLanguage newPL = new PatternLanguage(title);
                this.viewNewPL(newPL, window);
            }
            else {
                alert.close();
            }
        }
        else {
            PatternLanguage newPL = new PatternLanguage(title);
            this.viewNewPL(newPL, window);
        }


    }

}
