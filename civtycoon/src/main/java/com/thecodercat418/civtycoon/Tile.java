package com.thecodercat418.civtycoon;

import javafx.scene.Node;

public class Tile {
    World linkedWorld;
    Territory linkedTerritory;

    Node linkedChildPane;

    Position position;

    public Tile(World linkedWorld, Territory linkedTerritory, Node linkedChildNode, Position position){
        this.linkedChildPane = linkedChildNode;
        this.linkedTerritory = linkedTerritory;
        this.linkedWorld = linkedWorld;
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }
}
