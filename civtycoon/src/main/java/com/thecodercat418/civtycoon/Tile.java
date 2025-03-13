package com.thecodercat418.civtycoon;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Tile {
    World linkedWorld;
    Territory linkedTerritory;

    Pane linkedChildPane;
    GridPane linkedAnimationGrid; //Animation Controller??

    Position position;

    public Tile(World linkedWorld, Territory linkedTerritory, Pane linkedChildPane, Position position){
        this.linkedChildPane = linkedChildPane;
        this.linkedTerritory = linkedTerritory;
        this.linkedWorld = linkedWorld;
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }
}
