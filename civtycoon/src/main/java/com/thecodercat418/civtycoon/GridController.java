package com.thecodercat418.civtycoon;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class GridController {
    // This class will manage request for all of the tiles on the map
    // NOT STATIC
    // Init with grid size
    // Register all grid objects
    // finilize
    // done
    GridPane gp;
    GridPane gpmm;
    World loadedWorld;
    World miniWorld;

    Controller c;

    public static String minigridnum = "";
    public static Tile lastclicked = null;

    public GridController(World world, GridPane gp, GridPane gpmm, Controller c) {
        this.c = c;
        if (world.dimx != world.dimy) {
            throw new RuntimeException("MAIN GRID WORLD MUST BE A SQUARE.");
        }
        this.gp = gp;
        loadedWorld = world;
        for (int i = gp.getChildren().size() - 1; i > 0; i--) {
            if (gp.getChildren().get(i) instanceof Group) {
                continue;
            }
            gp.getChildren().remove(i);
        }
        while (!gp.getColumnConstraints().isEmpty()) {
            gp.getColumnConstraints().removeFirst();
        }
        System.out.println(gp.getChildren().size());
        while (!gp.getRowConstraints().isEmpty()) {
            gp.getRowConstraints().removeFirst();
        }
        for (int i = 0; i < world.dimx; i++) {
            gp.getRowConstraints().add(new RowConstraints(gp.getPrefHeight() / world.dimx));
            gp.getColumnConstraints().add(new ColumnConstraints(gp.getPrefWidth() / world.dimx));
        }

        for (int i = 0; i < world.map.size(); i++) {
            for (int j = 0; j < world.map.size(); j++) {
                Tile currentTile = world.map.get(i).get(j);
                Pane p = new Pane();
                p.setOnMouseClicked((me) -> {
                    
                    // Find action infomation
                    switch (c.a) {
                        case ZONING:
                            p.setStyle("-fx-background-color: grey;");
                            if (lastclicked != null) {
                                if (lastclicked.equals(currentTile)) {
                                    p.setStyle("");
                                    lastclicked = null;
                                } else {
                                    new ZonedArea(c.getZoningActionInformation(),
                                            createTerritory(lastclicked, currentTile, -1, -1));
                                    // p.setStyle("");
                                    lastclicked = null;
                                }

                            } else {
                                lastclicked = currentTile;
                                System.out.println(lastclicked);
                            }
                            break;
                        case BUILDING:
                            p.setStyle("-fx-background-color: gray;");
                            switch (c.getBuildingActionInformation()) {
                                case ROAD:
                                    p.setStyle("-fx-background-color: black;");
                                    currentTile.type = BuildingType.ROAD;
                                    currentTile.a.updateType();
                                    InfomationController.money-=200;
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                        p.setStyle("-fx-background-color: grey;");
                            if (lastclicked != null) {
                                if (lastclicked.equals(currentTile)) {
                                    p.setStyle("");
                                    lastclicked = null;
                                } else {
                                    new PathfindingEngine(lastclicked, currentTile);
                                    lastclicked = null;
                                }

                            } else {
                                lastclicked = currentTile;
                                System.out.println(lastclicked);
                            }
                            break;
                    }
                    currentTile.a.updateType();
                    currentTile.a.start();
                });
                p.setOnMouseEntered((me) -> {
                    switch (c.a) {
                        case ZONING:

                        case BUILDING:
                            switch (c.getBuildingActionInformation()) {
                                case ROAD:
                                    // currentTile.hoverAnimation = new Animation(currentTile);
                                    // currentTile.hoverAnimation.overrideType(BuildingType.ROAD);
                                    // currentTile.hovering(true);
                                    // currentTile.linkedChildPane.setStyle("-fx-background-color: black;");
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case Pathfinding:
                        break;
                    }
                });
                p.setOnMouseExited((me) -> {
                    //currentTile.hovering(false);
                    //currentTile.linkedChildPane.setStyle("");

                });
                gp.add(p, i, j);
                Animation a = new Animation(loadedWorld.map.get(i).get(j));
                loadedWorld.map.get(i).get(j).setAnimation(a);
                AnimationManager.register(a);
                currentTile.linkedChildPane = p;
            }
        }

        // minimap
        miniWorld = new World(world.dimx / 8);

        for (int i = gpmm.getChildren().size() - 1; i > 0; i--) {
            if (gpmm.getChildren().get(i) instanceof Group) {
                continue;
            }
            gpmm.getChildren().remove(i);
        }

        while (!gpmm.getColumnConstraints().isEmpty()) {
            gpmm.getColumnConstraints().removeFirst();
        }
        while (!gpmm.getRowConstraints().isEmpty()) {
            gpmm.getRowConstraints().removeFirst();
        }
        for (int i = 0; i < miniWorld.dimx; i++) {
            gpmm.getRowConstraints().add(new RowConstraints(gpmm.getPrefHeight() / miniWorld.dimx));
            gpmm.getColumnConstraints().add(new ColumnConstraints(gpmm.getPrefWidth() / miniWorld.dimx));
        }

        for (int i = 0; i < miniWorld.map.size(); i++) {
            for (int j = 0; j < miniWorld.map.size(); j++) {
                int f = i;
                int t = j;
                Pane p = new Pane();
                p.setOnMouseClicked((me) -> {
                    for (Node child : gpmm.getChildren()) {
                        if (child instanceof Pane) {
                            child.setStyle("");
                        }
                    }
                    System.out.println(p.getStyle());
                    if (minigridnum.equals(Integer.toString(f) + Integer.toString(t))) {
                        minigridnum = "";
                        setZoom(loadedWorld.dimx, 0, 0);
                        return;
                    }
                    p.setStyle("-fx-background-color: grey;");
                    System.out.println(p.getStyle());
                    minigridnum = Integer.toString(f) + Integer.toString(t);
                    setZoom(8, f, t);
                });

                gpmm.add(p, i, j);

                miniWorld.map.get(i).get(j).linkedChildPane = p;
            }
        }

        // gp.setGridLinesVisible(true);
    }

    public void setZoom(int dimOfZoom, int minimapx, int minimapy) {
        for (ArrayList<Tile> art : loadedWorld.map) {
            for (Tile t : art) {
                for (int i = t.linkedChildPane.getChildren().size() - 1; i > -1; i--) {
                    t.linkedChildPane.getChildren().remove(i);
                }
            }
        }
        for (int i = gp.getChildren().size() - 1; i > 0; i--) {
            if (gp.getChildren().get(i) instanceof Group) {
                continue;
            }
            gp.getChildren().remove(i);
        }
        while (!gp.getColumnConstraints().isEmpty()) {
            gp.getColumnConstraints().removeFirst();
        }
        while (!gp.getRowConstraints().isEmpty()) {
            gp.getRowConstraints().removeFirst();
        }
        for (int i = 0; i < dimOfZoom; i++) {// Diffrent fractonal values??
            gp.getRowConstraints().add(new RowConstraints(gp.getPrefHeight() / dimOfZoom));
            gp.getColumnConstraints().add(new ColumnConstraints(gp.getPrefWidth() / dimOfZoom));
        }
        // TODO: GridPain cannot extend past num of row/comlom*10
        for (int i = dimOfZoom * minimapx; i < dimOfZoom + dimOfZoom * minimapx; i++) {
            for (int j = dimOfZoom * minimapy; j < dimOfZoom + dimOfZoom * minimapy; j++) {

                if (dimOfZoom != loadedWorld.dimx) {

                    GridPane animationGrid = new GridPane();

                    while (!animationGrid.getColumnConstraints().isEmpty()) {
                        animationGrid.getColumnConstraints().removeFirst();
                    }
                    while (!animationGrid.getRowConstraints().isEmpty()) {
                        animationGrid.getRowConstraints().removeFirst();
                    }
                    for (int z = animationGrid.getChildren().size() - 1; z > 0; z--) {
                        if (animationGrid.getChildren().get(i) instanceof Group) {
                            continue;
                        }
                        animationGrid.getChildren().remove(i);
                    }

                    for (int z = 0; z < 16; z++) {
                        animationGrid.getRowConstraints()
                                .add(new RowConstraints((gp.getPrefHeight() / dimOfZoom) / 16));
                        animationGrid.getColumnConstraints()
                                .add(new ColumnConstraints((gp.getPrefWidth() / dimOfZoom) / 16));
                    }
                    for (int z = 0; z < 16; z++) {
                        for (int f = 0; f < 16; f++) {
                            animationGrid.add(new Pane(), z, f);
                        }
                    }
                    animationGrid.setGridLinesVisible(true);
                    animationGrid.setVisible(true);
                    loadedWorld.map.get(i).get(j).linkedChildPane.getChildren().add(animationGrid);
                    loadedWorld.map.get(i).get(j).type = BuildingType.NONE; //
                    loadedWorld.map.get(i).get(j).animationGrid = animationGrid;
                    loadedWorld.map.get(i).get(j).a.start();

                    // TODO: REMOVE FORCE AUTO LOCK. Find a way to not keep requesting resources

                    // Fill with panes
                    // Tell controller to start only specified Tiles gathered here.

                    // When zooming out, need to resize grid again. Think about removing it
                    // completly when not needed. Will have to reregister with the Animation
                    // conttroler tho

                }

                gp.add(loadedWorld.map.get(i).get(j).linkedChildPane, i - dimOfZoom * minimapx,
                        j - dimOfZoom * minimapy);
            }
        }
        System.out.println(gp.gridLinesVisibleProperty().get());
    }

    public Tile getTile(int x, int y) {
        //t.linkedChildPane.setStyle("-fx-background-color: green;");
        return loadedWorld.getTile(x, y);
    }

    public Territory createTerritory(Tile startTile, Tile endTile, int maxSizeX, int maxSizeY) {
        // Allow overlapping?? Overwrites? Merging? Filling the gaps?
        // String color = "";
        // switch (c.getZoningActionInformation()) {
        // case RESIDENTIAL:
        // color = "green";
        // break;
        // case COMMERCIAL:
        // color = "blue";
        // break;
        // case INDUSTRIAL:
        // color = "orange";
        // break;
        // default:
        // color = "black";
        // break;
        // }
        Territory t = new Territory();
        if (endTile.position.x <= startTile.position.x && endTile.position.y <= startTile.position.y) {
            for (int i = endTile.position.x; i < startTile.position.x + 1; i++) {
                for (int j = endTile.position.y; j < startTile.position.y + 1; j++) {
                    t.t.add(loadedWorld.map.get(i).get(j));
                    loadedWorld.map.get(i).get(j).linkedTerritory = t;
                    // loadedWorld.map.get(i).get(j).linkedChildPane.setStyle("-fx-background-color:
                    // " + color + ";");
                }
            }
        } else if (endTile.position.x >= startTile.position.x && endTile.position.y >= startTile.position.y) {
            for (int i = startTile.position.x; i < endTile.position.x + 1; i++) {
                for (int j = startTile.position.y; j < endTile.position.y + 1; j++) {
                    t.t.add(loadedWorld.map.get(i).get(j));
                    loadedWorld.map.get(i).get(j).linkedTerritory = t;
                    // loadedWorld.map.get(i).get(j).linkedChildPane.setStyle("-fx-background-color:
                    // " + color + ";");
                }
            }
        } else if (endTile.position.x > startTile.position.x && endTile.position.y < startTile.position.y) {
            for (int i = startTile.position.x; i < endTile.position.x + 1; i++) {
                for (int j = endTile.position.y; j < startTile.position.y + 1; j++) {
                    t.t.add(loadedWorld.map.get(i).get(j));
                    loadedWorld.map.get(i).get(j).linkedTerritory = t;
                    // loadedWorld.map.get(i).get(j).linkedChildPane.setStyle("-fx-background-color:
                    // " + color + ";");
                }
            }
        } else if (endTile.position.x < startTile.position.x && endTile.position.y > startTile.position.y) {
            for (int i = endTile.position.x; i < startTile.position.x + 1; i++) {
                for (int j = startTile.position.y; j < endTile.position.y + 1; j++) {
                    t.t.add(loadedWorld.map.get(i).get(j));
                    loadedWorld.map.get(i).get(j).linkedTerritory = t;
                    // loadedWorld.map.get(i).get(j).linkedChildPane.setStyle("-fx-background-color:
                    // " + color + ";");
                }
            }
        }
        return t;
    }
}
