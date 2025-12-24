package com.futureclient.util;

/**
 * Utility class for color operations
 */
public class ColorUtil {
    
    /**
     * Create an RGBA color
     */
    public static int rgba(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
    
    /**
     * Create an RGB color with full opacity
     */
    public static int rgb(int r, int g, int b) {
        return rgba(r, g, b, 255);
    }
    
    /**
     * Extract red component
     */
    public static int getRed(int color) {
        return (color >> 16) & 0xFF;
    }
    
    /**
     * Extract green component
     */
    public static int getGreen(int color) {
        return (color >> 8) & 0xFF;
    }
    
    /**
     * Extract blue component
     */
    public static int getBlue(int color) {
        return color & 0xFF;
    }
    
    /**
     * Extract alpha component
     */
    public static int getAlpha(int color) {
        return (color >> 24) & 0xFF;
    }
    
    /**
     * Interpolate between two colors
     */
    public static int interpolate(int color1, int color2, float ratio) {
        int r1 = getRed(color1);
        int g1 = getGreen(color1);
        int b1 = getBlue(color1);
        int a1 = getAlpha(color1);
        
        int r2 = getRed(color2);
        int g2 = getGreen(color2);
        int b2 = getBlue(color2);
        int a2 = getAlpha(color2);
        
        int r = (int)(r1 + (r2 - r1) * ratio);
        int g = (int)(g1 + (g2 - g1) * ratio);
        int b = (int)(b1 + (b2 - b1) * ratio);
        int a = (int)(a1 + (a2 - a1) * ratio);
        
        return rgba(r, g, b, a);
    }
}
