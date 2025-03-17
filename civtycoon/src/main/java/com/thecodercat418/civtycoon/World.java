package com.thecodercat418.civtycoon;

import java.util.ArrayList;


public class World {
    int dimx;
    int dimy;

    ArrayList<ArrayList<Tile>> map;
    public World(int dim){
        this.dimx = dim;
        this.dimy = dim;
        map = new ArrayList<>(dim);
        for(int i = 0; i < dim; i++){
            map.add(new ArrayList<>(dim));
            for(int j = 0; j < dim; j++){
                map.get(i).add(new Tile(this, null,null, new Position(i, j), BuildingType.NONE));
            }

        }
    }

    public World(int dimx, int dimy){
        this.dimx = dimx;
        this.dimy = dimy;
        map = new ArrayList<>(dimx);
        for(int i = 0; i < dimx; i++){
            map.add(new ArrayList<>(dimy));
            for(int j = 0; j < dimy; j++){
                map.get(i).add(new Tile(this, null, null, new Position(i, j), BuildingType.NONE));
            }

        }
    }
}
