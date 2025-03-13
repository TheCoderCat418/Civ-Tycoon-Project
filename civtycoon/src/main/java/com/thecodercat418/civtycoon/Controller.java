package com.thecodercat418.civtycoon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    public GridPane gp;
    public GridPane gpmm;
    public ToggleButton RZ;
    public ToggleButton CZ;
    public ToggleButton IZ;
    Action a = null;
    public void initialize(){
        GridController gc = new GridController(new World(20), gp, gpmm, this);
        gc.getTile(0,1);
        //gc.setZoom(5, 1, 0);
    }

    public void changeAction(ActionEvent ae){
        ToggleButton b = (ToggleButton)(ae.getSource());
        if(b.getText().equals("Zone Area")){
            a = Action.ZONING;
        }
    }

    public ZoningAction getZoningActionInformation(){
        if(Action.ZONING.equals(a)){
            if(RZ.isSelected()){
                return ZoningAction.RESIDENTIAL;
            }else if(CZ.isSelected()){
                return ZoningAction.COMMERCIAL;
            }else if(IZ.isSelected()){
                return ZoningAction.INDUSTRIAL;
            }
        }
        return ZoningAction.NONE;
    }



}
