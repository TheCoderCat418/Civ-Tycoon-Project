package com.thecodercat418.civtycoon;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Controller {
    @FXML
    public GridPane gp;
    public GridPane gpmm;
    public void initialize(){
        GridController gc = new GridController(new World(10), gp, gpmm);
        gc.getTile(0,0);
        gc.setZoom(5, 1, 0);
    }
}
