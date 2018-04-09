package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class PLViewController {

    @FXML
    void handleAddPattern() {

    }

    @FXML
    void handleLoadPattern() {

    }

    /**
     *
     * @param event
     * @throws Exception on failure to load fxml file
     */
    @FXML
    void handleChangePL(ActionEvent event) throws Exception {

        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        Parent root = loader.load();

        window.close();

        window.setTitle("Rocking Machines - Patterns Editor");
        Scene start = new Scene(root, 800, 600);
        window.setScene(start);
        window.show();
    }

}
