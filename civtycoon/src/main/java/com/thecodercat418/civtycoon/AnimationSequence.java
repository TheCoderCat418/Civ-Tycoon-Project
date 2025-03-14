package com.thecodercat418.civtycoon;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AnimationSequence {
    ArrayList<Color> frames = new ArrayList<>(); // Frame Class?
    int magicNumber = 16;
    public  AnimationSequence(File file){
        

            try {
                BufferedImage bi = ImageIO.read(AnimationSequence.class.getResourceAsStream("sprite_0.png"));
                magicNumber = bi.getWidth();
                for(int i = 0; i <  bi.getWidth(); i++){
                    for(int j = 0; j < bi.getHeight(); j++){
                       Color c = new Color(bi.getRGB(i, j));
                       frames.add(c);
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for(Color i :  frames){
                System.out.println(i.toString());
            }
            System.out.println("aa");
        }
    }

