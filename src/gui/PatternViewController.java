package gui;

import datamodel.PatternComponent;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class PatternViewController {

    @FXML private Pane pane;

    /* Holds the name of each pattern part and the text inside the TextField */
    @FXML
    HashMap<String, TextField> names = new HashMap<>();
    @FXML
    HashMap<String, TextArea> contents = new HashMap<>();

    public void handleSavePattern(ActionEvent event) {

        ArrayList<PatternComponent> partsList = Main.getCurrentPattern().getComponentsList();
        for (PatternComponent part: partsList) {
            part.setContents(contents.get(part.getName()).getText());
            part.setName(names.get(part.getName()).getText());
        }

        /* Get the current window into a variable */
        Stage window = Main.getWindow();

        PLViewController c = (PLViewController) Main.getPatternView().getUserData();
        c.renderPLView(window);


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

        this.names.clear();
        this.contents.clear();

        for (PatternComponent part: partsList) {

            if (col >= gpCols) {
                col = 0;
                row++;
            }

            String title = part.getName();
            VBox vbox = new VBox();

            TextField name = new TextField(title);
            TextArea contents = new TextArea(part.getContents());
            contents.setFont(Font.font("Deja Vu Mono", 12));

            /* Format textArea size so it's nice and big */
            contents.setWrapText(true);
            contents.setMaxSize(300, 200);
            int temp = (contents.getText().length() / contents.getPrefColumnCount()) + 1;
            contents.setPrefRowCount(temp>4?temp+1:4);

            HBox.setHgrow(name, Priority.ALWAYS);


            this.names.put(title, name);
            this.contents.put(title, contents);


            vbox.getChildren().clear();
            vbox.getChildren().add(name);
            vbox.getChildren().add(contents);
            vbox.setSpacing(10);

            gp.add(vbox, col, row);
            gp.setHgap(20);
            gp.setVgap(20);
            col++;
        }

        pane.getChildren().clear(); //remove previous GridPane
        pane.getChildren().add(gp); // add the GridPane
    }





}
