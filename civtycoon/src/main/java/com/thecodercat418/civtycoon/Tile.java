package com.thecodercat418.civtycoon;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Tile {
    World linkedWorld;
    Territory linkedTerritory;

    Pane linkedChildPane;
    ArrayList<AnimationObject> aos = new ArrayList<>();
    GridPane animationGrid = null;
    BuildingType type;


    Position position;

    public Tile(World linkedWorld, Territory linkedTerritory, Pane linkedChildPane, Position position, BuildingType type){
        this.linkedChildPane = linkedChildPane;
        this.linkedTerritory = linkedTerritory;
        this.linkedWorld = linkedWorld;
        this.position = position;
        this.type = type;
    }

    public Position getPosition(){
        return position;
    }
}
