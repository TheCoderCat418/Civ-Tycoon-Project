package com.thecodercat418.civtycoon;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Controller {
    @FXML
    public GridPane gp;
    public void initialize(){
        GridController gc = new GridController(new World(10), gp);
        gc.getTile(0,5);// NEED TO FIX NOT ALIGNED
    }
}
