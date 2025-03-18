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
        String reds = Integer.toHexString(red);
        String greens = Integer.toHexString(green);
        String blues = Integer.toHexString(blue);
        if (reds.length() == 1) {
            reds = "0" + reds;
        }
        if (greens.length() == 1) {
            greens = "0" + greens;
        }
        if (blues.length() == 1) {
            blues = "0" + blues;
        }
        return "#" + reds + greens + blues;
    }

    public String toHexCode(AnimationFilters af) {
        String reds = Integer.toHexString(red);
        String greens = Integer.toHexString(green);
        String blues = Integer.toHexString(blue);

        if (reds.length() == 1) {
            reds = "0" + reds;
        }
        if (greens.length() == 1) {
            greens = "0" + greens;
        }
        if (blues.length() == 1) {
            blues = "0" + blues;
        }
        switch (af) {
            case RED:
                return "#" + reds + "0000";
            case BLUE:
                return "#0000" + blues;
            case GREEN:
                return "#00" + greens + "00";

            default:
                return "#" + reds + greens + blues;
        }

    }
}
