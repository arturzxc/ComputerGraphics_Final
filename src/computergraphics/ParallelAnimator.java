/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

/**
 *
 * @author artur-work
 */
import java.awt.*;
import static java.lang.Math.*;

public class ParallelAnimator extends Animator {

    private static final String[] files = {"pyramid.dat", "test.dat"};//  

    public ParallelAnimator() {
        super();

        scene = new Scene(files);
        setupCamera();
    }

    protected void setupCamera() {
        camera = new Camera(-5, 5, -5, 5);
        
    }

    protected void animate(Graphics g) {

        camera.setViewport(WIDTH, HEIGHT);
        
        if (g == null || scene == null || camera == null) {
            return;
        }

        Matrix mX = new Matrix(), mY = new Matrix(), mZ = new Matrix();
        int num = 10;
        mX.setRotationX(-PI / 11 / num);
        mY.setRotationY(PI / 13 / num);
        mZ.setRotationZ(PI / 17 / num);

        //rotate against chose axis only.
        if (axis == 4) {
            scene.transform(mZ.multiply(mY.multiply(mX)));
        }
        if (axis == 1) {
            scene.transform(mX);
        }
        if (axis == 2) {
            scene.transform(mY);
        }
        if (axis == 3) {
            scene.transform(mZ);
        }
        scene.draw(camera, g);
    }

    public static void main(String[] args) {
        new ParallelAnimator().loop();
    }
    private Scene scene;
    protected Camera camera;

}
