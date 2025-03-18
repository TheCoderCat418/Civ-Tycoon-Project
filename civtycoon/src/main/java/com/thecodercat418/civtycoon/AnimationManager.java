package com.thecodercat418.civtycoon;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimationManager {

    static HashMap<Animations, AnimationSequence> loadedAnimations = new HashMap<>();

    static ArrayList<Animation> animationControllerTiles = new ArrayList<>();

    public static void setup() {
        File root = new File(AnimationManager.class.getResource("House1.piskel").getPath());
        root = root.getParentFile();
        System.out.println(root.isDirectory());
        for (File f : root.listFiles()) {
            if (f.isDirectory()) {
                AnimationSequence as = new AnimationSequence(f);
                loadedAnimations.put(Animations.valueOf(f.getName().toUpperCase()), as);
            }
        }

        TickManager.register((() -> {
            onTick();
        }));
    }

    public static AnimationSequence getAnimationSequenceFromBuildingType(BuildingType type, boolean randomizeMultiple) {
        switch (type) {
            case HOUSE:
                return AnimationManager.loadedAnimations.get(Animations.HOUSE1);
            case ROAD:
                return AnimationManager.loadedAnimations.get(Animations.ROAD);
            default:
                return AnimationManager.loadedAnimations.get(Animations.NOTSET);
        }
    }

    public static void updateAnimationType(Tile t) {
        for (Animation a : animationControllerTiles) {
            if (a.t.equals(t)) {
                a.updateType();
            }
        }
    }

    public static void register(Animation te) {
        if (animationControllerTiles.indexOf(te) == -1) {
            animationControllerTiles.add(te);
            te.start();
        }
    }

    public static void unregister(TickEntry te) {
        animationControllerTiles.removeIf(tefromarr -> (tefromarr.equals(te)));
    }

    private static void onTick() {
        for (Animation te : animationControllerTiles) {
            te.cycleToNext();
        }
    }

}
