/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

/**
 *
 * @author artur-work
 */
public class PerspectiveCamera extends Camera {

    protected Point3D projectionTransform(final Point3D p) {
        
        Point3D p2 = super.cameraTransform(p);
        double d = cop.distance(p);
        p2.x = p.x / (p.z / d + 1);
        p2.y = p.y / (p.z / d + 1);
        return p2;
    }

    public PerspectiveCamera(double xmin_, double xmax_, double ymin_, double ymax_) {
        super(xmin_, xmax_, ymin_, ymax_);
        zoom = 70;
        
    }

    public void setupCOP(Point3D cop_) {
        System.out.println("by " + by);
        cop.x = (Animator.WIDTH / 2 - cop_.x);
        cop.y = (Animator.HEIGHT / 2 - cop_.y);
        cop.z = (cop_.z);
    }

    public Point3D getCop() {       
        return this.cop;        
    }
    
    private Point3D cop = new Point3D(0, 0, -4); //centre of projection
}
