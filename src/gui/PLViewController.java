package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class PLViewController {

    @FXML private Text plTitle;


    @FXML
    protected void setTitle(String title) {
        plTitle.setText(title);
    }

    /**
     * Switch to template selection scene
     * @param event the button click
     * @throws Exception on failure to load the fxml file
     */
    @FXML
    void handleAddPattern(ActionEvent event) throws Exception{
        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("templateView.fxml"));
        Parent root = loader.load();
        TemplateViewController c = loader.getController();
        c.populateTemplates();

        window.close();

        window.setTitle("Rocking Machines - Patterns Editor");
        Scene start = new Scene(root, 800, 600);
        window.setScene(start);
        window.show();
    }

    @FXML
    void handleLoadPattern() {
        //TODO: Decide if we're going to implement this (no reason imo)
    }

    /**
     * Returns to the starting scene, so we can change the pattern language
     * @param event the button click
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
