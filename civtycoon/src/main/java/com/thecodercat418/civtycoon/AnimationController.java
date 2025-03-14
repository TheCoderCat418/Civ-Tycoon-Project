package com.thecodercat418.civtycoon;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class AnimationController {
    AnimationSequence as;
    Tile tilelinkedto;
    public AnimationController(Tile t, AnimationSequence as){
        this.as = as;
        this.tilelinkedto = t;
    }

    void play(){
        int frameCounter = 0;
        for(int i = 0; i < tilelinkedto.linkedAnimationGrid.getChildren().size(); i++){
            if(tilelinkedto.linkedAnimationGrid.getChildren().get(i) instanceof Pane){
                tilelinkedto.linkedAnimationGrid.getChildren().get(i).setStyle("-fx-background-color: #"+Integer.toHexString(as.frames.get(frameCounter).getRed())+Integer.toHexString(as.frames.get(frameCounter).getGreen())+Integer.toHexString(as.frames.get(frameCounter).getBlue()));
                frameCounter++;
            }
        }
    }
    void stop(){}
    void step(){}
    
}
