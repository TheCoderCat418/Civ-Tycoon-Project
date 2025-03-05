package com.thecodercat418.civtycoon;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;

public class World {
    int dim;

    ArrayList<ArrayList<Tile>> map;
    public World(int dim){
        this.dim = dim;
        map = new ArrayList<>(dim);
        for(int i = 0; i < dim; i++){
            map.add(new ArrayList<>(dim));
            for(int j = 0; j < dim; j++){
                map.get(i).add(new Tile());
            }

        }
    }
}
