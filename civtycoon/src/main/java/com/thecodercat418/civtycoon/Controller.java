package com.thecodercat418.civtycoon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    public GridPane gp;
    public GridPane gpmm;
    public ToggleButton RZ;
    public ToggleButton CZ;
    public ToggleButton IZ;
    public ToggleButton road;
    public BarChart<String, Number> landNeeds;
    public Label resi;
    public Action a = Action.ZONING;

    public void initialize() {
        TickManager.setup();
        AnimationManager.setup();
        InfomationController.setup();
        TickManager.register(() -> {
            update();
        });
        GridController gc = new GridController(new World(20), gp, gpmm, this);
        // gc.setZoom(5, 0, 0);
        // gc.getTile(3, 5).type = BuildingType.HOUSE;
        // gc.getTile(2, 5).type = BuildingType.ROAD;
        // gc.getTile(2, 6).type = BuildingType.ROAD;
        // gc.getTile(2, 7).type = BuildingType.ROAD;
        // gc.getTile(2, 8).type = BuildingType.ROAD;
        // gc.getTile(2, 9).type = BuildingType.ROAD;
        // gc.getTile(3, 9).type = BuildingType.HOUSE;
        // gc.getTile(3, 5).a.updateType().start();
        // gc.getTile(2, 5).a.updateType().start();
        // gc.getTile(2, 6).a.updateType().start();
        // gc.getTile(2, 7).a.updateType().start();
        // gc.getTile(2, 8).a.updateType().start();
        // gc.getTile(2, 9).a.updateType().start();
        // gc.getTile(3, 9).a.updateType().start();
        new PathfindingEngine(gc.getTile(3, 5), gc.getTile(3, 9), null);

    }

    public void ph(){

    }

    public void changeAction(ActionEvent ae) {
        ToggleButton b = (ToggleButton) (ae.getSource());
        switch (b.getText()) {
            case "Zone Area":
                a = Action.ZONING;
                break;
            case "Build":
                a = Action.BUILDING;
                break;
            case "Pathfinding":
                a = Action.Pathfinding;
                break;
        }
        
    }

    public ZoningAction getZoningActionInformation() {
        if (Action.ZONING.equals(a)) {
            if (RZ.isSelected()) {
                return ZoningAction.RESIDENTIAL;
            } else if (CZ.isSelected()) {
                return ZoningAction.COMMERCIAL;
            } else if (IZ.isSelected()) {
                return ZoningAction.INDUSTRIAL;
            }
        }
        return ZoningAction.NONE;
    }

    public BuildingType getBuildingActionInformation() {
        if (Action.BUILDING.equals(a)) {
            if (road.isSelected()) {
                return BuildingType.ROAD;
            }
        }
        return BuildingType.NONE;
    }

    public void update() {
        resi.setText(InfomationController.money + "");
    }

}
