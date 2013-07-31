/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

/**
 *
 * @author ak303
 */
import java.util.Scanner;
import java.io.*;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GObject {

    public Point3D[] vertex;
    public Face[] face;

    public GObject(Point3D[] vertex, Face[] face) {
        this.vertex = vertex;
        this.face = face;
    }

    public GObject(String fileName) {
        try {
            String nextLine = "";
            FileReader fr;
            BufferedReader br;
            String str = fileName;//Object.class.getResource(fileName).toString();
            //str = str.substring(5, str.length());//get rid of 'file://' before path.
            fr = new FileReader(str);
            br = new BufferedReader(fr);



            try {
                //load vertices(points3d)-------------------------------------------
                int numOfVertices = Integer.parseInt(br.readLine().trim());
                vertex = new Point3D[numOfVertices];
                //System.out.println("Number of verices: " + numOfVertices);
                for (int i = 0; i < numOfVertices; i++) {
                    String[] temp = br.readLine().split(" ");
                    vertex[i] = new Point3D(Double.parseDouble(temp[0].trim()), Double.parseDouble(temp[1].trim()), Double.parseDouble(temp[2].trim()));
                    //System.out.println("loaded vertex: " + vertex[i]);
                }


                //-----------SKIP EMPTY LINE between vertex and face loading
                nextLine = br.readLine();
                //----------------------

                //load Faces--------------------------------------------------------
                int numOfFaces = Integer.parseInt(br.readLine().trim());
                face = new Face[numOfFaces];
                //System.out.println("Number of Faces: "+numOfFaces);

                for (int i = 0; i < numOfFaces; i++) {


                    int numberOfVertices = Integer.parseInt(br.readLine().trim());
                    String[] indexTemp = br.readLine().split(" ");
                    int[] index = new int[indexTemp.length];
                    for (int j = 0; j < numberOfVertices; j++) {
                        index[j] = Integer.parseInt(indexTemp[j]);
                    }

                    String[] colorTemp = br.readLine().split(" ");
                    Float[] rgb = new Float[indexTemp.length];
                    rgb[0] = Float.parseFloat(colorTemp[0]);
                    rgb[1] = Float.parseFloat(colorTemp[1]);
                    rgb[2] = Float.parseFloat(colorTemp[2]);

                    Color color = new Color(rgb[0], rgb[1], rgb[2]);

                    face[i] = new Face(index, color);
                    //System.out.println(face[i]);
                }

            } catch (Exception e) {
                System.out.println("CANNOT LOAD MODEL!");
            }

        } catch (IOException ex) {
            Logger.getLogger(GObject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String toString() {
        String s = "";
        for (int i = 0; i < face.length; i++) {
            s += "\nFace " + face[i];
        }
        s += "\n";
        for (int i = 0; i < vertex.length; i++) {
            s += "\nVertex " + vertex[i];
        }

        return s;
    }

    public void transform(Matrix m) {
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = vertex[i].transform(m);
        }
    }
}
