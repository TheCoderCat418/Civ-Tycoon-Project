package com.thecodercat418.civtycoon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AnimationSequence {
    ArrayList<Integer> frames = new ArrayList<>(); // Frame Class?
    int magicNumber = 16;
    public  AnimationSequence(File file){

        file = new File("C:\\Users\\herman7593\\Downloads\\sprite_0.png");
        if(file.exists());
        {
            try {
                BufferedImage bi = ImageIO.read(file);
                magicNumber = bi.getWidth();
                for(int i = 0; i <  bi.getWidth(); i++){
                    for(int j = 0; j < bi.getHeight(); j++){
                       frames.add(bi.getRGB(i, j));
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("aa");
        }
    }
}
