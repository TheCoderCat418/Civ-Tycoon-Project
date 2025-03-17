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

    public static void register(Animation te) {
        if (animationControllerTiles.indexOf(te) == -1) {
            animationControllerTiles.add(te);
            te.play();
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
