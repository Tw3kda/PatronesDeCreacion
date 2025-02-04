package com.balitechy.spacewar.main;

import java.awt.*;

public class VectorBullet {

    private double x;
    private double y;
    public static final int WIDTH = 11;
    public static final int HEIGHT = 21;

    public VectorBullet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {
        y -= 5;
    }

    public void render(Graphics g) {
        g.setColor(java.awt.Color.BLACK); 
        g.drawLine((int) x, (int) y, (int) x, (int) (y - 10));
    }

    // Getter para la posición Y
    public double getY() {
        return y;
    }
}
