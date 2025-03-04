package com.thecodercat418.civtycoon;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class GridController {
    //This class will manage request for all of the tiles on the map
    //NOT STATIC
    //Init with grid size
    //Register all grid objects
    //finilize
    //done
    ArrayList<Tile> map;
    public GridController(int dim){
        map = new ArrayList<>(dim);
        for(int i = 0; i < dim)
    }

    public Tile register(Pane p){
        return new Tile();
    }
}
