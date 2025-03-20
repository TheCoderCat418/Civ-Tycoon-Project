package com.thecodercat418.civtycoon;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Animation {
    AnimationSequence asToPlay;
    boolean running = false;
    int frameIndex = 0;
    Tile t;
    AnimationFilters af = AnimationFilters.NONE;

    public Animation(Tile t) {
        this.t = t;
        updateType();
    }

    public Animation updateType() {
        stop();
        asToPlay = AnimationManager.getAnimationSequenceFromBuildingType(t.type, true);
        return this;
    }

    public Animation overrideType(BuildingType bt){
        stop();
        asToPlay = AnimationManager.getAnimationSequenceFromBuildingType(bt, true);
        return this;
    }

    public void setFilter(AnimationFilters af){
        this.af = af;
    }

    public void start() {
        running = true;
        frameIndex = 0;
    }

    public void stop() {
        running = false;
        clear();
    }

    public void clear() {
        if(t.animationGrid==null){
            return;
        }
        for (int i = 0; i < t.animationGrid.getChildren().size(); i++) {
            if (t.animationGrid.getChildren().get(i) instanceof Pane) {
                t.animationGrid.getChildren().get(i).setStyle("");
            }
        }
    }

    public void cycleToNext() {
        if (!running) {
            return;
        }
        if (frameIndex >= asToPlay.frames.length) {
            frameIndex = 0;
        }
        displayFrame(t.animationGrid, frameIndex);
        frameIndex++;
    }

    private void displayFrame(GridPane animationGrid, int frame) {
        if(animationGrid==null){
            return;
        }
        for (int i = 0; i < animationGrid.getChildren().size(); i++) {
            if (animationGrid.getChildren().get(i) instanceof Pane) {
                animationGrid.getChildren().get(i).setStyle("-fx-background-color: " + asToPlay.frames[frame].pixelGrid
                        .get(i / asToPlay.frameSize).get(i % asToPlay.frameSize).toHexCode(af));
            }
        }
    }

}
