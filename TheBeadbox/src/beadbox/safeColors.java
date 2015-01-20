/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beadbox;

import java.awt.Color;
import java.io.*;

/**
 *
 * @author Somang and Albert
 * 
 * To change colors, this class provides an array of color blind safe colors.
 * However, this should be called when the "Color Blind Option" gets ticked.
 * If the user is not Color Blind, then random color gets called.
 */
class safeColors {
    private int size=20; // temporarily palette size.
    private int[][] safeColorP = new int[size][3];

    public safeColors() {
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
                safeColorP[index][0] = intparts[0];
                safeColorP[index][1] = intparts[1];
                safeColorP[index][2] = intparts[2];
                index++;
            }  
            in.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public int[] pickRGB(int curFrequency, int track){
        /* Picks Color corresponding to the current frequency, and track number. */ 
        int red = (safeColorP[track][0]+(curFrequency/20));
        int green = (safeColorP[track][1]+(curFrequency/20));
        int blue = (safeColorP[track][2]+(curFrequency/20));
        if (red > 255) red = 255;
        if (green > 255) green = 255;
        if (blue > 255) blue = 255;
        int[] col = new int[]{red,green,blue};
        return col;
    }
    
    public Color pickColor(int curFrequency, int track){
        /* Picks Color corresponding to the current frequency, and track number. */ 
        int[] col = pickRGB(curFrequency,track);
        return new Color(col[0],col[1],col[2]);
    }
}
