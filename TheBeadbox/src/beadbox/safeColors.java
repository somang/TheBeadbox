/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beadbox;

import java.awt.Color;

/**
 *
 * @author somang
 */
class safeColors {
    private Color safeColor;

    public void safeColors() {
        System.out.println("color picker.");
    }
    
    public Color pickColor(int curFrequency, int track){
        safeColor = new Color(curFrequency,curFrequency,10);
        return safeColor;
    }
}
