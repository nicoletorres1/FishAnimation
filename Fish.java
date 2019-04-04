/**
 * Author: Nicole Torres
 * CS 3331 Exam 1 Extra Credit
 * 04/02/19
 */

package edu.utep.cs.exam1extracredit;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.Timer;


/**
 * Sample NoApplet to display the current time continuously.
 * See Section 4.7 on pages 149-154.
 */
@SuppressWarnings("serial")

public class Fish extends NoApplet {
    //fields
    private Image wallpaper; // this is the background image
    private Image fish;
    private int width; // wallpaper width
    private int height; // wallpaper height
    private int x;
    private int y; // fish coordinates
    private int dx = 2; //2
    private int dy = 5; // how coordinates will be changed 5
    private Dimension dim; // dimensions of jPanel


    private Timer timer;
    private int delay = 10; // how fast animation is updated

    /**
     *
     * @param params
     */

    public Fish(String[] params) {
        super(params);
    }


    @Override
    public void init() {
        //initialization
        String param = getParameter("delay");
        this.wallpaper = getImage(getCodeBase(), "image/ocean.jpg"); //assigning wallpaper
        this.fish = getImage(getCodeBase(), "image/fish.png");

        if (param != null) {
            delay = Integer.parseInt(param);
        }
        this.dim = getSize();
        this.width = dim.width;
        this.height = dim.height;
        y = dim.height/2;
        timer = new Timer(delay, e -> repaint());
    }

    /**
     *
     * @param g
     */
    public void update(Graphics g){
        //use for repaint()
        x += dx; // update x
        y += dy;

        if(y < ( height/2)-50){
            dy *= -1;
        }
        if(y > (height/2) + 50){
            dy *= -1;
        }
        // if fish goes outside of right boundary flip direction
        if(x > width){
            dx *= -1;
        }
        // if fish goes outside of left boundary flip direction
        if (x < -150){
            dx*= -1;
        }

        g.drawImage(wallpaper, 0, 0, this);
        g.drawImage(fish, x, y, this);
    }

    /**
     *
     * @param move
     * @return
     */
    public BufferedImage flip(BufferedImage move) {

        BufferedImage img = new BufferedImage(move.getWidth(), move.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int i = move.getWidth() - 1; i > 0; i--) {
            for (int j = 0; j < move.getHeight(); j++) {
                img.setRGB(move.getWidth() - i, j, move.getRGB(i, j));
            }
        }
        return img;
    }

    /**
     * @param g
     * contains everything that needs to be
     * displayed on screen
     */
    public void paintComponent(Graphics g) {
        update(g);
    }

    /**
     * Start the timer.
     */
    @Override
    public void start() {
        timer.start();
    }

    /**
     * Stop the timer to prevent from wasting CPU time.
     */
    @Override
    public void stop() {
        timer.stop();
    }

    public static void main(String[] args) {

        new Fish(new String[]{"width=580", "height=400"}).run();
    }
}