/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

/**
 *
 * @author artur-work
 */
public class Camera {

    public Vector3D getVPN() {

        return new Vector3D(0, 0, 1);

    }

    protected Point3D cameraTransform(final Point3D p) {
        Point3D p2 = new Point3D(p.x, p.y, p.z);

        p2.x *= zoom;
        p2.y *= zoom;
        p2.z *= zoom;

        return p2;
    }

    protected Point3D projectionTransform(final Point3D p) {
        Point3D p2 = new Point3D(p.x, p.y, p.z);

        return p;
    }

    protected final Point3D viewportTransform(final Point3D p) {
        Point3D p2 = new Point3D(p.x, p.y, p.z);
        p2.x += ((bx - ax) / 2);
        p2.y += ((by - ay) / 2);
        return p2;
    }

    public final Point3D project(final Point3D p) {
        Point3D temp = cameraTransform(p);
        temp = projectionTransform(temp);
        return viewportTransform(temp);
    }

    public Camera(double xmin_, double xmax_, double ymin_, double ymax_) {
        xmin = xmin_;
        xmax = xmax_;
        ymin = ymin_;
        ymax = ymax_;
    }

    public void setViewport(int width, int height) {
        /*calculate ax, bx, ay, by*/
        ax = xmin;
        ay = ymin;
        bx = width - xmax;
        by = height - ymax;
    }

    public String toString() {
        /* Make it look nice to save your debugging time! */
        String s = "\nCamera";
        s += "\n    ax " + ax + "   bx " + bx + "     ay " + ay + "     by" + by;
        s += "\n    xmin " + xmin + "   xmax " + xmax + "     ymin " + ymin + "     ymax" + ymax;
        return s;
    }
    private double xmin, xmax, ymin, ymax;
    private double fcp, bcp;  //NOT USED: front & back clippling planes
    protected double ax, bx, ay, by;
    public static double zoom = 30;
}
