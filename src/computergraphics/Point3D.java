/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author ak303
 */
public class Point3D {

    public double x, y, z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distance(Point3D p) {
        return sqrt(pow(p.x - x, 2) + pow(p.y - y, 2) + pow(p.z - z, 2));
    }

    public String toString() {

        return "x: " + x + " y: " + y + " z: " + z;

    }

    public Point3D transform(Matrix m) {
        
        Point3D p2 =new Point3D(x,y,z);
        
        p2.x = (x * m.m[0][0]) + (y * m.m[0][1]) + (z * m.m[0][2]) + (1 * m.m[0][3]);
        p2.y = (x * m.m[1][0]) + (y * m.m[1][1]) + (z * m.m[1][2]) + (1 * m.m[1][3]);
        p2.z = (x * m.m[2][0]) + (y * m.m[2][1]) + (z * m.m[2][2]) + (1 * m.m[2][3]);               

        return p2;
    }

    public Vector3D vector(Point3D p) {
        return new Vector3D(p.x - x, p.y - y, p.z - z);
    }

    public static Vector3D faceNormal(Point3D p1, Point3D p2, Point3D p3) {
        Vector3D normal = null;
        Vector3D v1 = p2.vector(p1);
        Vector3D v2 = p3.vector(p1);
        normal = v1.crossProduct(v2);
        return normal;
    }

    public static boolean isFrontFace(Point3D p1, Point3D p2, Point3D p3, Vector3D vpn) {
        Vector3D n = Point3D.faceNormal(p1, p2, p3);
        if (n.dotProduct(vpn) < 0) {
            return true;
        }
        return false;
    }
}