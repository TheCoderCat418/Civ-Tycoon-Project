package com.thecodercat418.civtycoon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AnimationSequence {
    AnimationFrame[] frames;
    int frameSize = 16;

    public AnimationSequence(File folder) {
        try {
            frames = new AnimationFrame[folder.listFiles().length];
            for(File f : folder.listFiles()){
                
                //System.out.println(f.getName().split("\\.")[0]);
                BufferedImage bi = ImageIO.read(f);
                if(bi.getWidth()!=frameSize){
                    throw new RuntimeException("ERR WITH SPRITE: Width does not match frame! Please check '"+f.getAbsolutePath());
                }
                AnimationFrame af = new AnimationFrame();
                for (int i = 0; i < bi.getWidth(); i++) {
                    for (int j = 0; j < bi.getHeight(); j++) {
                        Color c = new Color(new java.awt.Color(bi.getRGB(i, j)));
                        af.setPixel(i, j, c);
                    }
                }
                frames[Integer.parseInt(f.getName().split("\\.")[0])] = af;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (AnimationFrame i : frames) {
            //System.out.println(i.toString());
        }
        System.out.println("aa");
    }
}
