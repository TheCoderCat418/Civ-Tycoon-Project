package com.thecodercat418.civtycoon;

import java.util.ArrayList;

public class ZonedArea {
    // Get zoning needs
    // Run random numbers to see if it will be build

    public ZoningAction zone = ZoningAction.NONE;
    Territory occupiedArea;

    public ZonedArea(ZoningAction za, Territory occupiedArea) {
        zone = za;
        this.occupiedArea = occupiedArea;
        String color = "";
        switch (za) {
            case RESIDENTIAL:
                color = "green";
                break;
            case COMMERCIAL:
                color = "blue";
                break;
            case INDUSTRIAL:
                color = "orange";
                break;
            default:
                color = "black";
                break;
        }
        for (Tile t : occupiedArea.t) {
            t.linkedChildPane.setStyle("-fx-background-color: " + color + ";");
        }
        TickManager.register(() -> {
            runTick();
        });
    }

    public void runTick() {
        double want = 0;
        switch (zone) {
            case RESIDENTIAL:
                want = InfomationController.resWant;
                break;
            case COMMERCIAL:
                want = InfomationController.comWant;
                break;
            case INDUSTRIAL:
                want = InfomationController.indWant;
                break;

            default:
                break;
        }
        if ((int)(Math.random() * 10) == 0) {
            System.out.println("MADE");
            createBuilding();
        }
    }

    public void createBuilding() {
        ArrayList<Tile> wRoad = new ArrayList<>();
        ArrayList<Boolean> directionCanMove = new ArrayList<>(4);
        for (Tile t : occupiedArea.t) {
            if(!t.type.equals(BuildingType.NONE)){
                continue;
            }
            Tile up = t.linkedWorld.getTile(t.position.x, t.position.y + 1);
            Tile down = t.linkedWorld.getTile(t.position.x, t.position.y - 1);
            Tile left = t.linkedWorld.getTile(t.position.x + 1, t.position.y);
            Tile right = t.linkedWorld.getTile(t.position.x - 1, t.position.y);
            directionCanMove.clear();
            for (int i = 0; i < 4; i++) {
                directionCanMove.add(true);
            }

            boolean roadAttached = false;

            if (up != null) {
                if (up.type.equals(BuildingType.ROAD)) {
                    roadAttached = true;
                    directionCanMove.set(0, false);
                }
            }
            if (down != null) {
                if (down.type.equals(BuildingType.ROAD)) {
                    roadAttached = true;
                    directionCanMove.set(1, false);
                }
            }
            if (left != null) {
                if (left.type.equals(BuildingType.ROAD)) {
                    roadAttached = true;
                    directionCanMove.set(2, false);
                }
            }
            if (right != null) {
                if (right.type.equals(BuildingType.ROAD)) {
                    roadAttached = true;
                    directionCanMove.set(3, false);
                }
            }
            if (roadAttached) {
                wRoad.add(t);
            }
        }
        if(wRoad.isEmpty()){
            return;
        }
        //boolean done = false;
        Tile selectedTile = wRoad.get((int) Math.random() * wRoad.size());
        selectedTile.type = BuildingType.HOUSE;
        selectedTile.a.updateType();
        System.out.println(selectedTile);
    }
}

// ### VVV LOST TO TIME CRUNCH VVV ###
// int radius = 0;
// while (!done) {

// boolean radiusClear = true;
// for (int x = -1 - radius; x < 1 + radius; x++) {
// for (int y = -1 - radius; y < 1 + radius; y++) {
// if (!selectedTile.linkedWorld.getTile(selectedTile.position.x + x,
// selectedTile.position.y + y).type
// .equals(BuildingType.NONE)) {
// radiusClear = false;
// }
// }
// }
// if (radiusClear && radius <= 3) {
// radius++;
// } else {
// done = true;
// }
// }
// Create house by seeing how far you can extend. (directly relates to the
// number of people, max size 3x3)
// Has the house class and is isshued a tick entry. holds people(person[]) and
// monitors them to upgrade the house. should also have some random needs
// House should be knolageable of the stats around it (e.g distance away from
// fire station etc.)

// find building placement
// Create building
// switch (zone) {
// case RESIDENTIAL:
// InfomationController.resWant;
// break;
// case COMMERCIAL:
// InfomationController.comWant;
// break;
// case INDUSTRIAL:
// InfomationController.indWant;
// break;

// default:
// break;
// }
// }
