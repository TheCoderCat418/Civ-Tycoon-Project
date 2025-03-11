package com.thecodercat418.civtycoon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    public GridPane gp;
    public GridPane gpmm;
    public ToggleButton RZ;
    public ToggleButton CZ;
    public ToggleButton IZ;
    Action a;
    public void initialize(){
        GridController gc = new GridController(new World(20), gp, gpmm);
        gc.getTile(0,1);
        //gc.setZoom(5, 1, 0);
    }

    public void changeAction(ActionEvent ae){
        Button b = (Button)(ae.getSource());
        if(b.getText().equals("Zone Area")){
            a = Action.ZONING;
        }
    }

    public int getActionInformation(){
        if(a.equals(Action.ZONING)){
            if(RZ.isPressed()){
                return 0;
            }else if(CZ.isPressed()){
                return 1;
            }else if(IZ.isPressed()){
                return 2;
            }
        }
        return -1;
    }



}
