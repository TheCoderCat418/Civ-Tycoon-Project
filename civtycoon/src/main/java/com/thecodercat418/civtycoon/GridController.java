package com.thecodercat418.civtycoon;

import java.util.ArrayList;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class GridController {
    //This class will manage request for all of the tiles on the map
    //NOT STATIC
    //Init with grid size
    //Register all grid objects
    //finilize
    //done
    GridPane gp;
    World loadedWorld;


    
    public GridController(World world, GridPane gp){
        this.gp = gp;
        loadedWorld = world;
        while (gp.getColumnConstraints().size() > 0) {
            gp.getColumnConstraints().remove(0);
        }
        while (gp.getRowConstraints().size() > 0) {
            gp.getRowConstraints().remove(0);
        }
        for(int i = 0; i < world.dim; i++){
            gp.getRowConstraints().add(new RowConstraints(gp.getPrefHeight()/world.dim));
            gp.getColumnConstraints().add(new ColumnConstraints(gp.getPrefWidth()/world.dim));
        }

        for(int i = 0; i < world.map.size(); i++){
            for(int j = 0; j < world.map.size(); j++){
                int f = i;
                int t = j;
                Pane p = new Pane();
            p.setOnMouseClicked((me) -> {
                p.setStyle("-fx-background-color: grey;");
                System.out.println(Integer.toString(f) + Integer.toString(t));
            });
            gp.add(p, i, j);

                world.map.get(i).get(j).linkedPane = gp.getChildren().get(i*world.map.size()+j);
            }
        }
    }

    public Tile getTile(int x, int y){
        Tile t = loadedWorld.map.get(x).get(y);
        t.linkedPane.setStyle("-fx-background-color: green;");
        return loadedWorld.map.get(x).get(y);
    }
}
