package com.thecodercat418.civtycoon;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    public GridPane gp;
    public GridPane gpmm;
    public void initialize(){
        GridController gc = new GridController(new World(20), gp, gpmm);
        gc.getTile(0,1);
        //gc.setZoom(5, 1, 0);
    }
}
