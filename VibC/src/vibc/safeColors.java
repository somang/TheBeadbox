/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vibc;

import java.awt.Color;
import java.io.*;

/**
 *
 * @author somang
 * 
 * To change colors, this class provides an array of color blind safe colors.
 * However, this should be called when the "Color Blind Option" gets ticked.
 * If the user is not Color Blind, then random color gets called.
 */
class safeColors {
    
    private Color safeColor;
    private int size=100; // temporarily palette size.
    private Color[] scPalette = new Color[100];

    public void safeColorsInit() {
        // initiate the palette.
        int index = 0;
        int[] intparts = new int[3];
        String scCode;
        String[] parts;
        try{
            InputStream in = getClass().getResourceAsStream("scPalette.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while ((scCode = br.readLine()) != null)   {
                parts = scCode.split(",");
                for (int i=0;i<3;i++){
                    intparts[i] = Integer.parseInt(parts[i]);
                }
                scPalette[index] = Color.getHSBColor(intparts[0],intparts[1],intparts[2]);
                index++;
            }  
            in.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public Color pickColor(int curFrequency, int track){
        /* This method should return a color corresponding to a track number.
        Question 1. How do we know the track number before its dragged? */
        safeColor = scPalette[curFrequency];
        return safeColor;
    }
}
