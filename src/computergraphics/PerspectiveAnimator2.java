/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

/**
 *
 * @author artur-work
 */
public class PerspectiveAnimator2 extends PerspectiveAnimator {

    @Override
    protected void setupCamera() {
        
        camera = new PerspectiveCamera2(-5, 5, -5, 5);
        ((PerspectiveCamera2) camera).setupUVN(new Point3D(0, 0, 0), new Vector3D(0, 0, 1), new Vector3D(0, 1, 0));
        ((PerspectiveCamera2) camera).setupCOP(new Point3D(0, 0, 3));
    }

    public static void main(String[] args) {
        System.out.println("Running PerspectiveAnimator2 ");
        new PerspectiveAnimator2().loop();
    }
}
