/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

import static java.lang.Math.*;  /* Now you can use math functions without the Math. prefix */


public class Vector3D implements Cloneable {

    public double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "x: " + x + "        y: " + y + "        z: " + z;
    }

    public Vector3D clone() throws CloneNotSupportedException {
        return new Vector3D(x, y, z);
    }

    public double L2norm() {
        double norm = sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
        return norm;
    }

    public double dotProduct(Vector3D v) {
        return ((v.x * x) + (v.y * y) + (v.z * z));
    }

    public Vector3D crossProduct(Vector3D v) {
        double newX = (y * v.z - z * v.y);
        double newY = -1 * (x * v.z - z * v.x);
        double newZ = (x * v.y - y * v.x);
        return new Vector3D(newX, newY, newZ);
    }

    public void normalize() {
        double length = L2norm();
        x = x / length;
        y = y / length;
        z = z / length;         
    }
    
    public Vector3D transform(Matrix m){
                
        x = (x * m.m[0][0]) + (y * m.m[0][1]) + (z * m.m[0][2]) + (1 * m.m[0][3]);
        y = (x * m.m[1][0]) + (y * m.m[1][1]) + (z * m.m[1][2]) + (1 * m.m[1][3]);
        z = (x * m.m[2][0]) + (y * m.m[2][1]) + (z * m.m[2][2]) + (1 * m.m[2][3]); 

        return this;
    }
}