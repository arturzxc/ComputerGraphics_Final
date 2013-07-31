/*
 * To change this template, choose Tools | Templates
 * uper.getVand open the template in the editor.
 */
package computergraphics;

/**
 *
 * @author artur-work
 */
public class PerspectiveCamera2 extends PerspectiveCamera{

    public Vector3D getVPN() {/*return the view plan normal vector*/
        return vpn;
    }

    public PerspectiveCamera2(double xmin_, double xmax_, double ymin_, double ymax_) {
        super(xmin_, xmax_, ymin_, ymax_);        
    }

    protected Point3D cameraTransform(final Point3D p) {
        Point3D p2 = super.cameraTransform(p);
        /*
        p2.x += xtranslate;
        p2.y += ytranslate;
        p2.z += ztranslate;
        */
       // System.out.println("m before"+m);
        m.setTranslation(xtranslate, ytranslate, ztranslate);
       // System.out.println("m after"+m);
        //System.out.println("pt befre: "+p2);
        p2=p2.transform(m);
        //System.out.println("pt after: "+p2);
        return p2;
    }

    public void setupUVN(Point3D vrp_, Vector3D vpn_, Vector3D vuv_) {
        vrp = vrp_;
        vpn = vpn_;
        vuv = vuv_;        
    }     
    
    public static int xtranslate, ytranslate, ztranslate;
    public static double xcamrotate, ycamrotate,zcamrotate;
    
    private Matrix m = new Matrix(); //camera transformation matrix
    private Point3D vrp = new Point3D(0, 0, 0); //view reference point: the origin of camera coordinating system
    private Vector3D vpn = new Vector3D(0, 0, 1), vuv = new Vector3D(0, 1, 0);  //view plane normal (axis n) and the view up vector (axis v)
    private Point3D cop = new Point3D(0, 0, -4); //centre of projection
}
