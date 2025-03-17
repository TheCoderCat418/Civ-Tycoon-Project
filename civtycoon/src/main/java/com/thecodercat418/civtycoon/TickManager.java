package com.thecodercat418.civtycoon;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;

public class TickManager {
    public static double gameSpeed = 1;
    public final static int TICKCONSTANT = 10;
    private static int tickCounter = 0;
    private static ArrayList<TickEntry> tickEntries = new ArrayList<>();
    private static boolean hasSetup = false;
    public static void setup(){
        if(hasSetup){
            return;
        }
        new AnimationTimer() {
            public void handle(long deltaTime){
                if(tickCounter>=TICKCONSTANT*gameSpeed){
                    tickCounter=0;
                    onTick();
                }else{
                    tickCounter++;
                }
            }
        }.start();
        hasSetup = true;
    }
    public static void register(TickEntry te){
        if(tickEntries.indexOf(te)==-1){
            tickEntries.add(te);
        }
    }
    public static void unregister(TickEntry te){
        tickEntries.removeIf(tefromarr -> (tefromarr.equals(te)));
    }


    private static void onTick(){
        for(TickEntry te : tickEntries){
            te.onCalled();
        }
    }
}
