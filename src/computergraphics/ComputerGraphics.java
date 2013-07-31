/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

import java.awt.Color;

/**
 *
 * @author ak303
 */
public class ComputerGraphics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vector3D v1 = new Vector3D(1, -2, 4);
        Vector3D v2 = new Vector3D(2, -3, 5);
        Vector3D v3 = new Vector3D(3, 1, 2);
        Point3D p1 = new Point3D(1, -2, -3);
        Point3D p2 = new Point3D(3, 2, -4);
        
        System.out.println("v1 "+v1);
        System.out.println("v2 "+v2);
        System.out.println("v3 "+v3);
        System.out.println("p1 "+p1);
        System.out.println("p2 "+p2);
        System.out.println("**********************************");

        System.out.println("\n------------DISTANCE TEST-----------");
        System.out.println("distance of p1 and p2 " +p1.distance(p2));
        System.out.println("**********************************");
        
        System.out.println("\n-----------Vector norm test-----------");
        System.out.println("v1 norm "+v1.L2norm());
        System.out.println("**********************************");
        
        System.out.println("\n--------------Vector Dor Product---------");        
        System.out.println("v1 dot prod v2");
        System.out.println(v1.dotProduct(v2));
        System.out.println("**********************************");

        System.out.println("\n---------------Cross product----------");
        System.out.println("v1 corss prod v2: "+v1.crossProduct(v2));
        System.out.println("**********************************");
        
        System.out.println("\n-----------Normalizing test----------");
        v3.normalize();
        System.out.println("\nv3 after normalizing: "+v3);
        
        System.out.println("\n---------- Face 3d Testing ");
        int[] indexes = {2,3,5,6};
        
        Face f =  new Face(indexes, Color.red);
        
        System.out.println(f);
        
        System.out.println("\n--------------Testing GObject--------");
        GObject gobj = new GObject("/resources/pyramid.dat");
        System.out.println(gobj);
        
        System.out.println("\n--------------Testing Matrix--------");
        Matrix m = new Matrix();
        System.out.println(m);
        
        System.out.println("\n--------------Testing Matrix transform by Point3D--------");
        Point3D p3 = new Point3D(2.0,3.0,4.0);
        Matrix m2 = new Matrix();
        m2.m[0][0]=2.7;
        System.out.println(m2);
        
        System.out.println(p3);        
        System.out.println(p3.transform(m2));
        
        System.out.println("\n------------Testing creation of vector between two points");
        Point3D r = new Point3D(1,2,1);
        Point3D s = new Point3D(3,5,1);
        Vector3D vc= r.vector(s);
        System.out.println(vc);
        
        
    }
}
