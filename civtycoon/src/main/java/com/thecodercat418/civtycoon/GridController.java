package com.thecodercat418.civtycoon;

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

    public static String minigridnum = "";
    public static Tile lastclicked = null;

    public GridController(World world, GridPane gp, GridPane gpmm) {
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
                    p.setStyle("-fx-background-color: grey;");
                    if (lastclicked != null) {
                        if (lastclicked.equals(currentTile)) {
                            p.setStyle("");
                            lastclicked = null;
                        } else {
                            createTerritory(lastclicked, currentTile);
                            //p.setStyle("");
                            lastclicked = null;
                        }
                        
                    } else {
                        lastclicked = currentTile;
                        System.out.println(lastclicked);
                    }
                });
                gp.add(p, i, j);
                currentTile.linkedChildPane = p;
            }
        }

        // minimap
        miniWorld = new World(world.dimx / 10);

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
                    setZoom(10, f, t);
                });
                gpmm.add(p, i, j);

                miniWorld.map.get(i).get(j).linkedChildPane = p;
            }
        }

        // gp.setGridLinesVisible(true);
    }

    public void setZoom(int dimOfZoom, int minimapx, int minimapy) {
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

        for (int i = dimOfZoom * minimapx; i < dimOfZoom + dimOfZoom * minimapx; i++) {
            for (int j = dimOfZoom * minimapy; j < dimOfZoom + dimOfZoom * minimapy; j++) {
                gp.add(loadedWorld.map.get(i).get(j).linkedChildPane, i - dimOfZoom * minimapx,
                        j - dimOfZoom * minimapy);
            }
        }
        System.out.println(gp.gridLinesVisibleProperty().get());
    }

    public Tile getTile(int x, int y) {
        Tile t = loadedWorld.map.get(x).get(y);
        t.linkedChildPane.setStyle("-fx-background-color: green;");
        return loadedWorld.map.get(x).get(y);
    }

    public Territory createTerritory(Tile startTile, Tile endTile) {
        Territory t = new Territory();
        for (int i = startTile.position.x; i < endTile.position.x - startTile.position.x; i++) {
            for (int j = startTile.position.y; j < endTile.position.y - startTile.position.y; j++) {
                loadedWorld.map.get(i).get(j).linkedTerritory = t;
                loadedWorld.map.get(i).get(j).linkedChildPane.setStyle("-fx-background-color: orange;");
            }
        }
        return t;
    }
}
