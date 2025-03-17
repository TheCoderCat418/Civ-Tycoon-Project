package com.thecodercat418.civtycoon;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    public GridPane gp;
    public GridPane gpmm;
    public ToggleButton RZ;
    public ToggleButton CZ;
    public ToggleButton IZ;
    public BarChart<String, Number> landNeeds;
    Action a = null;

    public void initialize() {
        CategoryAxis x = new CategoryAxis(FXCollections.<String>observableArrayList(Arrays.asList(
                "Residental", "Commerial", "Industrial")));
        NumberAxis y = new NumberAxis();
        landNeeds = new BarChart<>(x, y);
        TickManager.setup();
        AnimationManager.setup();
        TickManager.register(() -> {
            update();
        });
        GridController gc = new GridController(new World(20), gp, gpmm, this);
        // gc.setZoom(5, 0, 0);

    }

    public void changeAction(ActionEvent ae) {
        ToggleButton b = (ToggleButton) (ae.getSource());
        if (b.getText().equals("Zone Area")) {
            a = Action.ZONING;
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

    public void update() {
        //landNeeds.getData().clear();
        XYChart.Series<String, Number> s = new XYChart.Series<>();
        s.getData().add(new XYChart.Data<String,Number>("Residental", InfomationController.resWant*100));
        landNeeds.getData().add(s);
    }

}
