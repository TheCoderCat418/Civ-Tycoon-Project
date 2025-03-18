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
    Animation a = null;
    Animation hoverAnimation = null;
    BuildingType type = BuildingType.NONE;

    Position position;

    public Tile(World linkedWorld, Territory linkedTerritory, Pane linkedChildPane, Position position,
            BuildingType type) {
        this.linkedChildPane = linkedChildPane;
        this.linkedTerritory = linkedTerritory;
        this.linkedWorld = linkedWorld;
        this.position = position;
        this.type = type;
    }

    public void hovering(boolean entering) {
        if (hoverAnimation == null) {
            return;
        }
        if (entering) {
            a.stop();
            hoverAnimation.start();
        } else {
            hoverAnimation.stop();
            hoverAnimation = null;
            a.start();

        }
    }

    public void setAnimation(Animation a) {
        this.a = a;
    }

    public Position getPosition() {
        return position;
    }
}
