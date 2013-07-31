/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

import java.awt.Graphics;

/**
 *
 * @author artur-work
 */
public class PerspectiveAnimator extends ParallelAnimator {
    

    protected void setupCamera() {          
        camera = new PerspectiveCamera(-5, 5, -5, 5);
        ((PerspectiveCamera) camera).setupCOP(new Point3D(1, 1, 10));//0,0,3
    }

    public static void main(String[] args) {
        System.out.println("Running PerspectiveAnimator ");
        new PerspectiveAnimator().loop();
    }
}
