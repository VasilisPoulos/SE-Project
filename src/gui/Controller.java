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
     */
    @FXML
    void createPLTitle(ActionEvent event) throws Exception {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        dialog.initOwner(window);

        Parent modal = FXMLLoader.load(getClass().getResource("createPLTitle.fxml"));

        Scene dialogScene = new Scene(modal, 600, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
