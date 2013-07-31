/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author artur-work
 */
public class Scene {//added extends JPanel

    private GObject[] obj;
    public static boolean drawCoords = true;
    public static boolean showall = true;

    public Scene(String[] fileName) {
        int num = fileName.length;
        obj = new GObject[num];
        for (int i = 0; i < num; i++) {
            obj[i] = new GObject(fileName[i]);
        }

    }

    public void transform(Matrix m) {
        for (int i = 0; i < obj.length; i++) {
            obj[i].transform(m);//transform each object.
        }
    }

    //wait until next lab for Camera
    public void draw(Camera c, Graphics g) {
        /*Go to GObject, check indexes necessary for the face and pick those points
         * from gobject vertex(Point3D)                          
         */
        for (int i = 0; i < obj.length; i++) {
            //System.out.println("Object roperties:\n" + obj[i]);
            for (int j = 0; j < obj[i].face.length; j++) {

                int[] indexes = obj[i].face[j].index;//get all indexes necessary for face.

                Point3D[] points = new Point3D[indexes.length];//create space for points.

                //get points based on indexes and proect with camera.
                for (int k = 0; k < indexes.length; k++) {
                    points[k] = c.project(obj[i].vertex[indexes[k]]);
                }

                double[] allXdouble = new double[points.length];
                double[] allYdouble = new double[points.length];
                double[] allZdouble = new double[points.length];

                //insert the points into arrays for fillPolygon drawing.
                for (int k2 = 0; k2 < points.length; k2++) {
                    allXdouble[k2] = points[k2].x;
                    allYdouble[k2] = points[k2].y;
                    allZdouble[k2] = points[k2].z;
                }

                //PRINT DEBUG INFO=============================================
                /*
                System.out.println("//=========================================//");
                System.out.println("Object number: " + i);
                System.out.println("Face number: " + j);
                System.out.println("num of indexes; " + indexes.length);
                
                System.out.println("Indexes:");
                for (int k4 = 0; k4 < indexes.length; k4++) {
                System.out.print(indexes[k4] + " ");
                }
                System.out.println("\n");
                
                for (int k3 = 0; k3 < allXdouble.length; k3++) {
                System.out.println("allX[" + k3 + "] is : " + allXdouble[k3]
                + "      allY[" + k3 + "] is : " + allYdouble[k3]
                + "      allZ[" + k3 + "] is : " + allZdouble[k3]);
                
                }
                System.out.println("//=========================================//");
                 * 
                 */
                // END PRINT DEBUG INFO=============================================

                //parse doubles into ints for printing.

                int[] allX = new int[allXdouble.length];
                int[] allY = new int[allYdouble.length];
                int[] allZ = new int[allZdouble.length];

                Point3D p1 = new Point3D(allXdouble[0], allYdouble[0], allZdouble[0]);
                Point3D p2 = new Point3D(allXdouble[1], allYdouble[1], allZdouble[1]);
                Point3D p3 = new Point3D(allXdouble[2], allYdouble[2], allZdouble[2]);

                if (Point3D.isFrontFace(p1, p2, p3, c.getVPN())) {

                    for (int k5 = 0; k5 < allX.length; k5++) {
                        allX[k5] = (int) allXdouble[k5];
                        allY[k5] = (int) allYdouble[k5];
                        allZ[k5] = (int) allZdouble[k5];
                    }

                    //check back faces


                    //set color of the face
                    g.setColor(obj[i].face[j].color);
                    //draw polygon for the retreived face.
                    g.fillPolygon(allX, allY, indexes.length);
                    g.setColor(Color.black);

                    if (drawCoords) {
                        for (int ds = 0; ds < 3; ds++) {
                            g.drawString("x : " + allX[ds]
                                    + " y : " + allY[ds]
                                    + " z : " + allZ[ds], allX[ds], allY[ds]);
                        }
                    }
                    //SOME STATISTICS AND HINTS.
                    if (showall) {
                        g.drawString("Num Lock to show/hide all text", 15, 40);
                        g.drawString("+ or - for zoom", 15, 50);
                        g.drawString("* to hide or show coordinates ", 15, 60);
                        g.drawString("/ to pause or resume animation", 15, 70);
                        g.drawString("1 to slow down animation", 15, 80);
                        g.drawString("2 to speed up animation", 15, 90);
                        g.drawString("x to rotate around x axis", 15, 100);
                        g.drawString("y to rotate around y axis", 15, 110);
                        g.drawString("z to rotate around z axis", 15, 120);
                        g.drawString("a to rotate around xyz axis", 15, 130);
                        
                        if (c instanceof PerspectiveCamera) {
                            PerspectiveCamera c2 = (PerspectiveCamera) c;
                            g.setColor(Color.RED);
                            g.drawString("cop",(int) c2.getCop().x, (int) c2.getCop().y);
                            g.fillOval((int) c2.getCop().x, (int) c2.getCop().y, 10, 10);

                        }

                    }
                }
            }

        }

    }

    public String toString() {/* Make it look nice to save your debugging time! */
        String s = "";
        s += ("Scene contains " + obj.length + "obects");
        for (int i = 0; i < obj.length; i++) {
            s += "\n " + obj[i];
        }
        return s;
    }
}
