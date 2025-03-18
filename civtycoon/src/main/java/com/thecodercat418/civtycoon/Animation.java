package com.thecodercat418.civtycoon;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Animation {
    AnimationSequence asToPlay;
    boolean running = false;
    int frameIndex = 0;
    Tile t;
    GridPane animationGrid;

    public Animation(Tile t) {
        this.t = t;
        this.animationGrid = t.animationGrid;
        updateType();
    }

    public void updateType() {
        stop();
        asToPlay = AnimationManager.getAnimationSequenceFromBuildingType(t.type, true);
        start();
    }

    public void start() {
        running = true;
        frameIndex = 0;
    }

    public void stop() {
        running = false;
    }

    public void cycleToNext() {
        if (!running) {
            return;
        }
        if (frameIndex >= asToPlay.frames.length) {
            frameIndex = 0;
        }
        displayFrame(animationGrid, frameIndex);
        frameIndex++;
    }

    private void displayFrame(GridPane animationGrid, int frame) {
        for (int i = 0; i < animationGrid.getChildren().size(); i++) {
            if (animationGrid.getChildren().get(i) instanceof Pane) {
                animationGrid.getChildren().get(i).setStyle("-fx-background-color: " + asToPlay.frames[frame].pixelGrid
                        .get(i / asToPlay.frameSize).get(i % asToPlay.frameSize).toHexCode());
            }
        }
    }

}
