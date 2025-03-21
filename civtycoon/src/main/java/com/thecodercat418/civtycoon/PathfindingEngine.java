package com.thecodercat418.civtycoon;

import java.util.ArrayList;

public class PathfindingEngine {
    Tile currentTile;
    Position inAnimation;
    Tile initalTile;
    Tile destinationTile;
    ArrayList<Direction> directions = new ArrayList<>();

    public PathfindingEngine(Tile startTile, Tile destinationTile) {
        initalTile = startTile;
        this.destinationTile = destinationTile;
        currentTile = startTile;
        directions = digDeeper(Direction.START, currentTile); //, BuildingType.NONE);
        for (int i = 0; i < directions.size(); i++) {
            if (directions.get(i).equals(Direction.RIGHT) || directions.get(i).equals(Direction.LEFT)
                    || directions.get(i).equals(Direction.DOWN) || directions.get(i).equals(Direction.UP)) {
                int base = directions.get(i).ordinal();
                base += 2;
                if (base > 3) {
                    base -= 4;
                } else if (base < 0) {
                    base += 4;
                }
                directions.set(i, Direction.values()[base]);
            }
        }
        for (Direction d : directions) {
            System.out.println(d.name());
        }
    }

    public PathfindingEngine(Tile startTile, Tile destinationTile, BuildingType lookingFor) {
        initalTile = startTile;
        this.destinationTile = destinationTile;
        currentTile = startTile;
        directions = digDeeper(Direction.START, currentTile );//, lookingFor);
        for (int i = 0; i < directions.size(); i++) {
            if (directions.get(i).equals(Direction.RIGHT) || directions.get(i).equals(Direction.LEFT)
                    || directions.get(i).equals(Direction.DOWN) || directions.get(i).equals(Direction.UP)) {
                int base = directions.get(i).ordinal();
                base += 2;
                if (base > 3) {
                    base -= 4;
                } else if (base < 0) {
                    base += 4;
                }
                directions.set(i, Direction.values()[base]);
            }
        }
        for (Direction d : directions) {
            System.out.println(d.name());
        }
    }

    public ArrayList<Direction> digDeeper(Direction lastDirection, Tile from){  //, BuildingType lookingFor) {
        ArrayList<Direction> tempdirs = new ArrayList<>();
        tempdirs.add(lastDirection);
        Tile up = from.linkedWorld.getTile(from.position.x, from.position.y - 1);
        Tile down = from.linkedWorld.getTile(from.position.x, from.position.y + 1);
        Tile left = from.linkedWorld.getTile(from.position.x - 1, from.position.y);
        Tile right = from.linkedWorld.getTile(from.position.x + 1, from.position.y);

        ArrayList<Boolean> directionCanMove = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            directionCanMove.add(false);
        }
        if (up != null) {
            if (up.type.equals(BuildingType.ROAD) && !lastDirection.equals(Direction.UP)) {
                directionCanMove.set(0, true);
            }
            if (up.equals(destinationTile) ){//|| up.type.equals(lookingFor) ) {
                tempdirs.add(Direction.DOWN);
                tempdirs.add(Direction.END);
                return tempdirs;
            }
        }
        if (down != null) {
            if (down.type.equals(BuildingType.ROAD) && !lastDirection.equals(Direction.DOWN)) {
                directionCanMove.set(1, true);
            }
            if (down.equals(destinationTile) ){//|| down.type.equals(lookingFor)) {
                tempdirs.add(Direction.UP);
                tempdirs.add(Direction.END);
                return tempdirs;
            }
        }
        if (left != null) {
            if (left.type.equals(BuildingType.ROAD) && !lastDirection.equals(Direction.LEFT)) {
                directionCanMove.set(2, true);
            }
            if (left.equals(destinationTile) ){//|| left.type.equals(lookingFor)) {
                tempdirs.add(Direction.RIGHT);
                tempdirs.add(Direction.END);
                return tempdirs;
            }
        }
        if (right != null) {
            if (right.type.equals(BuildingType.ROAD) && !lastDirection.equals(Direction.RIGHT)) {
                directionCanMove.set(3, true);
            }
            if (right.equals(destinationTile)){// || right.type.equals(lookingFor)) {
                tempdirs.add(Direction.LEFT);
                tempdirs.add(Direction.END);
                return tempdirs;
            }
        }

        if (directionCanMove.contains(true)) {
            for (int i = 0; i < directionCanMove.size(); i++) {
                if (directionCanMove.get(i)) {
                    Tile t = null;
                    Direction d = Direction.NONE;
                    switch (i) {
                        case 0:
                            t = up;
                            d = Direction.DOWN;
                            break;
                        case 1:
                            t = down;
                            d = Direction.UP;
                            break;
                        case 2:
                            t = left;
                            d = Direction.RIGHT;
                            break;
                        case 3:
                            t = right;
                            d = Direction.LEFT;
                            break;
                    }
                    ArrayList<Direction> dir = digDeeper(d, t);//, lookingFor);
                    if (dir.contains(Direction.END)) {
                        tempdirs.addAll(dir);
                    }
                }
            }

        }
        return tempdirs;
    }

    public void moveObject() {

    }
}
