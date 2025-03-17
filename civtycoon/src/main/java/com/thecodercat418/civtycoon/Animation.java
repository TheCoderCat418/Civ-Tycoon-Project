package com.thecodercat418.civtycoon;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Animation {
    AnimationSequence asToPlay;
    boolean running = false;
    int frameIndex = 0;
    Tile t;
    GridPane animationGrid;
    

    public Animation(Tile t){
        this.t=t;
        this.animationGrid = t.animationGrid;
        if(t.type.equals(BuildingType.HOUSE)){
            asToPlay = AnimationManager.loadedAnimations.get(Animations.HOUSE1);
        }
    }
    public void play(){
        running = true;
        frameIndex = 0;
    }
    public void stop(){
        running = false;
    }

    public void cycleToNext(){
        if(!running){
            return;
        }
        if(frameIndex>=asToPlay.frames.size()){
            frameIndex = 0;
        }
        displayFrame(animationGrid, frameIndex);
        frameIndex++;
    }


    private void displayFrame(GridPane animationGrid, int frame){
        int notPaneCounter = 0;
        for(int i = 0; i < animationGrid.getChildren().size(); i++){
            if(animationGrid.getChildren().get(i) instanceof Pane){
                animationGrid.getChildren().get(i).setStyle("-fx-background-color: "+asToPlay.frames.get(frame).pixelGrid.get(i/asToPlay.frameSize).get(i%asToPlay.frameSize).toHexCode());
            }else{
                notPaneCounter++;
            }
        }
    }
    
}
