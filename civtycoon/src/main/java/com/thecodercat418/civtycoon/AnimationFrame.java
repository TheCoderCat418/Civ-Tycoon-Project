package com.thecodercat418.civtycoon;

import java.util.ArrayList;

public class AnimationFrame {
    int index = 0;
    ArrayList<ArrayList<Color>> pixelGrid = new ArrayList<>();

    public AnimationFrame(int index) {
        this.index = index;
    }

    public AnimationFrame() {

    }

    public void setPixel(int x, int y, Color c) {
        while (pixelGrid.size() <= x) {
            pixelGrid.add(new ArrayList<Color>());
        }
        while (pixelGrid.get(x).size() <= y) {
            pixelGrid.get(x).add(new Color(0, 0, 0, false));
        }
        pixelGrid.get(x).set(y, c);
    }
}
