package com.thecodercat418.civtycoon;

public class Color {
    int red;
    int green;
    int blue;
    boolean isVisible = true;

    public Color(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    public Color(int r, int g, int b, boolean visible) {
        red = r;
        green = g;
        blue = b;
        isVisible = visible;
    }

    public Color(java.awt.Color color) {
        red = color.getRed();
        green = color.getGreen();
        blue = color.getBlue();
    }

    public String toHexCode() {
        return "#" + Integer.toHexString(red) + Integer.toHexString(green) + Integer.toHexString(blue);
    }
}
