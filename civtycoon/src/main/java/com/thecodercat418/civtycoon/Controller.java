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
        // CategoryAxis x = new
        // CategoryAxis(FXCollections.<String>observableArrayList(Arrays.asList(
        // "Residental", "Commerial", "Industrial")));
        // NumberAxis y = new NumberAxis();
        // landNeeds = new BarChart<>(x, y);
        TickManager.setup();
        AnimationManager.setup();
        InfomationController.setup();
        TickManager.register(() -> {
            update();
        });
        new GridController(new World(20), gp, gpmm, this);
        // gc.setZoom(5, 0, 0);

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
        resi.setText(InfomationController.resWant + "");
        // landNeeds.getData().clear();
        // System.out.println(InfomationController.resWant*100);
        // XYChart.Series<String, Number> s = new XYChart.Series<>();
        // s.getData().add(new XYChart.Data<String,Number>("Residental",
        // InfomationController.resWant*100));
        // landNeeds.getData().add(s);
    }

}
