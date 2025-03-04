package com.thecodercat418.civtycoon;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Controller {
    @FXML
    public GridPane gp;
    public void initialize(){
        while (gp.getColumnConstraints().size() > 0) {
            gp.getColumnConstraints().remove(0);
        }
        while (gp.getRowConstraints().size() > 0) {
            gp.getRowConstraints().remove(0);
        }
        int x = 100;
        for(int i = 0; i < x; i++){
            gp.getRowConstraints().add(new RowConstraints(gp.getPrefHeight()/x));
            gp.getColumnConstraints().add(new ColumnConstraints(gp.getPrefWidth()/x));
        }
        for(int i = 0; i < x*x; i++){
            gp.
        }
        System.out.println(gp.getRowCount());
        System.out.println(gp.getPrefWidth());
        
    }
}
