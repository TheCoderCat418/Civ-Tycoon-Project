package com.thecodercat418.civtycoon;

import javafx.scene.layout.Pane;

public class ResidentalHome extends Tile {
    int people = 0;
    int level = 1;

    public ResidentalHome(World linkedWorld, Territory linkedTerritory, Pane linkedChildPane, Position position,
            BuildingType type) {
        super(linkedWorld, linkedTerritory, linkedChildPane, position, type);
    }

    

}
