package gui;
import PatternsEditor.CreatePatternLanguage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class Controller {

    /**
     * Spawns a modal in order to title the new pattern language
     * @param event The button click
     * @throws Exception if not able to show the dialog
     */
    @FXML
    void createPLTitle(ActionEvent event) throws Exception {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        dialog.initOwner(window);

        Parent modal = FXMLLoader.load(getClass().getResource("createPLTitle.fxml"));

        Scene dialogScene = new Scene(modal, 600, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Closes the modal used to title the new pattern language, on pressing the Cancel button
     * @param event the click on the Cancel button
     */
    @FXML
    void cancelCreatePL(ActionEvent event) {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    /**
     * Sends the title given by user to the back end, when the Create button is pressed
     * @param event the click on the Create button
     */
    @FXML
    void createPL(ActionEvent event) {

    }

}
