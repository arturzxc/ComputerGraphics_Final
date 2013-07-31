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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Animator extends JFrame {

    public Animator() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ADD) {
                    Camera.zoom += 5;
                    System.out.println("Zoomed in " + Camera.zoom);
                }
                if (arg0.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                    Camera.zoom -= 5;
                    System.out.println("Zoomed out " + Camera.zoom);
                }
                if (arg0.getKeyCode() == KeyEvent.VK_MULTIPLY) {
                    Scene.drawCoords = !Scene.drawCoords;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_NUM_LOCK) {
                    Scene.showall = !Scene.showall;
                    Scene.drawCoords = !Scene.drawCoords;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_DIVIDE) {
                    Animator.running = !Animator.running;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_1) {
                    Animator.INTERVAL += 15;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_2) {
                    if (Animator.INTERVAL - 15 > 0) {
                        Animator.INTERVAL -= 15;
                    }
                }
                if (arg0.getKeyCode() == KeyEvent.VK_X) {
                    ParallelAnimator.axis = 1;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_Y) {
                    ParallelAnimator.axis = 2;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_Z) {
                    ParallelAnimator.axis = 3;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_A) {
                    ParallelAnimator.axis = 4;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
                    PerspectiveCamera2.xtranslate -= 10;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
                    PerspectiveCamera2.xtranslate += 10;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_UP) {
                    PerspectiveCamera2.ytranslate -= 10;
                }
                if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
                    PerspectiveCamera2.ytranslate += 10;
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }
    private int R;

    protected void animate(Graphics g) {
        g.setColor(Color.RED);
        R = R > 20 ? 0 : R + 1;
        g.fillPolygon(new int[]{100, WIDTH / 2, R * 3}, new int[]{100, HEIGHT / 2, R * 3}, 3);
    }

    protected final void loop() {
        while (true) {
            while (running) {
                image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = image.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());

                animate(g2);

                ((Graphics2D) getGraphics()).drawImage(image, 0, 0, null);
                paint(getGraphics());
                try {
                    Thread.sleep(INTERVAL);
                } catch (InterruptedException e) {
                }
            }
            try {
                Thread.sleep(2);//so cpu is not permanently hogged between loops.
            } catch (InterruptedException e) {
            }
        }
    }

    public final void paint(Graphics g) {
    }
    protected static final int WIDTH = 600;
    protected static final int HEIGHT = 400;
    public static int INTERVAL = 40;
    private BufferedImage image;
    public static boolean running = true;
    public static int axis = 4;

    public static void main(String[] args) {
        new Animator().loop();
    }
}
