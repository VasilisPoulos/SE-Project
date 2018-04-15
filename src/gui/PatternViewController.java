package gui;

import datamodel.Pattern;
import datamodel.PatternComponent;
import datamodel.PatternPart;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class PatternViewController {

    @FXML private Pane pane;

    /* Holds the name of each pattern part and the text inside the TextField */
    @FXML
    HashMap<String, TextField> fields;

    public void handleSavePattern(ActionEvent event) {
        return;
    }

    public void handleCancelPattern(ActionEvent event) {
        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        PLViewController c = (PLViewController) Main.getPatternView().getUserData();
        c.renderPLView(window);
    }

    public void populatePatternParts() {
        ArrayList<PatternComponent> partsList = Main.getCurrentPattern().getComponentsList();
        int size = partsList.size();
        int numCols = 2;
        int gpCols;
        if (size/numCols == 0) {
            gpCols = size;
        }
        else {
            gpCols = numCols;
        }
        GridPane gp = new GridPane();

        int row = 0;
        int col = 0;

        for (PatternComponent part: partsList) {

            if (col >= gpCols) {
                col = 0;
                row++;
            }

            String name = part.getName();
            VBox vbox = new VBox();

            Text text = new Text(name);
            TextField textField = new TextField(part.getContents());
            HBox.setHgrow(text, Priority.ALWAYS);
            HBox.setHgrow(textField, Priority.ALWAYS);

            vbox.getChildren().clear();
            vbox.getChildren().add(text);
            vbox.getChildren().add(textField);
            vbox.setSpacing(10);

            gp.add(vbox, col, row);
            gp.setHgap(20);
            gp.setVgap(20);
            col++;
        }
        HBox.setHgrow(gp, Priority.ALWAYS);

        pane.getChildren().clear(); //remove previous GridPane
        pane.getChildren().add(gp); // add the GridPane
    }





}
