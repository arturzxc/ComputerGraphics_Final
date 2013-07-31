/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

/**
 *
 * @author ak303
 */
import java.awt.Color;

public class Face {

    public int[] index;
    public Color color;

    public Face(int[] index, Color color) {
        this.index = index;
        this.color = color;
    }

    @Override
    public String toString() {        
        String s ="Indexes ";

        for (int i = 0; i < index.length; i++) {
            s += index[i]+" ";
        }
        s += "      Color:   " + color.getRed()+" "+color.getGreen()+" "+color.getBlue();
        return s;
    }
}